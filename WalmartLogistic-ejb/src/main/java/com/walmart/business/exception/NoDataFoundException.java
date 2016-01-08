package com.walmart.business.exception;

import javax.ejb.ApplicationException;

import com.walmart.exception.BusinessException;

@ApplicationException(rollback=true)
public class NoDataFoundException extends BusinessException {

	private static final long serialVersionUID = 5077420954080626074L;

	public NoDataFoundException(String registro) {
		super(registro + " nao encontrado.");		
	}
}
