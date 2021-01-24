package org.tcc.gestao.normas.core.common.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8019126960069690582L;

	private String message; 
	
	public NotFoundException(String message) {
        super();
        this.message = message;
    }
	
	public NotFoundException() {
        super();
    }
	
	@Override
    public String getLocalizedMessage() {
        return this.message;
    }
}