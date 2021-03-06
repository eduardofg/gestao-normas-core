package org.tcc.gestao.normas.core.domain.model;

import java.util.UUID;

public abstract class UniqueId {

    private final UUID value;

    protected UniqueId(final UUID value) {

        if (value == null) {
            throw new NullPointerException();
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}