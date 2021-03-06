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
@ApiModel(description = "Informações para excluir uma norma.")
public class ExcluirNormaCommandDTO implements Serializable {

	private static final long serialVersionUID = -8913388635740334026L;
	
	@NotNull(message = "ID não deve ser nulo!")
	@ApiModelProperty(value="ID da norma a ser excluído.", required = true)
	private String id;
}
