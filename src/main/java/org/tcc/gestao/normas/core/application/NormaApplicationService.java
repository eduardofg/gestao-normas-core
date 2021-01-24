package org.tcc.gestao.normas.core.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tcc.gestao.normas.core.api.dto.ExcluirNormaCommandDTO;
import org.tcc.gestao.normas.core.api.dto.IncluirNormaCommandDTO;
import org.tcc.gestao.normas.core.domain.model.Norma;
import org.tcc.gestao.normas.core.domain.model.NormaDomainRepository;
import org.tcc.gestao.normas.core.domain.model.NormaId;

@Service
public class NormaApplicationService {

	@Autowired
    private NormaDomainRepository normaRepository;

    public String handle(final IncluirNormaCommandDTO cmd) {

    	Norma norma = Norma.builder()
    			                 .id(NormaId.generate())
				                 .codigo(cmd.getCodigo())
				                 .descricao(cmd.getDescricao()).build();
        		
        return normaRepository.insert(norma);
    }
    
    public void handle(final ExcluirNormaCommandDTO cmd) {

    	normaRepository.delete(cmd.getId());
    }
}
