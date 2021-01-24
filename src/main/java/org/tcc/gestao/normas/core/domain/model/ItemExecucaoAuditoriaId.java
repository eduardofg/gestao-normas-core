package org.tcc.gestao.normas.core.domain.model;

import java.util.UUID;

public class ItemExecucaoAuditoriaId extends UniqueId {

    protected ItemExecucaoAuditoriaId(UUID value) {
        super(value);
    }

    public static ItemExecucaoAuditoriaId generate() {
        return new ItemExecucaoAuditoriaId(UUID.randomUUID());
    }

    public static ItemExecucaoAuditoriaId fromString(String value) {
        return value == null ? null : new ItemExecucaoAuditoriaId(UUID.fromString(value));
    }
}
