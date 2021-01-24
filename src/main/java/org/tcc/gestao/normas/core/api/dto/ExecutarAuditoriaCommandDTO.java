package org.tcc.gestao.normas.core.api.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Informações para executar um processo de auditoria.")
public class ExecutarAuditoriaCommandDTO implements Serializable {

    private static final long serialVersionUID = 7306498409129620432L;
	
    @NotNull(message = "Descrição da execução do processo não deve ser nulo!!")
    @ApiModelProperty(value="Descrição da execucao de um processo", required = true)
	private String descricao;
    
    @NotNull(message = "Processo de auditoria não deve ser nulo!!")
    @ApiModelProperty(value="Processo de auditoria relacionado a execução", required = true)
	private String processo;
    
    @NotNull(message = "Lista de itens da execucao não deve ser nulo!!")
    @ApiModelProperty(value="Lista de itens da execucao de um processo", required = true)
	private Set<ItemExecucaoAuditoriaCommandDTO> itens;
	
}
