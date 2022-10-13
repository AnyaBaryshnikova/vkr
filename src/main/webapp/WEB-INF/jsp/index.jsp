<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Вход</title>
    <meta charset="utf-8">
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/projects"); %>
</sec:authorize>
<div class="d-flex justify-content-center">
    <div class="pt-5 pb-2">
        <div>
            <form method="POST" action="/login">
                <h2>Вход в систему</h2>
                <div>
                    <input name="username" type="text" placeholder="Username"
                           autofocus="true"/>
                    <input name="password" type="password" placeholder="Password"/>
                    <button type="submit">Вход</button>
                    <h4><a href="/registration">Зарегистрироваться</a></h4>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>