package com.spring.recruit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVo;
import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.service.RecruitService;
import com.spring.recruit.vo.CareerVo;
import com.spring.recruit.vo.CertificateVo;
import com.spring.recruit.vo.EducationVo;
import com.spring.recruit.vo.RecruitVo;

@Service
public class RecruitServiceImpl implements RecruitService {

	@Autowired
	RecruitDao recruitDao;
	
	// 이름 + 전화번호로 조회
	public RecruitVo findByNameAndPhone(String name, String phone) {
	    List<RecruitVo> resultList = recruitDao.findByNameAndPhone(name, phone);
	    return resultList.isEmpty() ? null : resultList.get(0);
	}
	
	// 기본정보 + 학력 + 경력 + 자격증 조회
	public RecruitVo findBySeqWithDetails(String seq) {
	    RecruitVo recruit = recruitDao.findRecruitBySeq(seq);

	    recruit.setEducationList(Optional.ofNullable(recruitDao.findEducationListBySeq(seq)).orElse(new ArrayList<>()));
	    recruit.setCareerList(Optional.ofNullable(recruitDao.findCareerListBySeq(seq)).orElse(new ArrayList<>()));
	    recruit.setCertificateList(Optional.ofNullable(recruitDao.findCertificateListBySeq(seq)).orElse(new ArrayList<>()));

	    return recruit;
	}


	@Transactional
	@Override
	public void saveRecruit(RecruitVo recruit) {
		
	    if (recruit.getSeq() == null || recruit.getSeq().isEmpty()) {
	        // 신규 지원자 insert
	    	recruit.setSeq(UUID.randomUUID().toString());
	        recruitDao.insertRecruit(recruit);
	    } else {
	        // 기존 지원자 update
	        recruitDao.updateRecruit(recruit);
	        
	        recruitDao.deleteEducationBySeq(recruit.getSeq());
	        recruitDao.deleteCareerBySeq(recruit.getSeq());
	        recruitDao.deleteCertificateBySeq(recruit.getSeq());
	    }

	    // 학력
	    for (EducationVo edu : recruit.getEducationList()) {
	        if (isEmptyEducation(edu)) continue; 
	        edu.setEduSeq(UUID.randomUUID().toString());
	        edu.setSeq(recruit.getSeq());
	        recruitDao.insertEducation(edu);
	    }

	    // 경력
	    for (CareerVo car : recruit.getCareerList()) {
	        if (car.getCompName() == null || car.getCompName().trim().isEmpty()) continue;
	        car.setCarSeq(UUID.randomUUID().toString());
	        car.setSeq(recruit.getSeq());               
	        recruitDao.insertCareer(car);
	    }

	    // 자격증
	    for (CertificateVo cert : recruit.getCertificateList()) {
	        if (cert.getQualifiName() == null || cert.getQualifiName().trim().isEmpty()) continue;
	        cert.setCertSeq(UUID.randomUUID().toString()); 
	        cert.setSeq(recruit.getSeq());                
	        recruitDao.insertCertificate(cert);
	    }
	}
	
	// 비어있는 행 검토
	private boolean isEmptyEducation(EducationVo edu) {
	    return isBlank(edu.getSchoolName()) &&
	           isBlank(edu.getStartPeriod()) &&
	           isBlank(edu.getEndPeriod()) &&
	           isBlank(edu.getDivision()) &&
	           isBlank(edu.getLocation()) &&
	           isBlank(edu.getMajor()) &&
	           isBlank(edu.getGrade());
	}

	private boolean isBlank(String str) {
	    return str == null || str.trim().isEmpty();
	}


}
