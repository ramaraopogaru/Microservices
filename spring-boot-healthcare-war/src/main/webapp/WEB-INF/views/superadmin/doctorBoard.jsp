<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor Board</title>
</head>
<body>
	<div class="panel panel-success">
	  <!-- Default panel contents -->
	  <div class="panel-heading">Doctor Management Board</div>
	  <div class="col-md-6 col-sm-12 btn-margin-bottom action-buttons">
        <div class="btn-group" id="divAddNew">
            <a title="Add new" class="btn btn-block btn-danger btn-flat" href="./getDoctorRegForm"><span class="hide-on-mobile">Add New </span><i class="fa fa-plus"></i></a>
        </div>
        <div class="btn-group" id="divDelete">
            <a id="imgDelete" name="imgDelete" title="Delete" class="btn btn-block btn-primary btn-flat" href="#"><span class="hide-on-mobile">Delete </span><i class="fa fa-trash-o"></i></a>
        </div>
        <div class="btn-group" id="divActive">
            <a id="imgActive" name="imgActive" title="Active" class="btn btn-block btn-primary btn-flat"><span class="hide-on-mobile">Active </span><i class="fa fa-check"></i></a>
        </div>
        <div class="btn-group" id="divInactive">
            <a id="imgInactive" name="imgInactive" title="Inactive" class="btn btn-block btn-primary btn-flat"><span class="hide-on-mobile">Inactive </span><i class="fa fa-ban"></i></a>
		</div>
		<div class="btn-group">
            <a title="Show All" class="btn btn-block btn-primary btn-flat" onclick="showAllRecords();" href="./getAllDoctors"><span class="hide-on-mobile">Show All&nbsp;&nbsp;</span><i class="fa fa-th-list"></i></a>
        </div>
        <form class="navbar-form navbar-right" role="search" action="./searchDoctors">
		  <div class="form-group" align="right">
		  	<select id="ddlFilter" class="ddlFilter form-control input-sm input-small input-inline" selectedindex="0" name="searchKey">
			    <option value="-- Select --">-- Select --</option>
			    <option value="FirstName">First Name</option>
			    <option value="Email">Email</option>
			    <option value="Phone">Phone</option>
			    <option value="IsActive">Status</option>
		   </select> 
		    <input type="text" id="txtSearchField" name="searchValue" class="form-control input-sm input-small input-inline"/>
		  </div>
		  <button type="submit" class="btn btn-default">Search</button>
		</form>
	  </div>
	  <table class="table">
	  	<tr>
	  		<th>Doctor Id</th>
	  		<th>First Name</th>
	  		<th>Last Name</th>
	  		<th>Email</th>
	  		<th>Phone</th>
	  		<th>Status</th>
	  		<th>Edit</th>
	  		<th>Delete</th>
	  	</tr>
	  	<%-- <c:if test="${uiAdminsList >= '1'}"> --%>
		  	<c:forEach items="${uiDoctorBeanList}" var="doctorBean">
			  	<tr>
			  		<td>${doctorBean.doctorId}</td>
			  		<td>${doctorBean.firstName}</td>
			  		<td>${doctorBean.lastName}</td>
			  		<td>${doctorBean.email}</td>
			  		<td>${doctorBean.phoneNumber}</td>
			  		<td>${doctorBean.status}</td>
			  		<td><a href="./editDoctor?doctorId=${doctorBean.doctorId}"><div class="glyphicon glyphicon-edit"></div></a></td>
			  		<td><a href="./deleteDoctor?doctorId=${doctorBean.doctorId}"><div class="glyphicon glyphicon-trash"></div></a></td>
			  	</tr>
		  	</c:forEach>
	  	<%-- </c:if> --%>
	  </table>
	</div>
	<nav>
	  <ul class="pagination">
	  	<li>
	      <a href="./getAllDoctorsByPagening?currentPage=${loop.count-1}" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    
	  	<c:forEach items="${uiPageBarList}" varStatus="loop" var="pageBarNumber">
	  		<li><a href="./getAllDoctorsByPagening?currentPage=${loop.count}"><span>${loop.count}<span class="sr-only">(current)</span></span></a></li>
	  	</c:forEach>
	    
	    
	    <li>
	      <a href="./getAllDoctorsByPagening?currentPage=${loop.count+1}" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	  </ul>
	</nav>
</body>
</html>