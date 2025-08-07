package com.spring.recruit.service;

import org.springframework.stereotype.Service;

import com.spring.recruit.vo.RecruitVo;

@Service
public interface RecruitService {

	RecruitVo findByNameAndPhone(String name, String phone);

	RecruitVo findBySeqWithDetails(String seq);

	void saveRecruit(RecruitVo recruit);

}
