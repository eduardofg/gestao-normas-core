package org.tcc.gestao.normas.core.application;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tcc.gestao.normas.core.api.dto.ExcluirProcessoAuditoriaCommandDTO;
import org.tcc.gestao.normas.core.api.dto.IncluirProcessoAuditoriaCommandDTO;
import org.tcc.gestao.normas.core.domain.model.NormaId;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoriaDomainRepository;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoriaId;

@Service
public class ProcessoAuditoriaApplicationService {

	@Autowired
    private ProcessoAuditoriaDomainRepository processoRepository;

    public String handle(final IncluirProcessoAuditoriaCommandDTO cmd) {

    	ProcessoAuditoria processo = ProcessoAuditoria.builder()
    			                 .id(ProcessoAuditoriaId.generate())
    			                 .descricao(cmd.getDescricao())
    			                 .normas(cmd.getNormas().stream().map(norma -> NormaId.fromString(norma)).collect(Collectors.toSet()))
				                 .build();
        		
        return processoRepository.insert(processo);
    }
    
    public void handle(final ExcluirProcessoAuditoriaCommandDTO cmd) {

    	processoRepository.delete(cmd.getId());
    }
}
