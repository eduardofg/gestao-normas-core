package org.tcc.gestao.normas.core.domain.model;

import java.util.List;

public interface NormaDomainRepository {

	public List<Norma> findAll();
	
	public String insert(final Norma norma);

	public void delete(final String id);
	
	public Norma get(final String id);
}
