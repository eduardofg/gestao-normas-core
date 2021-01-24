package org.tcc.gestao.normas.core.infra;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tcc.gestao.normas.core.domain.model.Norma;
import org.tcc.gestao.normas.core.domain.model.NormaDomainRepository;

@Repository
public class NormaRepository implements NormaDomainRepository  {

    @Autowired
    private NormaModelRepository repository;

    @Autowired
    private ConversionService conversionService;

    @Transactional(readOnly = true)
    public Norma get(final String id) {
        return conversionService.convert(repository.getOne(id), Norma.class);
    }
    
    public String insert(final Norma norma) {
        
    	repository.save(conversionService.convert(norma, NormaModel.class));
    	
    	return norma.getId().toString();
    }
    
    public void delete(final String id) {
    	repository.deleteById(id);
    }
    
    public List<Norma> findAll() {
        return repository.findAll().stream().map(norma -> conversionService.convert(norma, Norma.class)).collect(Collectors.toList());
    }
}
