package org.tcc.gestao.normas.core.domain.model;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProcessoAuditoria {

	private final ProcessoAuditoriaId id;
	
	private String descricao;

	private Set<NormaId> normas;
	
}
