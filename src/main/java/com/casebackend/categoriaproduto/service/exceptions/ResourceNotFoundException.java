package com.casebackend.categoriaproduto.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object codigo) {
		super("Resource not found. Codigo " + codigo );
	}
}
