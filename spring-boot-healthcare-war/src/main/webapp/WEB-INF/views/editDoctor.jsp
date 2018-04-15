<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%-- <form:form action="updateDoctor" commandName="docBean">
<core:set value="${doctorBean}" var="dbean"></core:set>
DoctorId:<form:input path="docId"/>${dbean.getDocId()}<br>
DoctorName:<form:input path="docName"/>${dbean.getDocName()}<br>
Gender:<form:input path="gender"/>${dbean.getGender()}<br>
Email:<form:input path="email"/>${dbean.getEmail()}<br>
<input type="submit" value="update">
<input type="submit" value="cancel">
</form:form> --%>
<form:form action="updateDoctor" modelAttribute="doctorBean">
DoctorId:<form:input path="docId"/><br>
DoctorName:<form:input path="docName"/><br>
Gender:<form:input path="gender"/><br>
Email:<form:input path="email"/><br>
<input type="submit" value="update">
<input type="submit" value="cancel">
</form:form>

</body>
</html>