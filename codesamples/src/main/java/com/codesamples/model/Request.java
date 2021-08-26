package com.codesamples.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Request {

	@NotNull(message ="departmentId in the request body cannot be empty")
	private String departmentId;	
	private String eventType;
	@NotNull(message ="storeId in the request body cannot be empty")
	private String storeId;
	@NotNull(message ="Rdata in the request body cannot be empty")
	private Rdata rdata; 
	private List<String> rForms;

	
}
