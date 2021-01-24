package org.tcc.gestao.normas.core.infra;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;
import org.tcc.gestao.normas.core.domain.model.ItemExecucaoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ItemExecucaoAuditoriaDomainRepository;

@Repository
public class ItemExecucaoAuditoriaRepository implements ItemExecucaoAuditoriaDomainRepository  {

    @Autowired
    private ItemExecucaoAuditoriaModelRepository repository;

    @Lazy
    @Autowired
    private ConversionService conversionService;
    
    public String insert(final ItemExecucaoAuditoria itemExecucao) {
    	
    	repository.save(conversionService.convert(itemExecucao, ItemExecucaoAuditoriaModel.class));
    	
    	return itemExecucao.getId().toString();
    }
    
    public void delete(final String id) {
    	repository.deleteById(id);
    }
    
    public List<ItemExecucaoAuditoria> findAll() {
        return repository.findAll().stream().map(itemExecucao -> conversionService.convert(itemExecucao, ItemExecucaoAuditoria.class)).collect(Collectors.toList());
    }
}
