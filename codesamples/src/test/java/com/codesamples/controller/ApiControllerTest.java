package com.codesamples.controller;




import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.codesamples.model.Rdata;
import com.codesamples.model.Request;
import com.codesamples.model.Response;
import com.codesamples.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ApiControllerTest {
	
	private static final String URI = "/form";
	
	@Mock
	private Service service;
	
	@InjectMocks
	private ApiController controller;
	
	private MockMvc mockMvc;
	
	private Response response;
	

	@Before
	public void setupMockMvc() {
		MockitoAnnotations.initMocks(this);
		
		response = new Response();
		response.setIdentefier("123");
		response.setDisplay(false);
		response.setPrint(false);
		response.setStored(false);
		
		mockMvc = standaloneSetup(controller).setControllerAdvice(new ControllerExceptionHandler()).build();
	}

	@Test
	public void testHandleAPIrequest() throws Exception {
		doReturn(response).when(service).someServiceImpl(anyString());
		
		Request request = createRequestBodyObject();
		String jsonRequest = createJsonFromObject(request);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post(URI)
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void testDocumentIdNotEmpyt() throws Exception {
		
		Request request = createRequestBodyObject();
		request.setDepartmentId(null);
		String jsonRequest = createJsonFromObject(request);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post(URI)
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().string("{\"code\":400,\"message\":\"departmentId in the request body cannot be empty\"}"));
	}


	private Request createRequestBodyObject() {
		
		Request request = new Request();
		request.setDepartmentId("D00015");
		request.setEventType("r_P_a");
		request.setStoreId("S10088");
		
		Rdata data = new Rdata();
		data.setKey1("value1");
		data.setKey2("value2");
		request.setRdata(data);
		
		return request;
	}
	
	private String createJsonFromObject(Request request) {
		
		ObjectMapper obj = new ObjectMapper();
		String jsonStr = "";
		
		try {
			jsonStr = obj.writeValueAsString(request);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

}
