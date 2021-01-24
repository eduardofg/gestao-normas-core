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
import org.tcc.gestao.normas.core.api.dto.ExcluirProcessoAuditoriaCommandDTO;
import org.tcc.gestao.normas.core.api.dto.IncluirProcessoAuditoriaCommandDTO;
import org.tcc.gestao.normas.core.api.dto.ProcessoAuditoriaDTO;
import org.tcc.gestao.normas.core.application.ProcessoAuditoriaApplicationService;
import org.tcc.gestao.normas.core.common.ValidationUtils;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoriaDomainRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = ProcessoAuditoriaController.PATH, produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProcessoAuditoriaController {

	public static final String PATH = "/api/v1/processos";
	
    @Autowired
    private ProcessoAuditoriaDomainRepository processoRepository;
    
    @Autowired
    private ProcessoAuditoriaApplicationService processoService;

    @ApiOperation(value = "Retorna uma lista de processos")
    @GetMapping(path = "/")
    public List<ProcessoAuditoriaDTO> findAll() {
    	
        return processoRepository.findAll()
                .stream()
                .map(processo -> new ProcessoAuditoriaDTO(processo.getId().toString(), 
                		                                  processo.getDescricao(),
                		                                  processo.getNormas().stream().map(norma -> norma.toString()).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }
    
    @ApiOperation(value = "Inclui um novo processo de auditoria")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Processo de auditoria criado."),
    @ApiResponse(code = 400, message = "O processo de auditoria não pode ser criadoa porque está em um estado inválido.") })
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public String incluir(@RequestBody IncluirProcessoAuditoriaCommandDTO dto) {

    	ValidationUtils.assertIntegrity(dto);
    	
        return processoService.handle(dto);
    }
   
    @ApiOperation(value = "Exclui um processo de auditoria")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Exclusão do processo de auditoria efetuado.")})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void>  excluir(@PathVariable String id) {
	
    	ExcluirProcessoAuditoriaCommandDTO dto = ExcluirProcessoAuditoriaCommandDTO.builder().id(id).build();
    	
    	ValidationUtils.assertIntegrity(dto);
    	
    	processoService.handle(dto);
	
		return ResponseEntity.ok().build();
	}
}