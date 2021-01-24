package org.tcc.gestao.normas.core.domain.model;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExecucaoAuditoria {

	private final ExecucaoAuditoriaId id;

	private String descricao;
	
	private ProcessoAuditoriaId processo;
	
	private Set<ItemExecucaoAuditoria> itens;
	
}
