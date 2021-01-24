package org.tcc.gestao.normas.core.domain.model;

import java.util.List;

public interface ItemExecucaoAuditoriaDomainRepository {

	public List<ItemExecucaoAuditoria> findAll();
	
	public String insert(final ItemExecucaoAuditoria execucao);

	public void delete(final String id);
}
