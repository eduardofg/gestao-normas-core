package org.tcc.gestao.normas.core.infra;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;
import org.tcc.gestao.normas.core.domain.model.ExecucaoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ExecucaoAuditoriaDomainRepository;

@Repository
public class ExecucaoAuditoriaRepository implements ExecucaoAuditoriaDomainRepository  {

    @Autowired
    private ExecucaoAuditoriaModelRepository repository;

    @Lazy
    @Autowired
    private ConversionService conversionService;
    
    public String insert(final ExecucaoAuditoria execucao) {
        
    	repository.save(conversionService.convert(execucao, ExecucaoAuditoriaModel.class));
    	
    	return execucao.getId().toString();
    }
    
    public void delete(final String id) {
    	repository.deleteById(id);
    }
    
    public List<ExecucaoAuditoria> findAll() {
        return repository.findAll().stream().map(execucao -> conversionService.convert(execucao, ExecucaoAuditoria.class)).collect(Collectors.toList());
    }
}
