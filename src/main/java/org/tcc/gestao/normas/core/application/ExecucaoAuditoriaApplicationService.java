package org.tcc.gestao.normas.core.application;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tcc.gestao.normas.core.api.dto.ExcluirExecucaoAuditoriaCommandDTO;
import org.tcc.gestao.normas.core.api.dto.ExecutarAuditoriaCommandDTO;
import org.tcc.gestao.normas.core.domain.model.ExecucaoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ExecucaoAuditoriaDomainRepository;
import org.tcc.gestao.normas.core.domain.model.ExecucaoAuditoriaId;
import org.tcc.gestao.normas.core.domain.model.ItemExecucaoAuditoria;
import org.tcc.gestao.normas.core.domain.model.ItemExecucaoAuditoriaDomainRepository;
import org.tcc.gestao.normas.core.domain.model.ItemExecucaoAuditoriaId;
import org.tcc.gestao.normas.core.domain.model.NormaId;
import org.tcc.gestao.normas.core.domain.model.ProcessoAuditoriaId;

@Service
public class ExecucaoAuditoriaApplicationService {

	@Autowired
    private ExecucaoAuditoriaDomainRepository execucaoRepository;
	
	@Autowired
    private ItemExecucaoAuditoriaDomainRepository itemExecucaoRepository;

    public String handle(final ExecutarAuditoriaCommandDTO cmd) {

    	
    	ExecucaoAuditoria execucao = ExecucaoAuditoria.builder()
    			                                      .id(ExecucaoAuditoriaId.generate())
    			                                      .processo(ProcessoAuditoriaId.fromString(cmd.getProcesso()))
    			                                      .descricao(cmd.getDescricao())
    			                                      .itens(cmd.getItens().stream().map(item -> ItemExecucaoAuditoria.builder()
															    			                                  	        .id(ItemExecucaoAuditoriaId.generate())
															    			                                	        .norma(NormaId.fromString(item.getNorma()))
															    			                                	        .aprovado(item.isAprovado())
															    			                                	        .build()).collect(Collectors.toSet()))
    			                                      .build();
        	
    	execucao.getItens().forEach(item -> itemExecucaoRepository.insert(item));

        return execucaoRepository.insert(execucao);
    }
    
    public void handle(final ExcluirExecucaoAuditoriaCommandDTO cmd) {

    	execucaoRepository.delete(cmd.getId());
    }
}
