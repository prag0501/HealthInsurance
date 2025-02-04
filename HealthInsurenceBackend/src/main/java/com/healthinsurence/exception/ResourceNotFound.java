package com.healthinsurence.exception;

@SuppressWarnings("serial")
public class ResourceNotFound extends RuntimeException {

	public ResourceNotFound(String msg)
	{
		super(msg);
	}

}
