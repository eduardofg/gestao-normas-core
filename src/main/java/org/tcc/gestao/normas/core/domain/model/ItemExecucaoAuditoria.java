package org.tcc.gestao.normas.core.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemExecucaoAuditoria {

	private final ItemExecucaoAuditoriaId id;

	private NormaId norma;
	
	private boolean aprovado;
	
}
