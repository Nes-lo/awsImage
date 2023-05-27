<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
				<%@ page isErrorPage="true" %>

					<!DOCTYPE html>
					<html>

					<head>
						<meta charset="UTF-8">
						<title>Project Exam</title>
						<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
							rel="stylesheet"
							integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
							crossorigin="anonymous">
						<script
							src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
					</head>

					<body class="bg-dark p-2 text-white " style="margin:10vw;">

						<div class="d-flex justify-content-between align-items-center">
							<h1 class="text-left">Welcome, ${user.name}</h1>
							<p><a class="mr-4" href="/tasks/highlow">Priority High - Low</a></p>
							<p><a class="mr-4" href="/tasks/lowhigh">Priority Low - High</a></p>
							<p><a class="mr-4" href="/logout">Log Out</a></p>
						</div>


						<table class="table table-striped table-bordered text-white" style="color:white;">
							<thead>
								<tr>
									<th>Task</th>
									<th>Creator</th>
									<th>Assignee</th>
									<th>Priority</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="task" items="${tasks}">
									<tr>
										<td class="text-white"><a href="/tasks/${task.id}">${task.taskName}</a></td>
										<td class="text-white">
											<c:out value="${task.creator.name}"></c:out>
										</td>
										<td class="text-white">
											<c:out value="${task.assigne.name}"></c:out>
										</td>
										<td class="text-white">
											<c:if test="${task.priority==1 }">
												High
											</c:if>
											<c:if test="${task.priority==2 }">
												Medium
											</c:if>
											<c:if test="${task.priority==3 }">
												Low
											</c:if>
										</td>

										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>


						<p><a href="/tasks/new">Create Task</a></p>
					</body>

					</html>