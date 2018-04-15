<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="saveInPatientdetails" commandName="patientBean" >
PatientName:<form:input path="pname"></form:input><br>
<%--DateOfBirth:<form:input path="dateOfBirth"/><br> --%>
Gender:<form:radiobutton path="gender"  value="male" checked="checked"/>Male
<form:radiobutton path="gender" value="female"/>Female<br>
Email:<form:input path="email"/><br>
<%-- RegDate:<form:input path="regDate"/><br> --%>
MobileNumber:<form:input path="mobile"/><br>
Role:<form:select path="role"><br>
<form:option value="-" label="--Select role--"/>
<form:options items="${pnameList}"/>
</form:select><br>
<input type="submit" value="add">
<input type="submit" value="cancel">
</form:form>
</body>
</html>