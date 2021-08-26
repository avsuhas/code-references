package com.codesamples.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.codesamples.service.Service;
import com.codesamples.model.Request;
import com.codesamples.model.Response;

/**
 * @author suhas
 *  code sample : To validate parameters in the request. 
 */


// This annotation is required to activate @Valid annotation in the method level of @RequestBody
@Validated
@RestController
public class ApiController {
	
	
	@Autowired
	private Service service;

	
	@PostMapping(path= "/form",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> handleAPIrequest(@RequestBody @Valid Request requestPayload, 
													@RequestHeader HttpHeaders headers) {
		
		Response responseEntity = service.someServiceImpl(requestPayload);

		//Add the HATEOS links
		Link link = linkTo(methodOn(ApiController.class)
				.handleAPIrequest(requestPayload, headers)).withSelfRel()
				.withName("postForm").withType(MediaType.APPLICATION_JSON_VALUE);
		
		responseEntity.add(link);
		
		Link statusLink = linkTo(ApiController.class)
				.slash("status/"+ responseEntity.getIdentefier())
				.withRel("status").withType(MediaType.APPLICATION_JSON_VALUE).withName("getStatus");
		
		responseEntity.add(statusLink);
		
		return new ResponseEntity<>(responseEntity, HttpStatus.CREATED);
		
	}
}
