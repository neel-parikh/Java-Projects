<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
</head>
<body>
	<jsp:include page="top.html" />
	<!-- Container Start -->
	<div class="container">
		<div class="row">
			<div class="box">
				<div class="col-lg-12">
					<h4 class="title">Flight numbers and weekdays of all flight
						(Use Case 1)</h4>

					<form id="form1" method="post" action="UseCase1"
						class="form-horizontal">
						<div class="form-group">
							<label for="txtbox_dep_airport_code"
								class="col-sm-3 control-label">Departure Airport Code :
							</label>
							<div class="col-sm-3">
								<input class="form-control" id="txtbox_dep_airport_code"
									type="text" name="txtbox_dep_airport_code"
									placeholder="Departure Code" required="true" maxlength="3">
							</div>
						</div>
						<div class="form-group">
							<label for="txtbox_arr_airport_code"
								class="col-sm-3 control-label">Arrival Airport Code : </label>
							<div class="col-sm-3">
								<input class="form-control" id="txtbox_arr_airport_code"
									type="text" name="txtbox_arr_airport_code" value=""
									placeholder="Arrival Code" required="true" maxlength="3">
							</div>
						</div>
						
						<div class="form-group">
							<label for="chkbox_legs"
								class="col-sm-3 control-label">No of Legs : </label>
							<div class="checkbox col-sm-5">
							
								<label><input id="chkbox_legs_0"
									type="checkbox" name="chkbox_legs" value="0" checked="checked"> 0 </label>
								<br><label><input id="chkbox_legs_1"
									type="checkbox" name="chkbox_legs" value="1"> 1</label>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-3">
								<input id="submit_get_details" type="submit"
									name="submit_get_details" value="Get Flight Details"
									class="btn btn-default" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%
		if (request.getAttribute("UseCase") != null) {
			if (request.getAttribute("json").toString().equals("{}")) {
	%>
	<h5 style="color: white;" align="center">-- Sorry No result found
		for your request ---</h5>
	<%
		} else {
			HttpSession sess = request.getSession();
	%>
	<div class="col-md-offset-3 col-md-6">

		<!-- DIRECT FLIGHTS CHECK -->
		<%
			JSONObject total_obj = new JSONObject(request.getAttribute(
							"json").toString());
					JSONObject obj = null;
					JSONArray jsonArr = null, subJsonArr = null;
					if (request.getAttribute("json").toString()
							.contains("Direct")) {
		%>
		<h5 style="color: white;" align="center">
			Below are the Direct flights available from :
			<%=request.getAttribute("depcode")%>
			and Arriving at :
			<%=request.getAttribute("arrcode")%></h5>
		<table class="table table-bordered" style="color: white">
			<tr>
				<th>FLIGHT NUMBER</th>
				<th>DEPARTURE AIRPORT CODE</th>
				<th>ARRIVAL AIRPORT CODE</th>
				<th>AIRLINE NAME</th>
				<th>WEEKDAYS</th>
			</tr>
			<%
				jsonArr = total_obj.getJSONArray("Direct");
							for (int k = 0; k < jsonArr.length(); k++) {
								obj = jsonArr.getJSONObject(k);
			%>
			<tr>
				<td><%=obj.getString("Flight_number")%></td>
				<td><%=obj.getString("Departure_airport_code")%></td>
				<td><%=obj.getString("Arrival_airport_code")%></td>
				<td><%=obj.getString("Airline")%></td>
				<td><%=obj.getString("Weekdays")%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			}
				else if (sess.getAttribute("legs") != "one") {
		%>
		<h5 style="color: white;" align="center">
			Sorry !! No direct flights available from :
			<%=request.getAttribute("depcode")%>
			to
			<%=request.getAttribute("arrcode")%></h5>
		<hr>
		<%
			}
					//INDIRECT FLIGHTS	
					if (total_obj.length() >= 1) {
						//Parse the JSON Data

						for (int i = 0; i < total_obj.names().length(); i++) {
							if (!total_obj.names().getString(i)
									.equalsIgnoreCase("direct")) {
		%>
		<h5 style="color: white;" align="center">
			Below are the flights available via :
			<%=total_obj.names().getString(i)%></h5>
		<table class="table table-bordered" style="color: white">
			<tr>
				<th>FLIGHT NUMBER</th>
				<th>DEPARTURE AIRPORT CODE</th>
				<th>ARRIVAL AIRPORT CODE</th>
				<th>AIRLINE NAME</th>
				<th>WEEKDAYS</th>
			</tr>
			<%
				jsonArr = total_obj.getJSONArray(total_obj
											.names().getString(i));
									System.out.println(jsonArr.toString());
									for (int j = 0; j < jsonArr.length(); j++) {
										subJsonArr = jsonArr.getJSONArray(j);
										for (int h = 0; h < subJsonArr.length(); h++) {
											obj = subJsonArr.getJSONObject(h);
			%>
			<tr>
				<td><%=obj
												.getString("Flight_number")%></td>
				<td><%=obj
												.getString("Departure_airport_code")%></td>
				<td><%=obj
												.getString("Arrival_airport_code")%></td>
				<td><%=obj.getString("Airline")%></td>
				<td><%=obj.getString("Weekdays")%></td>
			</tr>
			<%
				}
									}
			%>
		</table>
		<%
			}
						}
					}
				}
			}
		%>
	</div>
</body>
</html>