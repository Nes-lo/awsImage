<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
				</head>

				<body class="bg-dark p-2 text-white " style="margin-left:20vw; margin-top:5vw; margin-right: 20vw;">
					<div class="container">
						<div class="row">
							<div class="col-9">
								<h3 class="display-4 fw-bold">Task: ${task.taskName}</h3>
							</div>
							<div class="col3">
								<h6><a href="/tasks">Dashboard</a></h6>
							</div>
						</div>
						<div class="col-8">
							<h5>Creator: ${task.creator.name} </h5>
							<h5>Assignee: ${task.assigne.name} </h5>
							<c:if test="${task.priority==1 }">
                            												<h5>Priority: High</h5>
                            											</c:if>
                            											<c:if test="${task.priority==2 }">
                            												<h5>Priority: Medium</h5>
                            											</c:if>
                            											<c:if test="${task.priority==3 }">
                            												<h5>Priority: Low</h5>
                            											</c:if>

						</div>

						<div class="col-12 text-center mt-3">

							<c:if test="${task.creator.id==user.id && task.completed==false}">
								<div class="d-flex justify-content-center">
                                	<form class="d-inline me-auto align-self-center" action="/tasks/${task.id}/edit" method="get">
                                		<button type="submit" class="btn btn-warning nav-link d-inline me-auto fw-bold text-white align-self-center">Edit</button>
                                	</form>
                                	<form class="d-inline ms-auto align-self-center" action="/tasks/${task.id}/delete" method="post">
                                		<input type="hidden" name="_method" value="delete" />
                                		<button type="submit" class="btn btn-danger nav-link d-inline me-2 fw-bold text-white align-self-center">Delete</button>
                                	</form>
                                </div>
							</c:if>
							<br>
							<c:if test="${task.assigne.id==user.id && task.completed==false }">
								<div class="mb-5">
									<div class="d-inline">
										<a href="/tasks/${task.id}/completed"
											class="btn btn-primary nav-link d-inline me-2 fw-bold text-white">Completed</a>
									</div>
								</div>
							</c:if>
						</div>
					</div>

				</body>

				</html>