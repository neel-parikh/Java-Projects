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

@WebServlet("/UseCase4")
public class UseCase4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UseCase4() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String info_type = request.getParameter("manifest_radio");
		String flight_pass = request.getParameter("txtbox_flight_pass");
		String flight_date = request.getParameter("datebox_flight_date");
		
		String webServiceURL = "http://localhost:8080/AirlineSystem/new/APICall/UseCase4?info_type="
		+ info_type + "&flight_date=" + flight_date + "&flight_pass=" + flight_pass;
		
		URL url = new URL(webServiceURL);
		System.out.println(webServiceURL);
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
		request.setAttribute("json", jsonData);
		request.setAttribute("UseCase", info_type);		
		request.setAttribute("flight_date", flight_date);
		request.setAttribute("flight_pass", flight_pass);
		request.getRequestDispatcher("Ucase4.jsp").forward(request, response);
	}
}
