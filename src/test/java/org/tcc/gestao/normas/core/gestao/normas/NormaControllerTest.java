package org.tcc.gestao.normas.core.gestao.normas;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.tcc.gestao.normas.core.api.NormaController;
import org.tcc.gestao.normas.core.api.dto.IncluirNormaCommandDTO;
import org.tcc.gestao.normas.core.domain.model.Norma;
import org.tcc.gestao.normas.core.domain.model.NormaId;
import org.tcc.gestao.normas.core.infra.NormaRepository;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest
@AutoConfigureMockMvc
public class NormaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	NormaRepository normaRepository;
	
	
	@Test
	public void deveCriarNorma() throws Exception{
		
		IncluirNormaCommandDTO dto = IncluirNormaCommandDTO.builder()
				                                               .codigo("NR001")
				                                               .descricao("Norma A")
				                                               .build();
		
		this.mockMvc.perform(MockMvcRequestBuilders.post(NormaController.PATH)
				    .contentType(MediaType.APPLICATION_JSON_VALUE)
				    .content(TestUtils.objectToJson(dto)))
				    .andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void deveExcluirNorma() throws Exception{
		
		NormaId normaId = NormaId.generate();
				
		normaRepository.insert(Norma.builder()
				                    .id(normaId)
				                    .codigo("NR001")
				                    .descricao("Norma A").build());
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete(NormaController.PATH + "/" + normaId.toString())
				    .contentType(MediaType.APPLICATION_JSON_VALUE))
				    .andExpect(status().is2xxSuccessful());
		
	}
	
	@Test
	public void deveBuscarTodosRegistros() throws Exception{
		
		normaRepository.insert(Norma.builder()
                .id(NormaId.generate())
                .codigo("NR001")
                .descricao("Norma A").build());
		
		normaRepository.insert(Norma.builder()
                .id(NormaId.generate())
                .codigo("NR002")
                .descricao("Norma B").build());
		
		normaRepository.insert(Norma.builder()
                .id(NormaId.generate())
                .codigo("NR003")
                .descricao("Norma C").build());
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(NormaController.PATH + "/")
				    .contentType(MediaType.APPLICATION_JSON_VALUE))
				    .andExpect(status().is2xxSuccessful());
	}
}
