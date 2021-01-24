package org.tcc.gestao.normas.core.infra;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tcc.gestao.normas.core.domain.model.ExecucaoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ExecucaoAuditoriaId;
import org.tcc.gestao.normas.core.domain.model.ItemExecucaoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoriaId;

@Component
public class ExecucaoAuditoriaConverter {
		
	@Lazy
    @Autowired
    private ConversionService conversionService;
	
    @Component
    public class ExecucaoAuditoriaModelToEntity implements Converter<ExecucaoAuditoriaModel, ExecucaoAuditoria> {

    	@Override
        public ExecucaoAuditoria convert(final ExecucaoAuditoriaModel source) {

            return ExecucaoAuditoria.builder().id(ExecucaoAuditoriaId.fromString(source.getId()))
            		                          .descricao(source.getDescricao())
            		                          .processo(ProcessoAuditoriaId.fromString(source.getProcesso().getId()))
            		                          .itens(source.getItens().stream().map(item -> conversionService.convert(item, ItemExecucaoAuditoria.class)).collect(Collectors.toSet()))
            		                          .build();
        }
    	
    }

    @Component
    public class ExecucaoAuditoriaEntityToModel implements Converter<ExecucaoAuditoria, ExecucaoAuditoriaModel> {

    	@Autowired
        private ProcessoAuditoriaModelRepository repository;
    	
        @Override
        @Transactional(readOnly = true)
        public ExecucaoAuditoriaModel convert(final ExecucaoAuditoria source) {
        	
            return ExecucaoAuditoriaModel.builder()
            		                   .id(source.getId().toString())
            		                   .descricao(source.getDescricao())
            		                   .processo(conversionService.convert(repository.getOne(source.getProcesso().toString()), ProcessoAuditoriaModel.class))
            		                   .itens(source.getItens().stream().map(item -> conversionService.convert(item, ItemExecucaoAuditoriaModel.class)).collect(Collectors.toSet()))
            		                   .build();
        }
    }
    
}
