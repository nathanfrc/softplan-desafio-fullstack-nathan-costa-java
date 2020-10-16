package com.softplan.procesos.api.responses;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

	private T content;
	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	private List<String> errors;

	public Response() {
	}

	
	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
