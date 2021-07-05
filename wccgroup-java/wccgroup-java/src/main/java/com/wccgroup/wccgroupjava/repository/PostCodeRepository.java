package com.wccgroup.wccgroupjava.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wccgroup.wccgroupjava.dto.PostCodeResDTO;
import com.wccgroup.wccgroupjava.entity.PostCode;

public interface PostCodeRepository extends JpaRepository<PostCode, Long>{

	@Query("SELECT a FROM PostCode a WHERE a.postCode IN (:postCode)")
	List<PostCode> findByPostCodes(@Param("postCode")List<String> postCode, Sort sort);
	
	@Query("SELECT new com.wccgroup.wccgroupjava.dto.PostCodeResDTO(a.postCode, b.postCode, a.latitude, a.longtitude, b.latitude, b.longtitude, '') "
			+ "FROM PostCode a, PostCode b WHERE a.postCode = :postCode1 and b.postCode = :postCode2")
	Optional<PostCodeResDTO> findByPostCode(@Param("postCode1")String postCode1, @Param("postCode2")String postCode2);

	boolean existsByPostCode(String postCode);

	Optional<PostCode> findByPostCode(String postCode);

}
