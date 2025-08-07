package com.spring.recruit.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.recruit.service.RecruitService;
import com.spring.recruit.vo.CareerVo;
import com.spring.recruit.vo.CertificateVo;
import com.spring.recruit.vo.EducationVo;
import com.spring.recruit.vo.RecruitVo;

@Controller
@RequestMapping("/recruit")
public class RecruitController {
	
	@Autowired
	private RecruitService recruitService;
	
	// 입사지원 로그인 시 처음 지원자면 입력 페이지로, 기존 지원자면 수정 페이지로 이동
	@RequestMapping(value="/login.do", method = { RequestMethod.GET,RequestMethod.POST})
	public String login(@RequestParam(required = false) String name, @RequestParam(required = false) String phone,
						HttpSession session, Model model) {
		
	    if (name == null || phone == null) {
	        return "recruit/recruitLogin";
	    }

		RecruitVo recruit = recruitService.findByNameAndPhone(name, phone);

		// 기존 지원자일때
		if (recruit != null) {
		    recruit = recruitService.findBySeqWithDetails(recruit.getSeq());
		    session.setAttribute("recruit", recruit);
		    return "redirect:/recruit/view.do?seq=" + recruit.getSeq();
		
		} else { // 기존 지원자가 아닐때
			RecruitVo newRecruit = new RecruitVo();
			newRecruit.setName(name);
			newRecruit.setPhone(phone);
			
			// 각 리스트 초기화
			newRecruit.setEducationList(Arrays.asList(new EducationVo()));
			newRecruit.setCareerList(Arrays.asList(new CareerVo()));
			newRecruit.setCertificateList(Arrays.asList(new CertificateVo()));
			
			session.setAttribute("recruit", newRecruit);
	        model.addAttribute("recruit", newRecruit); 
	        return "recruit/recruitView";
		}
	
	}
	

	// 입사지원서 보기
	@RequestMapping("/view.do")
	public String view(@RequestParam String seq, HttpSession session, Model model) {
	    RecruitVo recruit = (RecruitVo) session.getAttribute("recruit");

	    if (recruit == null || !seq.equals(recruit.getSeq())) {
	        recruit = recruitService.findBySeqWithDetails(seq);
	        session.setAttribute("recruit", recruit);
	    }

	    model.addAttribute("recruit", recruit); 
	    return "recruit/recruitView";
	}

	
	// 저장하기
	@RequestMapping(value="/save.do", method=RequestMethod.POST)
	public String save(RecruitVo recruit, HttpSession session, Model model) {
		
		recruit.setSubmit("N");
	    recruitService.saveRecruit(recruit);

	    // 최신 데이터 조회
	    RecruitVo updatedRecruit = recruitService.findBySeqWithDetails(recruit.getSeq());
	    session.setAttribute("recruit", updatedRecruit);

	    model.addAttribute("recruit", updatedRecruit);
	    return "recruit/recruitView";
	}

	// 제출하기
	@RequestMapping(value="/submit.do", method=RequestMethod.POST)
	public String submit(RecruitVo recruit, HttpSession session, Model model) {
	    recruit.setSubmit("Y");
	    recruitService.saveRecruit(recruit);

	    RecruitVo updatedRecruit = recruitService.findBySeqWithDetails(recruit.getSeq());
	    session.setAttribute("recruit", updatedRecruit);
	    model.addAttribute("recruit", updatedRecruit);

	    model.addAttribute("submitMessage", "입사지원서가 성공적으로 제출되었습니다.");

	    return "recruit/recruitView";
	}
	
}
