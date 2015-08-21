<%@page import="jdk.nashorn.internal.runtime.JSONFunctions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="org.codehaus.jettison.json.JSONException"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Airline Reservation System</title>
<link href="css/table.css" rel="stylesheet" />
</head>
<body>
	<%
		if (request.getAttribute("UseCase").equals("1")) {
	%>
	<jsp:include page="Ucase1.jsp" />
	<div class="table_css" align="center">
		<h5 style="color: white;"> List of Flight numbers and names Departing from : 
		<%=request.getAttribute("depcode")%>
		and Arriving at :
		<%=request.getAttribute("depcode")%></h5>
		<table datapagesize="1">
			<tr>
				<th>Flight Number</th>
				<th>Airline Name</th>
				<th>Weekdays</th>
			</tr>
			<%
				try {
						//Parse the JSON Data
						JSONArray jsonArr = new JSONArray(request.getAttribute(
								"jsonData").toString());
						JSONObject obj = null;
						for (int i = 0; i < jsonArr.length(); i++) {
							obj = jsonArr.getJSONObject(i);
			%>
			<tr>
				<td><%=obj.getString("Flight_number")%></td>
				<td><%=obj.getString("Airline")%></td>
				<td><%=obj.getString("Weekdays")%></td>
			</tr>
			<%
				}
					} catch (JSONException e) {
						e.printStackTrace();
					}
			%>
		</table>
		<br>
		<br>
		<br>
		<br>
		<br>
	</div>
	<%
		}
	%>
</body>
</html>