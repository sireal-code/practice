<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>입사지원서</title>
	<script>

	function addRow(sectionId) {
		 
        const section = document.getElementById(sectionId);
	    const tbody = section.querySelector('tbody');
	    const template = tbody.querySelector('.templateRow');
	    
	    const newRow = template.cloneNode(true);
	    newRow.style.display = '';
	    newRow.classList.remove('templateRow');
	    newRow.classList.add('dataRow');

	    const index = tbody.querySelectorAll('tr.dataRow').length;

	    newRow.querySelectorAll('[data-name]').forEach(el => {
	        const templateName = el.getAttribute('data-name');
	        const realName = templateName.replace(/\[%i%\]/g, index);
	        el.setAttribute('name', realName);
	        console.log(`✅ name="${realName}" 세팅됨`);
	    });

	    tbody.appendChild(newRow);
	}


	
	function deleteRow(sectionId) {
	    const tbody = document.querySelector(`#${sectionId} tbody`);
	    const rows = tbody.querySelectorAll('.dataRow');
	    rows.forEach(row => {
	        const checkbox = row.querySelector('input[type="checkbox"]');
	        if (checkbox && checkbox.checked) {
	            row.remove();
	        }
	    });
	    resetIndexes(sectionId); 
	}

	
	function resetIndexes(sectionId) {
	    const rows = document.querySelectorAll(`#${sectionId} .dataRow`);
	    rows.forEach((row, index) => {
	        row.querySelectorAll('[name]').forEach(el => {
	            const oldName = el.name;
	            const newName = oldName.replace(/\[\d+\]/g, `[${index}]`);
	            el.name = newName;
	        });
	    });
	}

	
	document.addEventListener('DOMContentLoaded', () => {
	    const submitMessage = '${submitMessage}';
	    if (submitMessage?.trim()) {
	        alert(submitMessage);
	        location.href = '${pageContext.request.contextPath}/recruit/login.do';
	    }

	    <c:if test="${empty recruit.educationList}">
	        addRow('educationSection');
	        addRow('careerSection');
	        addRow('certificateSection');
	    </c:if>
	});

	</script>


</head>
<body>

<c:set var="editable" value="${recruit.submit != 'Y'}" />

<div style="width: fit-content; margin: 0 auto;">

<h2 style="text-align: center;">입사 지원서</h2>

