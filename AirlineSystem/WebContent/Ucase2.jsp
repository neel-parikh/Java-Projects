<%@page import="jdk.nashorn.internal.runtime.JSONFunctions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="org.codehaus.jettison.json.JSONException"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seat Availability</title>
</head>
<body>
	<jsp:include page="top.html" />
	<!-- Container Start -->
	<div class="container">
		<div class="row">
			<div class="box">
				<div class="col-lg-12">
					<h4 class="title">Seat Availability (Use Case 2)</h4>

					<form id="form1" method="post" action="UseCase2"
						class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="txtbox_flight_no">Flight
								Number : </label>
							<div class="col-sm-4">
								<input class="form-control" id="txtbox_flight_no" type="text"
									name="txtbox_flight_no" placeholder="E.g. EY215" required="true" maxlength="6">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" for="datebox_flight_date">Search
								Date : </label>
							<div class="col-sm-4">
								<input class="form-control" id="datebox_flight_date" type="date"
									name="datebox_flight_date" min="2014-01-01" max="2020-12-31"
									required="true">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-3">
								<input id="submit_get_details" type="submit"
									name="submit_get_details" value="Get Availability"
									class="btn btn-default" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%
	if(request.getAttribute("UseCase")!=null)
	{
		if(request.getAttribute("json").toString().equals("[]"))
		{
		%>
		<h5 style="color: white;" align="center"> -- Sorry No result found for your request --- </h5>	
		<%}
		else
		{%>
	<h5 style="color: white;" align="center"> Seat Availability for Flight No: 
		<%=request.getAttribute("flight_no")%>
		for Date :
		<%=request.getAttribute("flight_date")%></h5>
	<div class="col-md-offset-3 col-md-6">
	<table class="table table-bordered" style="color:white">
       <tr>
				<th>DEPARTURE AIRPORT CODE</th>
				<th>SCHEDULED DEPARTURE TIME</th>
				<th>ARRIVAL AIRPORT CODE</th>
				<th>SCHEDULED ARRIVAL TIME</th>
				<th>NO OF SEATS AVAILABLE</th>				
			</tr>
			<%
				try {
						//Parse the JSON Data
						JSONArray jsonArr = new JSONArray(request.getAttribute(
								"json").toString());
						JSONObject obj = null;
						for (int i = 0; i < jsonArr.length(); i++) {
							obj = jsonArr.getJSONObject(i);
			%>
			<tr>
				<td><%=obj.getString("Departure_airport_code")%></td>
				<td><%=obj.getString("Departure_time")%></td>
				<td><%=obj.getString("Arrival_airport_code")%></td>
				<td><%=obj.getString("Arrival_time")%></td>
				<td><%=obj.getString("Number_of_available_seats")%></td>
			</tr>
			<%
				}
					} catch (JSONException e) {
						e.printStackTrace();
					}
		}
			%>
</table>
</div>
<%} %>
</html>