package org.tcc.gestao.normas.core.infra;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoriaDomainRepository;

@Repository
public class ProcessoAuditoriaRepository implements ProcessoAuditoriaDomainRepository  {

    @Autowired
    private ProcessoAuditoriaModelRepository repository;

    @Lazy
    @Autowired
    private ConversionService conversionService;
    
    public String insert(final ProcessoAuditoria processo) {
        
    	repository.save(conversionService.convert(processo, ProcessoAuditoriaModel.class));
    	
    	return processo.getId().toString();
    }
    
    public void delete(final String id) {
    	repository.deleteById(id);
    }
    
    public List<ProcessoAuditoria> findAll() {
        return repository.findAll().stream().map(processo -> conversionService.convert(processo, ProcessoAuditoria.class)).collect(Collectors.toList());
    }
}
