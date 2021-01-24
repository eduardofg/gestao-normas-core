package org.tcc.gestao.normas.core.infra;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "processo_auditoria")
public class ProcessoAuditoriaModel {

    @Id
    @NotNull
    private String id;
    
    @NotNull
    @Column(name = "descricao")
    private String descricao;
    
    @ManyToMany
    private Set<NormaModel> normas;
    
}
