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
import org.tcc.gestao.normas.core.api.ExecucaoAuditoriaController;
import org.tcc.gestao.normas.core.api.dto.ExecutarAuditoriaCommandDTO;
import org.tcc.gestao.normas.core.api.dto.ItemExecucaoAuditoriaCommandDTO;
import org.tcc.gestao.normas.core.domain.model.ExecucaoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ExecucaoAuditoriaId;
import org.tcc.gestao.normas.core.domain.model.ItemExecucaoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ItemExecucaoAuditoriaId;
import org.tcc.gestao.normas.core.domain.model.Norma;
import org.tcc.gestao.normas.core.domain.model.NormaId;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoriaId;
import org.tcc.gestao.normas.core.infra.ExecucaoAuditoriaRepository;
import org.tcc.gestao.normas.core.infra.ItemExecucaoAuditoriaRepository;
import org.tcc.gestao.normas.core.infra.NormaRepository;
import org.tcc.gestao.normas.core.infra.ProcessoAuditoriaRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest
@AutoConfigureMockMvc
public class ExecucaoAuditoriaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	NormaRepository normaRepository;
	
	@Autowired
	ProcessoAuditoriaRepository processoRepository;
	
	@Autowired
	ExecucaoAuditoriaRepository execucaoRepository;
	
	@Autowired
	ItemExecucaoAuditoriaRepository itemExecucaoRepository;
	
	@Test
	public void deveCriarExecucao() throws Exception{
		
		Norma norma1 = Norma.builder()
                .id(NormaId.generate())
                .codigo("NR001")
                .descricao("Norma A").build();
		
		normaRepository.insert(norma1);
		
		Norma norma2 = Norma.builder()
                .id(NormaId.generate())
                .codigo("NR002")
                .descricao("Norma B").build();
		
		normaRepository.insert(norma2);
		
		ProcessoAuditoriaId processoId = ProcessoAuditoriaId.generate();

		processoRepository.insert(ProcessoAuditoria.builder()
				                    .id(processoId)
				                    .descricao("Processo A")
				                    .normas(Set.of(norma1.getId(), norma2.getId()))
				                    .build());
		
		ExecutarAuditoriaCommandDTO dto = ExecutarAuditoriaCommandDTO.builder()
				                                                         .descricao("Execucao A")
				                                                         .processo(processoId.toString())
				                                                         .itens(Set.of(ItemExecucaoAuditoriaCommandDTO.builder()
				                                                        		                                      .norma(norma1.getId().toString())
				                                                        		                                      .aprovado(true)
				                                                        		                                      .build(),
				                                                        		       ItemExecucaoAuditoriaCommandDTO.builder()
				                                                        		                                      .norma(norma2.getId().toString())
				                                                        		                                      .aprovado(false)
				                                                        		                                      .build()))
				                                                         .build();
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(ExecucaoAuditoriaController.PATH)
				    .contentType(MediaType.APPLICATION_JSON_VALUE)
				    .content(TestUtils.objectToJson(dto)))
				    .andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void deveExcluirExecucao() throws Exception{

		Norma norma1 = Norma.builder()
                .id(NormaId.generate())
                .codigo("NR001")
                .descricao("Norma A").build();
		
		normaRepository.insert(norma1);
		
		ProcessoAuditoriaId processoId = ProcessoAuditoriaId.generate();

		ProcessoAuditoria processo = ProcessoAuditoria.builder()
									                .id(processoId)
									                .descricao("Processo A")
									                .normas(Set.of(norma1.getId()))
									                .build();
		processoRepository.insert(processo);
		
		ExecucaoAuditoriaId execucaoId = ExecucaoAuditoriaId.generate();
		
		ItemExecucaoAuditoria item1 = ItemExecucaoAuditoria.builder()
													        .id(ItemExecucaoAuditoriaId.generate())
													        .norma(norma1.getId())
													        .aprovado(true)
													        .build();
				
		itemExecucaoRepository.insert(item1);
		
		execucaoRepository.insert(ExecucaoAuditoria.builder()
				                                   .id(execucaoId)
				                                   .processo(processoId)
				                                   .descricao("Execucao A")
				                                   .itens(Set.of(item1))
				                                   .build());
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete(ExecucaoAuditoriaController.PATH + "/" + execucaoId.toString())
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
		
		ProcessoAuditoriaId processoIdA = ProcessoAuditoriaId.generate();
		
		processoRepository.insert(ProcessoAuditoria.builder()
                .id(processoIdA)
                .descricao("Processo A")
                .normas(Set.of(norma1.getId(), norma2.getId()))
                .build());
		
		ProcessoAuditoriaId processoIdB = ProcessoAuditoriaId.generate();
		
		processoRepository.insert(ProcessoAuditoria.builder()
                .id(processoIdB)
                .descricao("Processo B")
                .normas(Set.of(norma2.getId(), norma3.getId()))
                .build());
		
		ItemExecucaoAuditoria itemA1 = ItemExecucaoAuditoria.builder()
										                 .id(ItemExecucaoAuditoriaId.generate())
										                 .norma(norma1.getId())
										                 .aprovado(true)
										                 .build();
		
		itemExecucaoRepository.insert(itemA1);
		
		ItemExecucaoAuditoria itemA2 = ItemExecucaoAuditoria.builder()
										                 .id(ItemExecucaoAuditoriaId.generate())
										                 .norma(norma2.getId())
										                 .aprovado(false)
										                 .build();
				
		itemExecucaoRepository.insert(itemA2);
		
		execucaoRepository.insert(ExecucaoAuditoria.builder()
                .id(ExecucaoAuditoriaId.generate())
                .processo(processoIdA)
                .descricao("Execucao A")
                .itens(Set.of(itemA1, itemA2))
                .build());
		
		
		ItemExecucaoAuditoria itemB1 = ItemExecucaoAuditoria.builder()
										                .id(ItemExecucaoAuditoriaId.generate())
										                .norma(norma2.getId())
										                .aprovado(false)
										                .build();
        
		itemExecucaoRepository.insert(itemB1);
		
		ItemExecucaoAuditoria itemB2 = ItemExecucaoAuditoria.builder()
										                .id(ItemExecucaoAuditoriaId.generate())
										                .norma(norma3.getId())
										                .aprovado(false)
										                .build();

		itemExecucaoRepository.insert(itemB2);
		
		execucaoRepository.insert(ExecucaoAuditoria.builder()
                .id(ExecucaoAuditoriaId.generate())
                .processo(processoIdB)
                .descricao("Execucao B")
                .itens(Set.of(itemB1, itemB2))
                .build());
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(ExecucaoAuditoriaController.PATH + "/")
				    .contentType(MediaType.APPLICATION_JSON_VALUE))
				    .andExpect(status().is2xxSuccessful());
	}
}
