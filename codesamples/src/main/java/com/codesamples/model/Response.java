package com.codesamples.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response extends RepresentationModel<Response> {
	
	private String identefier;
	private String statusMessage;
	private Boolean display;
	private Boolean print;
	private Boolean stored;
	

}
