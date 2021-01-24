package org.tcc.gestao.normas.core.domain.model;

import java.util.UUID;

public class ExecucaoAuditoriaId extends UniqueId {

    protected ExecucaoAuditoriaId(UUID value) {
        super(value);
    }

    public static ExecucaoAuditoriaId generate() {
        return new ExecucaoAuditoriaId(UUID.randomUUID());
    }

    public static ExecucaoAuditoriaId fromString(String value) {
        return value == null ? null : new ExecucaoAuditoriaId(UUID.fromString(value));
    }
}
