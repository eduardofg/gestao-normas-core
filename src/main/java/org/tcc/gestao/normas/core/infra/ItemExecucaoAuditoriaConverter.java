package org.tcc.gestao.normas.core.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tcc.gestao.normas.core.domain.model.ItemExecucaoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ItemExecucaoAuditoriaId;
import org.tcc.gestao.normas.core.domain.model.NormaId;

@Component
public class ItemExecucaoAuditoriaConverter {
		
	@Lazy
    @Autowired
    private ConversionService conversionService;
	
    @Component
    public class ItemExecucaoAuditoriaModelToEntity implements Converter<ItemExecucaoAuditoriaModel, ItemExecucaoAuditoria> {

        @Override
        public ItemExecucaoAuditoria convert(final ItemExecucaoAuditoriaModel source) {
        	
            return ItemExecucaoAuditoria.builder().id(ItemExecucaoAuditoriaId.fromString(source.getId()))
			                                	   .norma(NormaId.fromString(source.getNorma().getId()))
			                                	   .aprovado(source.isAprovado())
			                                	   .build();
        }
    }

    @Component
    public class ItemExecucaoAuditoriaEntityToModel implements Converter<ItemExecucaoAuditoria, ItemExecucaoAuditoriaModel> {

    	@Autowired
        private NormaModelRepository repository;
    	
        @Override
        @Transactional(readOnly = true)
        public ItemExecucaoAuditoriaModel convert(final ItemExecucaoAuditoria source) {
        	
            return ItemExecucaoAuditoriaModel.builder()
            		                   .id(source.getId().toString())
            		                   .aprovado(source.isAprovado())
            		                   .norma(repository.getOne(source.getNorma().toString()))		                                            
            		                   .build();
        }
    }

}
