package org.tcc.gestao.normas.core.api.dto;

import java.io.Serializable;

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
@ApiModel(description = "Informações do item executado em um processo de auditoria.")
public class ItemExecucaoAuditoriaCommandDTO implements Serializable {

    private static final long serialVersionUID = 7306498409129620432L;
    
    @NotNull(message = "Norma do item de execucao não deve ser nulo!!")
    @ApiModelProperty(value="Norma do item da execucao de um processo", required = true)
	private String norma;
    
    @NotNull(message = "Situação do item de execucao não deve ser nulo!!")
    @ApiModelProperty(value="Situação do item da execucao de um processo", required = true)
	private boolean aprovado;
	
}
