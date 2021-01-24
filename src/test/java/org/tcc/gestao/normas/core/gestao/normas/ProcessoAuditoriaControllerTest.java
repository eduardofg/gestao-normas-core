package org.tcc.gestao.normas.core.gestao.normas;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tcc.gestao.normas.core.TestUtils;
import org.tcc.gestao.normas.core.api.ProcessoAuditoriaController;
import org.tcc.gestao.normas.core.api.dto.IncluirProcessoAuditoriaCommandDTO;
import org.tcc.gestao.normas.core.domain.model.Norma;
import org.tcc.gestao.normas.core.domain.model.NormaId;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoriaId;
import org.tcc.gestao.normas.core.infra.NormaRepository;
import org.tcc.gestao.normas.core.infra.ProcessoAuditoriaRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest
@AutoConfigureMockMvc
public class ProcessoAuditoriaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	NormaRepository normaRepository;
	
	@Autowired
	ProcessoAuditoriaRepository processoRepository;
	
	
	@Test
	public void deveCriarProcesso() throws Exception{
		
		Norma norma1 = Norma.builder()
                .id(NormaId.generate())
                .codigo("NR001")
                .descricao("Norma A").build();
		
		normaRepository.insert(norma1);
		
		IncluirProcessoAuditoriaCommandDTO dto = IncluirProcessoAuditoriaCommandDTO.builder()
				                                                         .descricao("Processo A")
				                                                         .normas(Set.of(norma1.getId().toString()))
				                                                         .build();
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(ProcessoAuditoriaController.PATH)
				    .contentType(MediaType.APPLICATION_JSON_VALUE)
				    .content(TestUtils.objectToJson(dto)))
				    .andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void deveExcluirProcesso() throws Exception{

		Norma norma1 = Norma.builder()
                .id(NormaId.generate())
                .codigo("NR001")
                .descricao("Norma A").build();
		
		normaRepository.insert(norma1);
		
		ProcessoAuditoriaId processoId = ProcessoAuditoriaId.generate();

		processoRepository.insert(ProcessoAuditoria.builder()
				                    .id(processoId)
				                    .descricao("Processo A")
				                    .normas(Set.of(norma1.getId()))
				                    .build());
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete(ProcessoAuditoriaController.PATH + "/" + processoId.toString())
				    .contentType(MediaType.APPLICATION_JSON_VALUE))
				    .andExpect(status().is2xxSuccessful());
		
	}
	
	@Test
	public void deveBuscarTodosRegistros() throws Exception{
		
		Norma norma1 = Norma.builder()
                .id(NormaId.generate())
                .codigo("NR001")
                .descricao("Norma A").build();
		
		Norma norma2 = Norma.builder()
		        .id(NormaId.generate())
		        .codigo("NR002")
		        .descricao("Norma B").build();
		
		Norma norma3 = Norma.builder()
		        .id(NormaId.generate())
		        .codigo("NR003")
		        .descricao("Norma C").build();
        
		normaRepository.insert(norma1);
		normaRepository.insert(norma2);
		normaRepository.insert(norma3);
		
		processoRepository.insert(ProcessoAuditoria.builder()
                .id(ProcessoAuditoriaId.generate())
                .descricao("Processo A")
                .normas(Set.of(norma1.getId(), norma2.getId()))
                .build());
		
		processoRepository.insert(ProcessoAuditoria.builder()
                .id(ProcessoAuditoriaId.generate())
                .descricao("Processo A")
                .normas(Set.of(norma2.getId(), norma3.getId()))
                .build());
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(ProcessoAuditoriaController.PATH + "/")
				    .contentType(MediaType.APPLICATION_JSON_VALUE))
				    .andExpect(status().is2xxSuccessful());
	}
}
