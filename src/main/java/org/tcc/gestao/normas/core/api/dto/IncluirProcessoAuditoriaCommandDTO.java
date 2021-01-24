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
@ApiModel(description = "Informações para cadastro de um processo de auditoria.")
public class IncluirProcessoAuditoriaCommandDTO implements Serializable {

    private static final long serialVersionUID = 7306498409129620432L;
	
    @NotNull(message = "Descrição do processo não deve ser nulo!!")
    @ApiModelProperty(value="Descrição de um processo", required = true)
	private String descricao;
    
    @NotNull(message = "Lista de normas não deve ser nulo!!")
    @ApiModelProperty(value="Lista de normas de um processo", required = true)
	private Set<String> normas;
	
}
