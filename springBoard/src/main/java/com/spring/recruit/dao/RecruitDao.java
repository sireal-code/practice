package com.spring.recruit.dao;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.recruit.vo.CareerVo;
import com.spring.recruit.vo.CertificateVo;
import com.spring.recruit.vo.EducationVo;
import com.spring.recruit.vo.RecruitVo;

public interface RecruitDao {
    
	List<RecruitVo> findByNameAndPhone(String name, String phone);

	RecruitVo findRecruitBySeq(String seq);
	
	List<EducationVo> findEducationListBySeq(String seq);
	List<CareerVo> findCareerListBySeq(String seq);
	List<CertificateVo> findCertificateListBySeq(String seq);
	
	
	void insertRecruit(RecruitVo recruit);
	void updateRecruit(RecruitVo recruit);

	void deleteEducationBySeq(String seq);
	void insertEducation(EducationVo edu);

	void deleteCareerBySeq(String seq);
	void insertCareer(CareerVo career);

	void deleteCertificateBySeq(String seq);
	void insertCertificate(CertificateVo cert);

	
}
