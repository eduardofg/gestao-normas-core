package org.tcc.gestao.normas.core.infra;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "item_execucao_auditoria")
public class ItemExecucaoAuditoriaModel {

    @Id
    @NotNull
    private String id;
    
    @ManyToOne
    private NormaModel norma;
    
    @NotNull
    @Column(name = "aprovado")
    private boolean aprovado;
    
}
