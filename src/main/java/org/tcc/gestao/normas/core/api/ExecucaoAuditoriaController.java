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
import org.tcc.gestao.normas.core.api.dto.ExcluirExecucaoAuditoriaCommandDTO;
import org.tcc.gestao.normas.core.api.dto.ExecucaoAuditoriaDTO;
import org.tcc.gestao.normas.core.api.dto.ExecutarAuditoriaCommandDTO;
import org.tcc.gestao.normas.core.api.dto.ItemExecucaoAuditoriaDTO;
import org.tcc.gestao.normas.core.application.ExecucaoAuditoriaApplicationService;
import org.tcc.gestao.normas.core.common.ValidationUtils;
import org.tcc.gestao.normas.core.domain.model.ExecucaoAuditoriaDomainRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = ExecucaoAuditoriaController.PATH, produces = { MediaType.APPLICATION_JSON_VALUE })
public class ExecucaoAuditoriaController {

	public static final String PATH = "/api/v1/executar";
	
    @Autowired
    private ExecucaoAuditoriaDomainRepository execucaoRepository;
    
    @Autowired
    private ExecucaoAuditoriaApplicationService execucaoService;

    @ApiOperation(value = "Retorna uma lista de execucoes")
    @GetMapping(path = "/")
    public List<ExecucaoAuditoriaDTO> findAll() {
    	
        return execucaoRepository.findAll()
                .stream()
                .map(execucao -> new ExecucaoAuditoriaDTO(execucao.getId().toString(), 
                		                                  execucao.getDescricao(),
                		                                  execucao.getItens().stream().map(item -> new ItemExecucaoAuditoriaDTO(item.getNorma().toString(), item.isAprovado())).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }
    
    @ApiOperation(value = "Executa um processo de auditoria")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Processo de auditoria executado."),
    @ApiResponse(code = 400, message = "A execução não pode ser efetuada porque está em um estado inválido.") })
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public String executar(@RequestBody ExecutarAuditoriaCommandDTO dto) {

    	ValidationUtils.assertIntegrity(dto);
    	
        return execucaoService.handle(dto);
    }
   
    @ApiOperation(value = "Exclui uma execucao de um processo de auditoria")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Exclusão da execução do processo de auditoria efetuado.")})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void>  excluir(@PathVariable String id) {
	
    	ExcluirExecucaoAuditoriaCommandDTO dto = ExcluirExecucaoAuditoriaCommandDTO.builder().id(id).build();
    	
    	ValidationUtils.assertIntegrity(dto);
    	
    	execucaoService.handle(dto);
	
		return ResponseEntity.ok().build();
	}
}