package com.wccgroup.wccgroupjava.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import com.wccgroup.wccgroupjava.dto.PostCodeReqDTO;
import com.wccgroup.wccgroupjava.dto.PostCodeResDTO;
import com.wccgroup.wccgroupjava.dto.UpdatePostCodeResDTO;
import com.wccgroup.wccgroupjava.service.PostCodeService;

/**
 * 
 * @author Hermes
 *
 * test functionality of the get method in GeographicDistanceController
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {GeographicDistanceController.class})
public class GeographicDistanceControllerTest {

	@MockBean
	private PostCodeService mockService;
	
	@InjectMocks
	@Autowired
	private GeographicDistanceController testCase;
	
	/**
	 * mock object response
	 * @return Optional of postcode 
	 */
	private Optional<PostCodeResDTO> getResponse() {
		PostCodeResDTO response = new PostCodeResDTO();
		response.setDistance("12");
		response.setLatitude1(1);
		response.setLatitude2(2);
		return Optional.of(response);
		
	}
	
	/**
	 * mock object response
	 * @return Optional of update postcode 
	 */
	private UpdatePostCodeResDTO getUpdateResponse() {
		UpdatePostCodeResDTO response = new UpdatePostCodeResDTO();
		response.setPostCode("AA");
		return response;
		
	}
	
	/**
	 * assertion to make sure is correct status
	 */
	@Test
	void testGetPostalCode() {
		PostCodeReqDTO postCode = new PostCodeReqDTO();
		postCode.setPostCode1("AA");
		postCode.setPostCode2("AB");
		Mockito.when(mockService.retrieveDistance(postCode)).thenReturn(getResponse());
		ResponseEntity<PostCodeResDTO> testResult = testCase.postalCodes(postCode);
		assertEquals(HttpStatus.OK, testResult.getStatusCode());
	}
	
	/**
	 * assertion throws response status ex
	 */
	@Test
	void testGetPostalCodeEmpty() {
		PostCodeReqDTO postCode = new PostCodeReqDTO();
		postCode.setPostCode1("AA");
		postCode.setPostCode2("AB");
		Mockito.when(mockService.retrieveDistance(postCode)).thenReturn(Optional.empty());
		assertThrows(ResponseStatusException.class, () -> testCase.postalCodes(postCode));
	}
	
	/**
	 * assertion to make sure is correct status
	 */
	@Test
	void testUpdatePostalCode() {
		PostCodeReqDTO body = new PostCodeReqDTO();
		body.setPostCode1("AA");
		body.setPostCode2("AB");
		Mockito.when(mockService.isPostCodeExist("A")).thenReturn(true);
		Mockito.when(mockService.updatePostCode("A", body)).thenReturn(getUpdateResponse());
		ResponseEntity<UpdatePostCodeResDTO> testResult = testCase.updatePostCode("A", body);
		assertEquals(HttpStatus.OK, testResult.getStatusCode());
	}
	
	/**
	 * assertion throws response status ex
	 */
	@Test
	void testUpdatePostalCodeEx() {
		PostCodeReqDTO body = new PostCodeReqDTO();
		body.setPostCode1("AA");
		body.setPostCode2("AB");
		Mockito.when(mockService.isPostCodeExist("A")).thenReturn(false);
		Mockito.when(mockService.updatePostCode("A", body)).thenReturn(getUpdateResponse());
		assertThrows(ResponseStatusException.class, () -> testCase.updatePostCode("A", body));
	}
}
