<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" 
crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
<table border="2" height="100%" width="100%">
<tr height="10">
<td  id="headerid"  colspan="2" bgcolor="skyblue">
<tiles:insertAttribute name="header"></tiles:insertAttribute>
</td>
</tr>
<%-- <tr>
<td  id="headerid2"  colspan="2">
<tiles:insertAttribute name="header2"></tiles:insertAttribute>
</td>
</tr> --%>
<tr>
<td  id="menuid" bgcolor="skyblue">
<tiles:insertAttribute name="menu"></tiles:insertAttribute>
</td>
<td  id="bodyid">
<tiles:insertAttribute name="body"></tiles:insertAttribute>
</td>
</tr>

<tr>
<td  id="footerid"  colspan="2">
<tiles:insertAttribute name="footer" ></tiles:insertAttribute>
</td>
</tr>
</table>
<%-- <div class="page">
        <tiles:insertAttribute name="header" />
         <tiles:insertAttribute name="menu" />
       <!--  <div class="content"> -->
           
            <tiles:insertAttribute name="body" />
        <!-- </div> -->
        <tiles:insertAttribute name="footer" />
    </div> --%>

</body>
</html>