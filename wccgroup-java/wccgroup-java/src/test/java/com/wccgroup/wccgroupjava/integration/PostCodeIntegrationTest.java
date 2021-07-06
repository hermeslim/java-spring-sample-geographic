package com.wccgroup.wccgroupjava.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.wccgroup.wccgroupjava.dto.PostCodeReqDTO;
import com.wccgroup.wccgroupjava.dto.PostCodeResDTO;
import com.wccgroup.wccgroupjava.service.PostCodeService;

/**
 * 
 * @author Hermes
 *
 * test integration of the get method in post code controller
 * 
 */
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PostCodeIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PostCodeService mockService;

	/**
	 * unit test basic auth response 200 OK and retrieveDistrance controller
	 * test contentType equal to application/json
	 * test expected json value
	 * @throws Exception
	 */
	@Test
	@WithMockUser()
	public void testIntegrationController() throws Exception {
	PostCodeReqDTO request =  new PostCodeReqDTO();
	request.setPostCode1("AA");
	request.setPostCode2("AB");
	
	PostCodeResDTO response = new PostCodeResDTO();
	response.setPostCode1("AA");
	
	
	when(mockService.retrieveDistance(request)).thenReturn(Optional.of(response));
	mvc.perform(get("/postCode")
			.content("{ \"postCode1\" : \"AA\",\r\n"
					+ "    \"postCode2\" : \"AB\" }")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			)
	.andExpect(status().isOk())
	.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	.andExpect(jsonPath("$.postCode1", is("AA")));
	
	}
	
	/**
	 * unit test basic auth response 401
	 * @throws Exception
	 */
	@Test
	public void testIntegrationControllerBasicAuth() throws Exception {
	PostCodeReqDTO request =  new PostCodeReqDTO();
	request.setPostCode1("AA");
	request.setPostCode2("AB");
	
	PostCodeResDTO response = new PostCodeResDTO();
	response.setPostCode1("AA");
	
	when(mockService.retrieveDistance(request)).thenReturn(Optional.of(response));
	mvc.perform(get("/postCode")
			.content("{ \"postCode1\" : \"AA\",\r\n"
					+ "    \"postCode2\" : \"AB\" }")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			)
	.andExpect(status().isUnauthorized());
	
	}

}
