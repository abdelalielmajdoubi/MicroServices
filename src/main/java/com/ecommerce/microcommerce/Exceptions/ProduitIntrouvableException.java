package com.ecommerce.microcommerce.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProduitIntrouvableException extends RuntimeException {
	public ProduitIntrouvableException(String s) 
	   {
	      super(s);
	   }

}
