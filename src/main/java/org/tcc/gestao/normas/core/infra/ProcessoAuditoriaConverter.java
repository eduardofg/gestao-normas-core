package org.tcc.gestao.normas.core.infra;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tcc.gestao.normas.core.domain.model.NormaId;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoriaId;

@Component
public class ProcessoAuditoriaConverter {
		
	@Lazy
    @Autowired
    private ConversionService conversionService;
	
    @Component
    public class ProcessoAuditoriaModelToEntity implements Converter<ProcessoAuditoriaModel, ProcessoAuditoria> {

        @Override
        public ProcessoAuditoria convert(final ProcessoAuditoriaModel source) {
            return ProcessoAuditoria.builder().id(ProcessoAuditoriaId.fromString(source.getId()))
            		                          .descricao(source.getDescricao())
            		                          .normas(source.getNormas().stream().map(norma -> NormaId.fromString(norma.getId())).collect(Collectors.toSet()))
            		                          .build();
        }
    }

    @Component
    public class ProcessoAuditoriaEntityToModel implements Converter<ProcessoAuditoria, ProcessoAuditoriaModel> {

    	@Autowired
        private NormaModelRepository repository;
    	
        @Override
        @Transactional(readOnly = true)
        public ProcessoAuditoriaModel convert(final ProcessoAuditoria source) {
        	
            return ProcessoAuditoriaModel.builder().id(source.getId().toString())
            		                   .descricao(source.getDescricao())
            		                   .normas(source.getNormas().stream().map(norma -> conversionService.convert(repository.getOne(norma.toString()), NormaModel.class)).collect(Collectors.toSet()))
            		                   .build();
        }
    }

}
