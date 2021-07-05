package com.wccgroup.wccgroupjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wccgroup.wccgroupjava.dto.PostCodeReqDTO;
import com.wccgroup.wccgroupjava.dto.PostCodeResDTO;
import com.wccgroup.wccgroupjava.dto.UpdatePostCodeResDTO;
import com.wccgroup.wccgroupjava.service.PostCodeService;

@RestController
public class GeographicDistanceController {
	
	private static final String POSTCODE_NOT_FOUND = "Post code not found";
	private static final String POSTCODE_NO_CONTENT = "No content found in post code";

	@Autowired
	private PostCodeService postCodeService;
	
	@GetMapping(path = "/postCode", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostCodeResDTO> postalCodes(@RequestBody PostCodeReqDTO postCode) {
		if (!postCodeService.retrieveDistance(postCode).isEmpty()) {
			return ResponseEntity.ok(postCodeService.retrieveDistance(postCode).get());
		}
		throw new ResponseStatusException(HttpStatus.NO_CONTENT, POSTCODE_NOT_FOUND);
	}
	
	@PutMapping(path = "/postCode/{postCode}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UpdatePostCodeResDTO> updatePostCode(@PathVariable String postCode, @RequestBody PostCodeReqDTO reqBody) {
		if(!postCodeService.isPostCodeExist(postCode)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, POSTCODE_NO_CONTENT);
		}
		return ResponseEntity.ok(postCodeService.updatePostCode(postCode, reqBody));
	}
}
