package org.tcc.gestao.normas.core.common.exception;

public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 1656288650486468453L;

	public InternalServerErrorException() {
        super();
    }
	
	@Override
    public String getLocalizedMessage() {
        return "Um erro interno ocorreu no processamento desta requisição! Contate o administrador.";
    }
}