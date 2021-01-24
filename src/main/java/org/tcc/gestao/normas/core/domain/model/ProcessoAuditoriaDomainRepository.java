package org.tcc.gestao.normas.core.domain.model;

import java.util.List;

public interface ProcessoAuditoriaDomainRepository {

	public List<ProcessoAuditoria> findAll();
	
	public String insert(final ProcessoAuditoria processo);

	public void delete(final String id);
}
