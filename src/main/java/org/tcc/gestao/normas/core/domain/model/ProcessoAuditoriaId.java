package org.tcc.gestao.normas.core.domain.model;

import java.util.UUID;

public class ProcessoAuditoriaId extends UniqueId {

    protected ProcessoAuditoriaId(UUID value) {
        super(value);
    }

    public static ProcessoAuditoriaId generate() {
        return new ProcessoAuditoriaId(UUID.randomUUID());
    }

    public static ProcessoAuditoriaId fromString(String value) {
        return value == null ? null : new ProcessoAuditoriaId(UUID.fromString(value));
    }
}
