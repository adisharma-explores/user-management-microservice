package com.ecommerce.user_management.Exceptions;


public class InvalidPasswordException extends Exception{
	InvalidPasswordException(String msg)
	{
		super(msg);
	}
}