<%@page import="jdk.nashorn.internal.runtime.JSONFunctions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="org.codehaus.jettison.json.JSONException"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Passenger Information</title>

<script type="text/javascript">
	function viewList() {
		if (document.getElementById('radio_pass').checked) {
			document.getElementById('lbl_flight_passngr').innerHTML = "Passenger Name : ";
			document.getElementById('txtbox_flight_pass').placeholder = "E.g. Chris";
			document.getElementById('datebox_flight_date').required = false;
			document.getElementById('dateDiv').style.display = 'none';
			document.getElementById('sorry').hidden = true;
		} else {
			document.getElementById('lbl_flight_passngr').innerHTML = "Flight Number : ";
			document.getElementById('txtbox_flight_pass').placeholder = "E.g. EY215";
			document.getElementById('dateDiv').style.display = 'block';
			document.getElementById('sorry').hidden = true;
			document.getElementById('datebox_flight_date').required = true;
		}
	}
</script>

</head>
<body>
	<jsp:include page="top.html" />
	<!-- Container Start -->
	<div class="container">
		<div class="row">
			<div class="box">
				<div class="col-lg-12">
					<h4 class="title">Passenger Information (Use Case 4/5)</h4>
					<form id="form1" method="post" action="UseCase4"
						class="form-horizontal">
						<div class="form-group">
							<input type="radio" id="radio_flight" value="flight"
								name="manifest_radio" checked onclick="viewList()"> View
							list of Flights <input type="radio" id="radio_pass"
								name="manifest_radio" value="passenger" onclick="viewList()">
							View List of Passengers
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" id="lbl_flight_passngr"
								for="txtbox_flight_pass">Flight Number : </label>
							<div class="col-sm-3">
								<input class="form-control" id="txtbox_flight_pass" type="text"
									name="txtbox_flight_pass" placeholder="E.g. EY215"
									required="true" maxlength="6" />
							</div>
						</div>
						<div class="form-group" id="dateDiv">
							<label class="col-sm-3 control-label" id="lbl_flight_date"
								for="txtbox_flight_no">Flight Date : </label>
							<div class="col-sm-3">
								<input class="form-control" id="datebox_flight_date" type="date"
									name="datebox_flight_date" required="true" min="2014-01-01"
									max="2020-12-31">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-3">
								<input id="submit_get_details" type="submit"
									name="submit_get_details" value="Get Details"
									class="btn btn-default" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%
		if (request.getAttribute("UseCase") != null
				&& request.getAttribute("UseCase").toString()
						.equalsIgnoreCase("passenger")) {
			if(request.getAttribute("json").toString().equals("[]")) {
	%>
	<h5 style="color: white;" align="center" id="sorry">-- Sorry No
		result found for your request ---</h5>
	<%
		} else {
	%>
	<h5 style="color: white;" align="center" id="sorry">
		Below is the Travel information for :
		<%=request.getAttribute("flight_pass")%></h5>
	<div class="col-md-offset-3 col-md-6">
		<table class="table table-bordered" style="color: white">
			<tr>
				<th>FLIGHT NUMBER</th>
				<th width="100">FLIGHT DATE</th>
				<th>SEAT NUMBER</th>
				<th>DEPARTURE AIRPORT CODE</th>
				<th>ARRIVAL AIRPORT CODE</th>
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
				<td><%=obj.getString("Flight_number")%></td>
				<td><%=obj.getString("Date")%></td>
				<td><%=obj.getString("Seat_number")%></td>
				<td><%=obj.getString("Departure_airport_code")%></td>
				<td><%=obj.getString("Arrival_airport_code")%></td>
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
	<%
		} else if (request.getAttribute("UseCase") != null
				&& request.getAttribute("UseCase").toString()
						.equalsIgnoreCase("flight")) {
			if(request.getAttribute("json").toString().equals("[]")) {
	%>
	<h5 style="color: white;" align="center" id="sorry">-- Sorry No
		result found for your request ---</h5>
	<%
		} else {
	%>
	<h5 style="color: white;" align="center">
		Below is the passenger list for :
		<%=request.getAttribute("flight_pass")%>
		on
		<%=request.getAttribute("flight_date")%></h5>
	<div class="col-md-offset-3 col-md-6">
		<table class="table table-bordered" style="color: white">
			<tr>
				<th>CUSTOMER NAME</th>
				<th>CUSTOMER PHONE</th>
				<th>SEAT NUMBER</th>
				<th>DEPARTURE AIRPORT CODE</th>
				<th>ARRIVAL AIRPORT CODE</th>
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
				<td><%=obj.getString("Customer_name")%></td>
				<td><%=obj.getString("Customer_phone")%></td>
				<td><%=obj.getString("Seat_number")%></td>
				<td><%=obj.getString("Departure_airport_code")%></td>
				<td><%=obj.getString("Arrival_airport_code")%></td>
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
	<%
		}
	%>
</body>
</html>