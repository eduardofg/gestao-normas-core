package org.tcc.gestao.normas.core.common.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

public class IntegrityException extends IllegalArgumentException {

    private Set<? extends ConstraintViolation<?>> violations;

    private static final long serialVersionUID = 5471545598335027351L;

    public IntegrityException(Set<? extends ConstraintViolation<?>> violations) {
        super();
        this.violations = violations;
    }

    @Override
    public String getMessage() {
        return violations.stream().map(violation -> this.parseViolation(violation)).reduce("", String::concat);
    }

    private String parseViolation(final ConstraintViolation<?> violation) {

        List<Object> arguments = new ArrayList<>();

        arguments.add(violation.getInvalidValue());

        violation.getConstraintDescriptor().getAttributes().forEach((key, value) -> {
            if (!key.equals("message") && !key.equals("groups") && !key.equals("payload")) {
                arguments.add(value);
            }
        });

        return new StringBuilder(" - ")
                .append(violation.getMessage())
                .append("\n").toString();
    }

}