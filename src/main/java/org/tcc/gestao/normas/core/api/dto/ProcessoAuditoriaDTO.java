package org.tcc.gestao.normas.core.api.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
public class ProcessoAuditoriaDTO implements Serializable {

	private static final long serialVersionUID = 2127450209312210568L;

	private String id;
	
	private String descricao;
	
	private Set<String> normas;
	
}
