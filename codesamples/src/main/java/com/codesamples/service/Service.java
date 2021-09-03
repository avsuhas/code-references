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

	public Response someServiceImpl(String eventType) {

		Response response = new Response();
		response.setIdentefier(UUID.randomUUID().toString());
		response.setStatusMessage("event pending");
		
		if(eventType != null) {
			if(eventType.contains("EVENT_TYPE_RENDER")) {
				response.setDisplay(false);
			}
			if(eventType.contains("EVENT_TYPE_PRINT")) {
				response.setPrint(false);
			}
			if(eventType.contains("EVENT_TYPE_ARCHIVE")) {
				response.setStored(false);
			}
					
		}
		return response;
	}

}