<form action="${pageContext.request.contextPath}/recruit/save.do" method="post">
    <input type="hidden" name="seq" value="${recruit.seq}" />

    <!-- 기본정보 -->
    <table id="basicSection" border="1">
        <tr>
            <th>이름</th>
            <td><input type="text" name="name" value="${recruit.name}" ${editable ? "" : "readonly"} /></td>
            <th>생년월일</th>
            <td><input type="text" name="birth" value="${recruit.birth}" ${editable ? "" : "readonly"} /></td>
        </tr>
        <tr>
            <th>성별</th>
            <td>
                <select name="gender" ${editable ? "" : "disabled"}>
                    <option value="남자" ${recruit.gender == '남자' ? 'selected' : ''}>남자</option>
                    <option value="여자" ${recruit.gender == '여자' ? 'selected' : ''}>여자</option>
                </select>
            </td>
            <th>연락처</th>
            <td><input type="text" name="phone" value="${recruit.phone}" ${editable ? "" : "readonly"} /></td>
        </tr>
        <tr>
            <th>email</th>
            <td><input type="text" name="email" value="${recruit.email}" ${editable ? "" : "readonly"} /></td>
            <th>주소</th>
            <td><input type="text" name="addr" value="${recruit.addr}" ${editable ? "" : "readonly"} /></td>
        </tr>
        <tr>
            <th>희망근무지</th>
            <td>
                <select name="location" ${editable ? "" : "disabled"}>
                    <option value="서울" ${recruit.location == '서울' ? 'selected' : ''}>서울</option>
                    <option value="경기" ${recruit.location == '경기' ? 'selected' : ''}>경기</option>
                    <option value="강원" ${recruit.location == '강원' ? 'selected' : ''}>강원</option>
                </select>
            </td>
            <th>근무형태</th>
            <td>
                <select name="workType" ${editable ? "" : "disabled"}>
                    <option value="정규직" ${recruit.workType == '정규직' ? 'selected' : ''}>정규직</option>
                    <option value="계약직" ${recruit.workType == '계약직' ? 'selected' : ''}>계약직</option>
                </select>
            </td>
        </tr>
    </table>


   <!-- 학력 -->    
	<div style="display: flex; justify-content: space-between; align-items: center;">
	    <h2 style="margin: 0;">학력</h2>
	    <c:if test="${editable}">
	        <div>
	            <button type="button" onclick="addRow('educationSection')">추가</button>
	            <button type="button" onclick="deleteRow('educationSection')">삭제</button>
	        </div>
	    </c:if>
	</div>
	
    <table id="educationSection" border="1">
        <thead>
            <tr>
                <th></th>
                <th>재학기간</th>
                <th>구분</th>
                <th>학교명(소재지)</th>
                <th>전공</th>
                <th>학점</th>
            </tr>
        </thead>
        <tbody>
        
        <!-- 행 추가 및 삭제 -->
             <tr class="templateRow" style="display:none">
                <td><input type="checkbox" /></td>
			    <td>
			        <input type="text" data-name="educationList[i].startPeriod" size="6" /> ~
			        <input type="text" data-name="educationList[i].endPeriod" size="6" />
			    </td>
                <td>
                    <select data-name="educationList[i].division">
                        <option>재학</option>
                        <option>중퇴</option>
                        <option>졸업</option>
                    </select>
                </td>
                <td>
                    <input type="text" data-name="educationList[i].schoolName"/>
                    <select data-name="educationList[i].location">
                        <option>서울</option>
                        <option>경기</option>
                    </select>
                </td>
                <td><input type="text" data-name="educationList[i].major" /></td>
                <td><input type="text" data-name="educationList[i].grade" size="5" /></td>
            </tr> 
            
			<c:forEach var="edu" items="${recruit.educationList}" varStatus="i">
			    <tr class="dataRow">
			        <td><input type="checkbox" /></td>
			        <td>
			            <input type="text" name="educationList[${i.index}].startPeriod" value="${edu.startPeriod}" size="6" ${editable ? "" : "readonly"} /> ~
			            <input type="text" name="educationList[${i.index}].endPeriod" value="${edu.endPeriod}" size="6" ${editable ? "" : "readonly"} />
			        </td>
			        <td>
			            <select name="educationList[${i.index}].division" ${editable ? "" : "disabled"}>
			                <option ${edu.division == '재학' ? 'selected' : ''}>재학</option>
			                <option ${edu.division == '중퇴' ? 'selected' : ''}>중퇴</option>
			                <option ${edu.division == '졸업' ? 'selected' : ''}>졸업</option>
			            </select>
			        </td>
			        <td>
			            <input type="text" name="educationList[${i.index}].schoolName" value="${edu.schoolName}" ${editable ? "" : "readonly"} />
			            <select name="educationList[${i.index}].location" ${editable ? "" : "disabled"}>
			                <option ${edu.location == '서울' ? 'selected' : ''}>서울</option>
			                <option ${edu.location == '경기' ? 'selected' : ''}>경기</option>
			            </select>
			        </td>
			        <td><input type="text" name="educationList[${i.index}].major" value="${edu.major}" ${editable ? "" : "readonly"} /></td>
			        <td><input type="text" name="educationList[${i.index}].grade" value="${edu.grade}" size="5" ${editable ? "" : "readonly"} /></td>
			    </tr>
			</c:forEach>
        </tbody>
    </table>

	
    <!-- 경력 -->
    <div style="display: flex; justify-content: space-between; align-items: center;">
	    <h2 style="margin: 0;">경력</h2>
	    <c:if test="${editable}">
	        <div>
	            <button type="button" onclick="addRow('careerSection')">추가</button>
	            <button type="button" onclick="deleteRow('careerSection')">삭제</button>
	        </div>
	    </c:if>
	</div>
	
    <table id="careerSection" border="1">
        <thead>
            <tr>
                <th></th>
                <th>근무기간</th>
                <th>회사명</th>
                <th>부서/직급/직책</th>
                <th>지역</th>
            </tr>
        </thead>
        <tbody>
        
        <!-- 행 추가 및 삭제 -->
             <tr class="templateRow" style="display:none">
                <td><input type="checkbox" /></td>
                <td>
                    <input type="text" data-name="careerList[i].startPeriod" size="6" /> ~
                    <input type="text" data-name="careerList[i].endPeriod" size="6" />
                </td>
                <td>
					<input type="text" data-name="careerList[i].compName" />
                </td>
                <td>
                    <input type="text" data-name="careerList[i].task" />
                </td>
                <td><input type="text" data-name="careerList[i].location" /></td>
            </tr> 


		<c:forEach var="career" items="${recruit.careerList}" varStatus="i">
		    <tr class="dataRow">
		        <td><input type="checkbox" /></td>
		        <td>
		            <input type="text" name="careerList[${i.index}].startPeriod" value="${career.startPeriod}" size="6" ${editable ? "" : "readonly"} /> ~
		            <input type="text" name="careerList[${i.index}].endPeriod" value="${career.endPeriod}" size="6" ${editable ? "" : "readonly"} />
		        </td>
		        <td><input type="text" name="careerList[${i.index}].compName" value="${career.compName}" ${editable ? "" : "readonly"} /></td>
		        <td><input type="text" name="careerList[${i.index}].task" value="${career.task}" ${editable ? "" : "readonly"} /></td>
		        <td><input type="text" name="careerList[${i.index}].location" value="${career.location}" ${editable ? "" : "readonly"} /></td>
		    </tr>
		</c:forEach>
            
        </tbody>
    </table>
    
    <!-- 자격증 -->
    <div style="display: flex; justify-content: space-between; align-items: center;">
	    <h2 style="margin: 0;">자격증</h2>
	    <c:if test="${editable}">
	        <div>
	            <button type="button" onclick="addRow('certificateSection')">추가</button>
	            <button type="button" onclick="deleteRow('certificateSection')">삭제</button>
	        </div>
	    </c:if>
	</div>
	
     <table id="certificateSection" border="1">
        <thead>
            <tr>
                <th></th>
                <th>자격증명</th>
                <th>취득일</th>
                <th>발행처</th>
            </tr>
        </thead>
        <tbody>
        
        <!-- 행 추가 및 삭제 -->
             <tr class="templateRow" style="display:none">
                <td><input type="checkbox" /></td>
                <td>
                    <input type="text" data-name="certificateList[i].qualifiName"/>
                </td>
                <td>
					<input type="text" data-name="certificateList[i].acquDate" />
                </td>
                <td>
                    <input type="text" data-name="certificateList[i].organizeName"/>
                </td>
            </tr> 


		<c:forEach var="certificate" items="${recruit.certificateList}" varStatus="i">
		    <tr class="dataRow">
		        <td><input type="checkbox" /></td>
		        <td><input type="text" name="certificateList[${i.index}].qualifiName" value="${certificate.qualifiName}" ${editable ? "" : "readonly"} /></td>
		        <td><input type="text" name="certificateList[${i.index}].acquDate" value="${certificate.acquDate}" ${editable ? "" : "readonly"} /></td>
		        <td><input type="text" name="certificateList[${i.index}].organizeName" value="${certificate.organizeName}" ${editable ? "" : "readonly"} /></td>
		    </tr>
		</c:forEach>
            
        </tbody>
    </table>

	<!-- 저장 및 제출 -->
	<c:if test="${editable}">
	    <div style="text-align: center; margin-top: 20px;">
	        <button type="submit" formaction="${pageContext.request.contextPath}/recruit/save.do">저장</button>
	        <button type="submit" formaction="${pageContext.request.contextPath}/recruit/submit.do">제출</button>
	    </div>
	</c:if>

</div>
</form>

</body>
</html>
