
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript">
var app_path = '${pageContext.request.contextPath}';
//alert(app_path);
function getDeptNames(value) {
	console.log("hai  "+value);
	$.ajax({
		  type: "GET",
		  url:app_path+"/getDeptNames",
		  data:{ role:value},
		  success: function(response){
		    //console.log("ajax call:   "+response);
		   // var deptSelectTag = document.getElementById('deptSelectId');
		   
		     document.getElementById('lableId').innerHTML = 'Departments';
			  var myDiv = document.getElementById("deptId");
				var selectTag = document.createElement("select");
				selectTag.setAttribute('id', "deptSelectId");
				 $("#deptSelectId").remove();
				 if(response.length<1){
					 $("#deptSelectId").hide();
					 $("#lableId").hide();
				 }else{
					 $("#lableId").show();
				myDiv.appendChild(selectTag);				
				for (var i = 0; i < response.length; i++) {
				    var option = document.createElement("option");
				    option.setAttribute('id', "deptOptionId");
				    option.setAttribute("value", response[i]);
				    option.text = response[i];
				    selectTag.appendChild(option);
				}}

		  }
		});
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- <script type="text/javascript">
function signup(){
	/* var fname1=document.signupform.fn.value; */
	var fname1=document.getElementById("firstname").value;
	var mname1=document.getElementById("mname").value;
	var lname1=document.getElementById("lname").value;
	var email1=document.getElementById("email").value;
	var userid1=document.getElementById("userid").value;
	var password1=document.getElementById("password").value;
	var mnumber1=document.getElementById("mnumber").value;
 	
 var message="please enter name";
	if(fname1==''||null){
		window.alert("please enter valid firstname");
		document.getElementById("sfname").innerText=message;
		return false;
	} else if(mname1==""||null){
		window.alert("please enter valid middlename");	
	}else if(lname1==""||null){
		window.alert("please enter valid lastname");
	}else if(email1==""||null){
		window.alert("please enter valid email");
	}else if(userid1==""||null){
		window.alert("please enter valid userid");
	}else if(password1==""||null){
		window.alert("please enter valid password");
	}else if(mnumber1==""||null){
		window.alert("please enter valid mobilenumber");
	}
	
}

</script> -->
</head>
<body>
 <form:form action="signupPage" commandName="userProfBean" method="POST" name="signupform" onsubmit="return signup()">
First Name<span style= "color:red" >*</span>:
<form:input path="firstName" id="firstname"/><form:errors path="firstName"/>
<span id="sfname"></span><br>
Middile Name:<form:input  path="middleName" id="mname"/><br>
Lastname<span style= "color:red" >*</span>:<form:input path="lastName" id="lname"/><form:errors path="lastName"></form:errors><br>
Email<span style= "color:red" >*</span>:<form:input path="email" id="email"/><form:errors path="email"></form:errors><span style= "color:red" >${message}</span><br>
User Id<span style= "color:red" >*</span>:<form:input path="userId" id="userid"/><form:errors path="userId"></form:errors><span style= "color:red" >${message1}</span><br>
Password<span style= "color:red" >*</span>:<form:input path="password" id="password"/><form:errors path="password"></form:errors><br>
Mobile No<span style= "color:red" >*</span>:<form:input path="mobilenumber" id="mnumber"/><form:errors path="mobilenumber"/><br>
<form:radiobutton path="gender"  value="male" checked="checked"/>Male
<form:radiobutton path="gender" value="female"/>Female<br>

<%-- role:<form:select path="role" onchange="getDeptNames(this.value)"><br>
<form:option value="-" label="--Select role--"/>
<form:options items="${roleList}"/>
</form:select><br>
<label id="lableId"></label><div id="deptId"></div>
 --%>

<%-- department:<form:select path="department"><br>
<form:option value="-" label="--Select department--"/>
<form:options items="${deptList}"/>
</form:select> --%><br>
<form:checkbox path="acceptTerm" value="accept terms and conditions" />accept terms and conditions<br><br><br><br><br>
<input type="submit" value="Submit" id="sid"/>
<input type="submit" value="Cancel"/><br><br><br><br>
<div style="color: green">
${message}
</div>
</form:form>
<%-- <form action="/savedepartmet">
First Name<span style= "color:red" >*</span>:<input type="text" name="firstname"><br>
Middile Name:<input type="text" name="lastname"><br>
Last Name<span style= "color:red" >*</span>:<input type="text" name="lastname"><br>
Email<span style= "color:red" >*</span>:<input type="text" name="email"><br>
UserId<span style= "color:red" >*</span>:<input type="text" name="userid"><br>
Password<span style= "color:red" >*</span>:<input type="password" name="pwd"><br>
MobileNumber<span style= "color:red" >*</span>:<input type="text" name="mnumber"><br>
Gender<span style= "color:red" >*</span>:<input type="radio" name="radioValue" checked="checked">male<input type="radio" name="radioValue">female<br>
Department:<select style="width:300px">
<option>${deptList}</option>
</select>
<core:forEach items="${deptList}" var="deptbean">
<option>${deptbean.getDeptName()}</option>
</core:forEach></select>
<br>
<input type="checkbox" name="chbox"><span>accept terms and conditions</span><br>
<input type="submit" value="Submit"/>signupPage
<input type="submit" value="Cancel"/>
</form> --%>

</body>
</html>