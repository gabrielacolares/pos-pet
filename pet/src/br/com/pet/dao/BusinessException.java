package br.com.pet.dao;

public class BusinessException extends RuntimeException{

	public BusinessException(String string) {
		super(string);
	}
	
	public static final long serialVersionUID = 1L;

}
