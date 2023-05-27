<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
				<%@ page isErrorPage="true" %>
					<!DOCTYPE html>
					<html>

					<head>
						<meta charset="UTF-8">
						<title>Proyecto Examen</title>
						<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
							rel="stylesheet"
							integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
							crossorigin="anonymous">
						<script
							src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
					</head>

					<body class="bg-dark p-2 text-white " style="margin-left:10vw;">

						<h2><a href="/tasks">Dashboard</a></h2>

						<h1>Create a new task</h1>
						<form:form action="/tasks/new" modelAttribute="task" method="post">
							<div class="form-control form-control-lg bg-light">
								<form:label path="taskName">Task </form:label>
								<form:input path="taskName" class="form-control form-control-lg"
									placeholder="Enter Task" /><br>
								<form:errors path="taskName" class="text-danger" />
								<br>
								<form:label path="assigne">Assignee </form:label>
								<form:select  class="form-control form-control-lg" id="assigne" path="assigne">
									<option value="">Selecciona una opción</option>
									 <c:forEach var="user" items="${users}">
                                            <option value="${user.id}">${user.name}</option>
                                        </c:forEach>
								</form:select>
								<form:errors path="assigne" class="text-danger" />
								<br>
								<form:label path="priority">Priority </form:label>
								<form:select  class="form-control form-control-lg" id="priority" path="priority">
									<option value="">Selecciona una opción</option>
									<option value="1">High</option>
									<option value="2">Medium</option>
									<option value="3">Low</option>
								</form:select>
								<form:errors path="priority" class="text-danger" />
								<br>
								<div>
									<input class="form-control form-control-lg btn btn-primary btn-large btn-block"
										value="Create" type="submit" />
								</div>
						</form:form>
						</div>
					</body>

					</html>