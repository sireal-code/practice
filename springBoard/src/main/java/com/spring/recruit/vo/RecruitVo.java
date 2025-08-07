package com.spring.recruit.vo;

import java.util.List;

public class RecruitVo {

    private String seq;         // 지원서 고유번호
    private String name;        
    private String birth;      
    private String gender;      // 성별 (FIELD3)
    private String phone;     
    private String email;       
    private String addr;      
    private String location;    // 희망 근무지
    private String workType;    // 근무 형태 
    private String submit;      // 제출 여부 ("Y"/"N")

    public RecruitVo() {}

    private List<EducationVo> educationList;
    private List<CareerVo> careerList;
    private List<CertificateVo> certificateList;
    
    // Getter & Setter
    public String getSeq() {
        return seq;
    }
    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getWorkType() {
        return workType;
    }
    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getSubmit() {
        return submit;
    }
    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public boolean isSubmitted() {
        return "Y".equalsIgnoreCase(this.submit);
    }

    
    public List<EducationVo> getEducationList() {
        return educationList;
    }
    public void setEducationList(List<EducationVo> educationList) {
        this.educationList = educationList;
    }

    public List<CareerVo> getCareerList() {
        return careerList;
    }
    public void setCareerList(List<CareerVo> careerList) {
        this.careerList = careerList;
    }

    public List<CertificateVo> getCertificateList() {
        return certificateList;
    }
    public void setCertificateList(List<CertificateVo> certificateList) {
        this.certificateList = certificateList;
    }

    @Override
    public String toString() {
        return "RecruitVo [seq=" + seq + ", name=" + name + ", birth=" + birth + ", gender=" + gender +
               ", phone=" + phone + ", email=" + email + ", addr=" + addr + ", location=" + location +
               ", workType=" + workType + ", submit=" + submit + "]";
    }
    

}
