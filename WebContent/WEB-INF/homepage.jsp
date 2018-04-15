<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<h2>Hello ${sessionScope.username}</h2>
			</div>
			<div class="pull-right" style="margin-top: 20px;">
			<ul>
				<li style="display: inline"><a href="/MessageApp/MessageController" role="button" class="btn btn-primary" name="message">Messages<span class="badge badge-light"></span></a></li>
				<li style="display: inline"><a href="/MessageApp/LoginController" role="button" class="btn btn-primary" name="logout">Log out<span class="badge badge-light"></span></a></li>
			</ul>
			</div>
		</div>
		<div class="new-message">
			<form action="/MessageApp/Homepage" method="POST">
				<div class="form-group">
					<label for="textSubject">Subject</label>
					<textarea class="form-control" id="textSubject" rows=1 name="subject"></textarea>
					<label for="textMessage">Message</label>
					<textarea class="form-control" id="textMessage" rows=15 name="newMessage"></textarea>				
				</div>
				<div class="form-group pull-right">
					<label for="receiver">Recipient</label>
					<select multiple-class="form-control" id="receiver" name="receiver">
						<c:forEach items="${sessionScope.usernames}" var="item">
							<option>${item}</option>
						</c:forEach>
					</select>
					<input type="submit" value="Send">
				</div>			
			</form>
		</div>
	</div>
</body>
</html>