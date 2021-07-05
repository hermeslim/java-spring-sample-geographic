package com.wccgroup.wccgroupjava.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wccgroup.wccgroupjava.dto.PostCodeReqDTO;
import com.wccgroup.wccgroupjava.dto.PostCodeResDTO;
import com.wccgroup.wccgroupjava.dto.UpdatePostCodeResDTO;
import com.wccgroup.wccgroupjava.entity.PostCode;
import com.wccgroup.wccgroupjava.repository.PostCodeRepository;

@Service
@Transactional
public class PostCodeService {
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(PostCodeService.class);

	@Autowired
	private HaversinceService haversinceService;
	
	@Autowired
	private PostCodeRepository postCodeRepo;

	/**
	 * get distance, latitude and longtitude for both postcodes
	 * @param postCode
	 * @return Optional<PostCodeResDTO>
	 */
	public Optional<PostCodeResDTO> retrieveDistance(PostCodeReqDTO postCode) {
		
		Optional<PostCodeResDTO> result = postCodeRepo.findByPostCode(postCode.getPostCode1(), postCode.getPostCode2());
		if (result.isPresent()) {
			PostCodeResDTO postCodeResponse = result.get();
			postCodeResponse.setDistance(haversineFormula(postCodeResponse.getLatitude1(),postCodeResponse.getLongtitude1(),postCodeResponse.getLatitude2(), postCodeResponse.getLongtitude2()));
			return result;
		}
		return Optional.empty();
	}
	
	private String haversineFormula(double lat1, double lon1, double lat2, double lon2) {
		return haversinceService.haversine(lat1, lon1, lat2, lon2) + " KM";
	}

	/**
	 * get distance, latitude and longtitude for both postcodes
	 * @param postCode
	 * @return String updated post code 
	 */
	public UpdatePostCodeResDTO updatePostCode(String postCode, PostCodeReqDTO body) {
		UpdatePostCodeResDTO responseDTO = new UpdatePostCodeResDTO();
		Optional<PostCode> ps = postCodeRepo.findByPostCode(postCode);
		if(ps.isPresent()) {
			PostCode postCodeToUpdate = ps.get();
			postCodeToUpdate.setLatitude(body.getLatitude());
			postCodeToUpdate.setLongtitude(body.getLongtitude());
			postCodeToUpdate.setPostCode(body.getPostCode());
			
			responseDTO.setPostCode(postCodeRepo.save(postCodeToUpdate).getPostCode());
			
			log.info("Successfully updated postcode : " + responseDTO.getPostCode());
		}
		return responseDTO;
	}

	public boolean isPostCodeExist(String postCode) {
		return postCodeRepo.existsByPostCode(postCode);
	}
		

}
