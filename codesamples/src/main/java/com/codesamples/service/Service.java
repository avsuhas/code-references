package com.codesamples.service;

import java.util.UUID;

import org.springframework.stereotype.Component;


import com.codesamples.model.Response;


/**
 * @author suhas
 *
 */
@Component
public class Service {

	public Response someServiceImpl(String string) {

		Response response = new Response();
		response.setIdentefier(UUID.randomUUID().toString());
		response.setStatusMessage("event pending");
		return response;
	}

}
