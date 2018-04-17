<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="jumbotron row align-items-center">
			<div class="pull-left">
				<h2>Your messages</h2>
			</div>
			<div class="pull-right" style="margin-top: 20px;">
				<ul>
					<li style="display: inline"><a href="/MessageApp/Homepage"
						role="button" class="btn btn-primary" name="message">Homepage<span
							class="badge badge-light"></span></a></li>
					<li style="display: inline"><a
						href="/MessageApp/LoginController" role="button"
						class="btn btn-primary" name="logout">Log out<span
							class="badge badge-light"></span></a></li>
				</ul>
			</div>
		</div>
		<div class="messages">
			<c:forEach items="${messages}" var="item">
				<div class="container">
					<div class="row">
						<div class="col-md-4">
							<c:out value="${authors.get(item.authorId)}"></c:out>
						</div>
						<div class="col-md-4">
							<a href="/MessageApp/MessageController?id=${item.id}">${item.subject}</a>
						</div>
						<div class="col-md-4">
							<c:out value="${item.getDateAsString()}"></c:out>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>