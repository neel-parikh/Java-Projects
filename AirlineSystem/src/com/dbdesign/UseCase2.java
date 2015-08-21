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

/**
 * Servlet implementation class UseCase3
 */
@WebServlet("/UseCase2")
public class UseCase2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UseCase2() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String flight_no = request.getParameter("txtbox_flight_no");
		String flight_date = request.getParameter("datebox_flight_date");
		
		String webServiceURL = "http://localhost:8080/AirlineSystem/new/APICall/UseCase2?flight_no="+ flight_no + "&flight_date=" + flight_date;
		URL url = new URL(webServiceURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		String jsonData="";
		
		if (conn.getResponseCode() != 200) 
		{
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		
		//Get the JSON data in string
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
		
		String line;
		while ((line = br.readLine()) != null) 
		{
			jsonData+=line;
		}
		conn.disconnect();
		System.out.println(jsonData);
		request.setAttribute("json", jsonData);
		request.setAttribute("UseCase", "2");		
		request.setAttribute("flight_no", flight_no);
		request.setAttribute("flight_date", flight_date);
		request.getRequestDispatcher("Ucase2.jsp").forward(request, response);
	}

}
