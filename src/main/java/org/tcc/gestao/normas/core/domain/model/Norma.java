package org.tcc.gestao.normas.core.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Norma {

	private final NormaId id;

	private String codigo;
	
	private String descricao;
	
}
