package org.tcc.gestao.normas.core.common.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 8019126960069690582L;

	private String message; 
	
	public BusinessException(String message) {
        super();
        this.message = message;
    }
	
	public BusinessException() {
        super();
    }
	
	@Override
    public String getLocalizedMessage() {
        return this.message;
    }
}