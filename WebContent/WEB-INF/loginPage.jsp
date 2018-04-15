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
<c:if test="${requestScope.registrationScreen}">
	<div class="container">
		<c:if test="${requestScope.usernameDuplicate}">
			<div class="alert alert-danger">Username is already occupied.</div>
		</c:if>
		<c:if test="${requestScope.unconfirmedPassword}">
			<div class="alert alert-danger">Passwords are not equal.</div>
		</c:if>
		<div class="jumbotron row align-items-center">
			<div class="col-6 col-md-4"></div>
			<div class="col-6 col-md-4">
				<form action="/MessageApp/UserController" method="POST">
					<div class="form-group">
						<div class="form-inline">
							<input type="text" name="username" id="username"
								placeholder="Username">
						</div>
					</div>
					<div class="form-group">
						<div class="form-inline">
							<input type="password" name="password" id="password"
								placeholder="Password">
						</div>
					</div>
					<div class="form-group">
						<div class="form-inline">
							<input type="password" name="confirmpassword" id="password"
								placeholder="Confirm Password">
						</div>
					</div>
					<div class="form-group">
						<a href="/MessageApp/LoginController" class="btn btn-primary"
							role="button" aria-disabled="true">Get Back</a>
						<button type="submit" class="btn btn-primary">Sign Up</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</c:if>

<c:if
	test="${empty requestScope.registrationScreen || requestScope.signedUp}">
	<div class="container">
		<c:if test="${not empty requestScope.loginSuccess}">
			<div class="alert alert-danger">Login failed, try again.</div>
		</c:if>
		<c:if test="${requestScope.signedUp}">
			<div class="alert alert-success">Registration success, you can
				log into your account now.</div>
		</c:if>
		<div class="jumbotron row align-items-center">
			<div class="col-6 col-md-4"></div>
			<div class="col-6 col-md-4">
				<form action="/MessageApp/LoginController" method="POST">
					<div class="form-group">
						<label for="username">Login</label>
						<div class="form-inline">
							<input type="text" name="username" id="username">
						</div>
					</div>
					<div class="form-group">
						<label for="password">Password</label>
						<div class="form-inline">
							<input type="password" name="password" id="password">
						</div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Login</button>
						<a href="/MessageApp/LoginController?reg=1"
							class="btn btn-primary" role="button" aria-disabled="true">Sign
							Up</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</c:if>
</body>
</html>