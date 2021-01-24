package org.tcc.gestao.normas.core.domain.model;

import java.util.UUID;

public class NormaId extends UniqueId {

    protected NormaId(UUID value) {
        super(value);
    }

    public static NormaId generate() {
        return new NormaId(UUID.randomUUID());
    }

    public static NormaId fromString(String value) {
        return value == null ? null : new NormaId(UUID.fromString(value));
    }
}
