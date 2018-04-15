<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table  height="100%">
<tr>
<td  id="headerid" >
<tiles:insertAttribute name="header"></tiles:insertAttribute>
</td>
</tr>
<tr height="100%">
<td  id="bodyid" >
<tiles:insertAttribute name="body"></tiles:insertAttribute>
</td>
</tr>

<tr height="100%">
<td  >

</td>
</tr>

<tr height="100%">
<td  id="footerid" >
<tiles:insertAttribute name="footer" ></tiles:insertAttribute>
</td>
</tr>
</table>

</body>
</html>