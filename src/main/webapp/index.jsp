<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
<h2>Hello World</h2>

<c:if test="${ empty loginUser }">
    <form action="${ contextPath }/login" method="post">
        <label for="userId">아이디 : </label>
        <input type="text" id="userId" name="userId"/>

        <br>

        <label for="userPwd">비밀번호 : </label>
        <input type="password" id="userPwd" name="userPwd"/>

        <br><br>

        <input type="button" onclick="location.assign('${ contextPath }/user/enroll');" value="회원가입">
        <input type="submit" value="로그인"/>
    </form>
</c:if>

<c:if test="${ not empty loginUser}">
    <a href = "${contextPath}/user/info">${ loginUser.name}</a>님 안녕하세요.

    <form action="${ contextPath }/logout" method="post">
        <input type="submit" value="로그아웃">
    </form>
</c:if>
</body>
</html>