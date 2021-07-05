package com.wccgroup.wccgroupjava.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wccgroup.wccgroupjava.dto.PostCodeReqDTO;
import com.wccgroup.wccgroupjava.dto.PostCodeResDTO;
import com.wccgroup.wccgroupjava.repository.PostCodeRepository;

/**
 * 
 * @author Hermes
 *
 * test functionality of the get method in PostCodeService
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PostCodeService.class, HaversinceService.class})
public class PostCodeServiceTest {

	@MockBean
	private PostCodeRepository mockRepo;
	
	@InjectMocks
	@Autowired
	private PostCodeService testCase;
	
	/**
	 * mock object response
	 * @return Optional of postcode 
	 */
	private Optional<PostCodeResDTO> getResponse() {
		PostCodeResDTO response = new PostCodeResDTO();
		response.setLatitude1(1);
		response.setLatitude2(2);
		response.setLongtitude1(1);
		response.setLongtitude2(2);
		response.setPostCode1("AA");
		response.setPostCode2("AB");
		return Optional.of(response);
	}
	
	/**
	 * assertion to make sure is all value return correctly
	 */
	@Test
	void testRetrieveDistance() {
		PostCodeReqDTO postCode = new PostCodeReqDTO();
		postCode.setPostCode1("AA");
		postCode.setPostCode2("AB");
		Mockito.when(mockRepo.findByPostCode(postCode.getPostCode1(), postCode.getPostCode2())).thenReturn(getResponse());
		Optional<PostCodeResDTO> testResult = testCase.retrieveDistance(postCode);
		assertEquals("AA", testResult.get().getPostCode1());
		assertEquals("AB", testResult.get().getPostCode2());
		assertEquals(1, testResult.get().getLatitude1());
		assertEquals(2, testResult.get().getLatitude2());
		assertEquals(1, testResult.get().getLongtitude1());
		assertEquals(2, testResult.get().getLongtitude2());
		assertEquals("157.2254320380729 KM", testResult.get().getDistance());
	}
	
	/**
	 * assertion true if the result is empty
	 */
	@Test
	void testGetPostalCodeEmpty() {
		PostCodeReqDTO postCode = new PostCodeReqDTO();
		postCode.setPostCode1("AA");
		postCode.setPostCode2("AB");
		Mockito.when(mockRepo.findByPostCode(postCode.getPostCode1(), postCode.getPostCode2())).thenReturn(Optional.empty());
		Optional<PostCodeResDTO> testResult = testCase.retrieveDistance(postCode);
		assertTrue(testResult.isEmpty());
	}
}
