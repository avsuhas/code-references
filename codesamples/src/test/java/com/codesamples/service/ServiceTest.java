package com.codesamples.service;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.codesamples.model.Response;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
	
	@InjectMocks
	private Service service;
	
	
	@Test
	public void testSomeServiceImpl() {
		Response response = service.someServiceImpl("EVENT_TYPE_RENDER");
		response.setDisplay(false);
		assertFalse(response.getDisplay());
		
	}

}
