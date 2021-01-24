package org.tcc.gestao.normas.core.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tcc.gestao.normas.core.api.dto.ExcluirNormaCommandDTO;
import org.tcc.gestao.normas.core.api.dto.IncluirNormaCommandDTO;
import org.tcc.gestao.normas.core.api.dto.NormaDTO;
import org.tcc.gestao.normas.core.application.NormaApplicationService;
import org.tcc.gestao.normas.core.common.ValidationUtils;
import org.tcc.gestao.normas.core.domain.model.NormaDomainRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = NormaController.PATH, produces = { MediaType.APPLICATION_JSON_VALUE })
public class NormaController {

	public static final String PATH = "/api/v1/normas";
	
    @Autowired
    private NormaDomainRepository normaRepository;
    
    @Autowired
    private NormaApplicationService normaService;

    @ApiOperation(value = "Retorna uma lista de normas")
    @GetMapping(path = "/")
    public List<NormaDTO> findAll() {
    	
        return normaRepository.findAll()
                .stream()
                .map(norma -> new NormaDTO(norma.getId().toString(), norma.getCodigo(), norma.getDescricao()))
                .collect(Collectors.toList());
    }
    
    @ApiOperation(value = "Inclui uma nova norma")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Norma criada."),
    @ApiResponse(code = 400, message = "A norma não pode ser criada porque está em um estado inválido.") })
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public String incluir(@RequestBody IncluirNormaCommandDTO dto) {

    	ValidationUtils.assertIntegrity(dto);
    	
        return normaService.handle(dto);
    }
   
    @ApiOperation(value = "Exclui uma norma")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Exclusão do norma efetuada.")})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void>  excluir(@PathVariable String id) {
	
    	ExcluirNormaCommandDTO dto = ExcluirNormaCommandDTO.builder().id(id).build();
    	
    	ValidationUtils.assertIntegrity(dto);
    	
    	normaService.handle(dto);
	
		return ResponseEntity.ok().build();
	}
}