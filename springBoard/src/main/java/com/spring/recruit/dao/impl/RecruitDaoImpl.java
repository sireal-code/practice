package com.spring.recruit.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.vo.CareerVo;
import com.spring.recruit.vo.CertificateVo;
import com.spring.recruit.vo.EducationVo;
import com.spring.recruit.vo.RecruitVo;

@Repository
public class RecruitDaoImpl implements RecruitDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<RecruitVo> findByNameAndPhone(String name, String phone) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("name", name);
	    paramMap.put("phone", phone);

	    return sqlSession.selectList("recruit.findByNameAndPhone", paramMap);
	}

	@Override
	public RecruitVo findRecruitBySeq(String seq) {
	    return sqlSession.selectOne("recruit.findRecruitBySeq", seq);
	}

	@Override
	public List<EducationVo> findEducationListBySeq(String seq) {
	    return sqlSession.selectList("recruit.findEducationListBySeq", seq);
	}

	@Override
	public List<CareerVo> findCareerListBySeq(String seq) {
	    return sqlSession.selectList("recruit.findCareerListBySeq", seq);
	}

	@Override
	public List<CertificateVo> findCertificateListBySeq(String seq) {
	    return sqlSession.selectList("recruit.findCertificateListBySeq", seq);
	}

	@Override
	public void insertRecruit(RecruitVo recruit) {
	    sqlSession.insert("recruit.insertRecruit", recruit);
	}

	
	@Override
	public void updateRecruit(RecruitVo recruit) {
	    sqlSession.update("recruit.updateRecruit", recruit);
	}

	@Override
	public void deleteEducationBySeq(String seq) {
	    sqlSession.delete("recruit.deleteEducationBySeq", seq);
	}

	@Override
	public void insertEducation(EducationVo edu) {
	    sqlSession.insert("recruit.insertEducation", edu);
	}

	@Override
	public void deleteCareerBySeq(String seq) {
	    sqlSession.delete("recruit.deleteCareerBySeq", seq);
	}

	@Override
	public void insertCareer(CareerVo career) {
	    sqlSession.insert("recruit.insertCareer", career);
	}

	@Override
	public void deleteCertificateBySeq(String seq) {
	    sqlSession.delete("recruit.deleteCertificateBySeq", seq);
	}

	@Override
	public void insertCertificate(CertificateVo cert) {
	    sqlSession.insert("recruit.insertCertificate", cert);
	}

}
