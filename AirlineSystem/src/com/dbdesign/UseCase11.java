package com.dbdesign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UseCase11
 */
@WebServlet("/UseCase1_2")
public class UseCase11 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UseCase11() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String depcode = request.getParameter("txtbox_dep_airport_code");
		String arrcode = request.getParameter("txtbox_arr_airport_code");
		String flight_date = request.getParameter("datebox_flight_date");
		String legs;
		String chkbox_leg[] = request.getParameterValues("chkbox_legs");
		if(chkbox_leg.length == 2)
		{
			legs = "both";
		}
		else
		{
			if(chkbox_leg[0].equals("1"))
				legs = "one";
			else
				legs = "zero";
		}
		
		String webServiceURL = "http://localhost:8080/AirlineSystem/new/APICall/UseCase11?flight_date="
				+ flight_date + "&depcode=" + depcode + "&arrcode=" + arrcode + "&legs=" + legs;
		System.out.println(webServiceURL);
		URL url = new URL(webServiceURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		String jsonData = "";

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		// Get the JSON data in string
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String line;
		while ((line = br.readLine()) != null) {
			jsonData += line;
		}
		conn.disconnect();
		//System.out.println(jsonData);
		request.setAttribute("json", jsonData);
		request.setAttribute("depcode", depcode);
		request.setAttribute("arrcode", arrcode);
		HttpSession sess = request.getSession();
		sess.setAttribute("legs", legs);
		request.setAttribute("flight_date", flight_date);
		request.setAttribute("UseCase", "11");
		request.getRequestDispatcher("Ucase11.jsp").forward(request, response);
	}
}
