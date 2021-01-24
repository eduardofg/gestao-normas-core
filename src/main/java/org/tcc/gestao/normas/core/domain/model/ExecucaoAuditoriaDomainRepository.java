package org.tcc.gestao.normas.core.domain.model;

import java.util.List;

public interface ExecucaoAuditoriaDomainRepository {

	public List<ExecucaoAuditoria> findAll();
	
	public String insert(final ExecucaoAuditoria execucao);

	public void delete(final String id);
}
