<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>입사지원 로그인</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>

<form id="loginForm" method="post" action="${pageContext.request.contextPath}/recruit/login.do" >
    <table align="center" border="1" cellpadding="10" cellspacing="0">
        <tr>
            <td align="center">이름</td>
            <td><input type="text" name="name" required /></td>
        </tr>
        <tr>
            <td align="center">휴대폰번호</td>
            <td><input type="text" name="phone" required /></td>
        </tr>
    </table>
    <div style="text-align: center; margin-top: 10px;">
        <button type="submit">입사지원 로그인</button>
    </div>
</form>


</body>
</html>
