<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">							
									<label class="active" id="login-form-link">Update Doctor</label>
							</div>
							
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form:form id="registration-form" action="./updateDoctor" commandName="doctorBean" method="POST">
		
									<div class="form-group">
										<form:input path="doctorId" type="hidden"  id="doctorId" tabindex="1" class="form-control" placeholder="HospitalId" value="" ></form:input>
									</div>
									<div class="form-group">
										<form:input path="firstName" type="text"  id="firstName" tabindex="1" class="form-control" placeholder="FirstName" value=""></form:input>
									</div>
									<div class="form-group">
										<form:input path="middleName" type="text"  id="middleName" tabindex="1" class="form-control" placeholder="MiddleName" value=""></form:input>
									</div>
									<div class="form-group">
										<form:input path="lastName" type="text"  id="lastName" tabindex="1" class="form-control" placeholder="LastName" value=""></form:input>
									</div>
									<div class="form-group">
										<form:input path="email" type="text"  id="email" tabindex="1" class="form-control" placeholder="Email" value=""></form:input>
									</div>
									<div class="form-group">
										<form:input path="password" type="text"  id="password" tabindex="1" class="form-control" placeholder="Password" value=""></form:input>
									</div>
									<div class="form-group">
										<form:input path="phoneNumber" type="text"  id="phoneNumber" tabindex="1" class="form-control" placeholder="PhoneNumber" value=""></form:input>
									</div>
									<div class="form-group">
										<c:if test="${doctorBean.status == true}">
											<form:radiobutton  path="status" id="status" tabindex="1"  value="true" />Active
											<form:radiobutton path="status" id="status" tabindex="1"  value="false" disabled="false"/>In Active
										</c:if>
										<c:if test="${doctorBean.status == false}">
											<form:radiobutton path="status" id="status" tabindex="1"  value="true" disabled="false" />Active
											<form:radiobutton path="status" id="status" tabindex="1"  value="false" />In Active
										</c:if>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit"  id="updation-submit" tabindex="4" class="form-control btn btn-updation" value="Update">
											</div>
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
        <div class="container">

            <div class="row row-offcanvas row-offcanvas-right">

                <div class="col-xs-12 col-sm-9">
               
                 <hr>
	 
	  <footer>
                <p>© Company 2014</p>
      </footer>
	</div>
	</div></div>
	
</body>
</html>