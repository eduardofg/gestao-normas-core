package org.tcc.gestao.normas.core.infra;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Entity
@Table(name = "execucao_auditoria")
public class ExecucaoAuditoriaModel {

    @Id
    @NotNull
    private String id;
    
    @NotNull
    @Column(name = "descricao")
    private String descricao;
    
    @ManyToOne
    private ProcessoAuditoriaModel processo;
    
    @OneToMany
    private Set<ItemExecucaoAuditoriaModel> itens;
    
}
