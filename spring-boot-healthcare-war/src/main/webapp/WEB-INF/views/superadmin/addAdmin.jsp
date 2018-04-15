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
								<label class="active" id="login-form-link">Add Hospital</label>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form:form id="registration-form" action="./addAdmin" commandName="adminBean"  method="POST" >
									<div class="box box-primary">
				                    <div class="box-header"></div>
				                    <div class="box-body">
				                        <div class="message-box"></div>
				                        <div class="row">
				                            <div class="col-md-9">
				                            
				                            	<div class="row">
				                                    <div class="col-md-4">
				                                        <div class="form-group">
				                                            <label class="control-label"><label for="User_CompanyId">Hospital</label><span class="required">*</span></label>
				                                            <form:select class="form-control" path="hospitalName" style="">
																<form:option value="">-- Select Hospital --</form:option>
																<form:options items="${hospitalNameList}"></form:options>
															</form:select>
				                                            <span class="field-validation-valid" data-valmsg-for="User.CompanyId" data-valmsg-replace="true"></span>
				                                        </div>
				                                    </div>
				                                </div>
				                                
				                                <div class="row">
				                                    <div class="col-md-4">
				                                        <div class="form-group">
				                                            <label class="control-label"><label for="User_FirstName">First Name</label><span class="required">*</span></label>
				                                            <!-- <input class="form-control" data-val="true" data-val-required="The First Name field is required." id="User_FirstName" maxlength="50" name="User.FirstName" type="text" value=""> -->
				                                            <form:input class="form-control" path="firstName" type="text" value=""></form:input>
				                                            <span class="field-validation-valid" data-valmsg-for="User.FirstName" data-valmsg-replace="true"></span>
				                                        </div>
				                                    </div>
				                                    <div class="col-md-4">
				                                        <div class="form-group">
				                                            <label class="control-label"><label for="User_MiddleName">Middle Name</label>
				                                            <!-- <input class="form-control" data-val="true" data-val-required="The Middle Name field is required." id="User_MiddleName" maxlength="50" name="User.MiddleName" type="text" value=""> -->
				                                            <form:input class="form-control" path="middleName" type="text" value=""></form:input>
				                                            <span class="field-validation-valid" data-valmsg-for="User.MiddleName" data-valmsg-replace="true"></span>
														</div>
				                                    </div>
				                                    <div class="col-md-4">
				                                        <div class="form-group">
				                                            <label class="control-label"><label for="User_LastName">Last Name</label><span class="required">*</span></label>
				                                            <!-- <input class="form-control" data-val="true" data-val-required="The Last Name field is required." id="User_LastName" maxlength="50" name="User.LastName" type="text" value=""> -->
				                                            <form:input class="form-control" path="lastName" type="text" value=""></form:input>
				                                            <span class="field-validation-valid" data-valmsg-for="User.LastName" data-valmsg-replace="true"></span>
				                                        </div>
				                                    </div>
				                                </div>
				                                <div class="row">
				                                    <div class="col-md-4">
				                                        <div class="form-group">
				                                            <label class="control-label"><label for="User_Phone">Phone</label><span class="required">*</span></label>
				                                            <!-- <input class="form-control" data-val="true" data-val-required="The Phone field is required." id="User_Phone" maxlength="50" name="User.Phone" type="text" value=""> -->
				                                            <form:input class="form-control" path="phone" type="text" value=""/>
				                                            <span class="field-validation-valid" data-valmsg-for="User.Phone" data-valmsg-replace="true"></span>
				                                        </div>
				                                    </div>
				                                    <div class="col-md-4">
				                                        <div class="form-group">
															<label class="control-label"><label for="User_Email">Email (User Name)</label> <span class="required">*</span></label>
				                                            <!-- <input class="form-control" data-val="true" data-val-regex="Enter valid email." data-val-regex-pattern="^[\w\+\-\._]+@[\w\-\._]+\.\w{2,}$" data-val-required="The Email (User Name) field is required." id="User_Email" maxlength="50" name="User.Email" type="text" value=""> -->
				                                            <form:input class="form-control" path="email" type="text"  value=""></form:input>
				                                            <span class="field-validation-valid" data-valmsg-for="User.Email" data-valmsg-replace="true"></span>
				                                        </div>
													</div>
													<div class="col-md-4">
				                                        <div class="form-group">
															<label class="control-label"><label for="User_Email">Password</label> <span class="required">*</span></label>
				                                            <!-- <input class="form-control" data-val="true" data-val-regex="Enter valid email." data-val-regex-pattern="^[\w\+\-\._]+@[\w\-\._]+\.\w{2,}$" data-val-required="The Email (User Name) field is required." id="User_Email" maxlength="50" name="User.Email" type="text" value=""> -->
				                                            <form:input class="form-control" path="password" type="text"  value=""></form:input>
				                                            <span class="field-validation-valid" data-valmsg-for="User.Email" data-valmsg-replace="true"></span>
				                                        </div>
													</div>
												</div>
												<div class="row">
				                                    <div class="col-md-4">
				                                    	<div class="form-group">
															<form:radiobutton path="status" id="status" tabindex="1"  value="true" />Active
															<form:radiobutton path="status" id="status" tabindex="1"  value="false" />In Active
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit"  id="registration-submit" tabindex="4" class="form-control btn btn-registartion" value="Add">
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
		</div>
	</div>
</body>
</html>