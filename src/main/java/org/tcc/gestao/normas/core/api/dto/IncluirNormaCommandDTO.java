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
@ApiModel(description = "Informações para cadastro de uma norma.")
public class IncluirNormaCommandDTO implements Serializable {

    private static final long serialVersionUID = 7306498409129620432L;
	
    @NotNull(message = "Código de uma norma!")
    @ApiModelProperty(value="Código único de uma norma", required = true)
	private String codigo;
    
    @NotNull(message = "Descrição não deve ser nulo!")
    @ApiModelProperty(value="Descrição de uma norma", required = true)
	private String descricao;
	
}
