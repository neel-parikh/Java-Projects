package com.restfulws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

@Path("/APICall")
public class RESTAirline {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/UseCase1")
	public String getFlightDetails(@QueryParam("depcode") String depcode,
			@QueryParam("arrcode") String arrcode,
			@QueryParam("legs") String legs) {
		try {
			// Loading class adapter
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/airline_reservation", "root",
					"tiger");

			JSONObject obj = null;
			JSONObject total_obj = null;
			JSONArray total_jsonArr = null, mid_Arr = null;
			total_obj = new JSONObject();
			if (legs.equals("zero") || legs.equals("both")) {
				PreparedStatement ps = con
						.prepareStatement("select distinct f.flight_number,f.airline,f.Departure_airport_code,i.departure_time, "
								+ "f.Arrival_airport_code,i.arrival_time,f.weekdays "
								+ "from flight f, flight_instance i where "
								+ "i.flight_number = f.flight_number and f.arrival_airport_code = ? and f.departure_airport_code = ?");
				ps.setString(1, arrcode);
				ps.setString(2, depcode);
				System.out.println(ps);
				ResultSet rs = ps.executeQuery();
				if (rs.isBeforeFirst()) {
					total_jsonArr = new JSONArray();
					while (rs.next()) {
						obj = new JSONObject();
						for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
							obj.put(rs.getMetaData().getColumnName(i), rs
									.getString(rs.getMetaData()
											.getColumnName(i)));
							System.out.println(rs.getString(i));
						}
						total_jsonArr.put(obj);
					}
					total_obj.put("Direct", total_jsonArr);
				}
				if (legs.equals("zero"))
					return total_obj.toString();
			}

			if (legs.equals("both") || legs.equals("one")) {
				PreparedStatement ps1 = con
						.prepareStatement("select distinct f.arrival_airport_code,i.arrival_time "
								+ "from flight f,flight_instance i where f.flight_number = i.flight_number "
								+ "and f.Arrival_airport_code "
								+ "in (select f.departure_airport_code from flight f,flight_instance i "
								+ "where f.flight_number = i.flight_number "
								+ "and f.arrival_airport_code = ?) "
								+ "and f.departure_airport_code = ?");
				ps1.setString(1, arrcode);
				ps1.setString(2, depcode);
				System.out.println(depcode + "  " + arrcode);
				ResultSet rs1 = ps1.executeQuery();

				PreparedStatement ps2 = null, ps3 = null;
				ResultSet rs2, rs3;
				LinkedHashMap<String, List<ResultSet>> hm = new LinkedHashMap<String, List<ResultSet>>();
				while (rs1.next()) {
					List<ResultSet> ls = new ArrayList<ResultSet>();
					ls.clear();
					ps2 = con
							.prepareStatement("select distinct f.flight_number,f.airline,f.departure_airport_code,i.departure_time,f.arrival_airport_code,i.arrival_time,i.Date"
									+ ",f.weekdays "
									+ " from flight f,flight_instance i"
									+ " where f.flight_number=i.flight_number "
									+ "and f.departure_airport_code = ? and f.arrival_airport_code = ?");
					ps2.setString(2, rs1.getString(1));
					ps2.setString(1, depcode);
					System.out.println(ps2.toString());
					rs2 = ps2.executeQuery();

					ps3 = con
							.prepareStatement("select distinct f.flight_number,f.airline,f.departure_airport_code,i.departure_time,f.arrival_airport_code,i.arrival_time, i.Date, "
									+ "f.weekdays"
									+ " from flight f,flight_instance i"
									+ " where f.flight_number=i.flight_number "
									+ "and f.departure_airport_code = ? and f.arrival_airport_code = ?");
					ps3.setString(1, rs1.getString(1));
					ps3.setString(2, arrcode);
					System.out.println(ps3.toString());
					rs3 = ps3.executeQuery();
					ls.add(rs2);
					ls.add(rs3);
					hm.put(rs1.getString(1), ls);

				}
				/*
				 * HashMap<String, List<String>> hmlist = new HashMap<String,
				 * List<String>>(); int total_cols =
				 * rs.getMetaData().getColumnCount(); in while(rs.next()) {
				 * for(int i=1;i<=total_cols;i++) { hmlist.pu
				 * hm.put(rs.getMetaData().getColumnName(i),
				 * rs.getString(rs.getMetaData().getColumnName(i))); } } Gson
				 * gson = new Gson(); return gson.toJson(hm);
				 */

				Iterator<Entry<String, List<ResultSet>>> it = hm.entrySet()
						.iterator();

				while (it.hasNext()) {
					total_jsonArr = new JSONArray();
					Entry<String, List<ResultSet>> entry = it.next();

					List<ResultSet> al = entry.getValue();
					for (int j = 0; j < al.size(); j++) {
						ResultSet r1 = al.get(j);
						mid_Arr = new JSONArray();
						int total_cols = r1.getMetaData().getColumnCount();
						while (r1.next()) {
							obj = new JSONObject();
							for (int cnt = 1; cnt <= total_cols; cnt++) {
								System.out.println(r1.getMetaData()
										.getColumnName(cnt)
										+ "   "
										+ r1.getString(cnt));
								obj.put(r1.getMetaData().getColumnName(cnt),
										r1.getString(cnt));
							}
							mid_Arr.put(obj);
						}
						total_jsonArr.put(mid_Arr);
					}
					total_obj.put(entry.getKey(), total_jsonArr);
				}
				return total_obj.toString();
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/UseCase2")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSeatsInfo(@QueryParam("flight_no") String flight_no,
			@QueryParam("flight_date") String flight_date) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/airline_reservation", "root",
					"tiger");

			PreparedStatement ps = con
					.prepareStatement("SELECT f.Departure_airport_code,"
							+ "i.Departure_time,f.Arrival_airport_code,i.Arrival_time, i.Number_of_available_seats "
							+ "FROM FLIGHT f,FLIGHT_INSTANCE i "
							+ "WHERE i.Flight_number = f.flight_number and i.Flight_number = ? AND i.Date = ?");
			ps.setString(1, flight_no);
			ps.setString(2, flight_date);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			int total_cols = rs.getMetaData().getColumnCount();
			JSONArray jsonArr = new JSONArray();
			JSONObject obj = null;
			while (rs.next()) {
				obj = new JSONObject();
				for (int i = 1; i <= total_cols; i++) {
					obj.put(rs.getMetaData().getColumnName(i),
							rs.getString(rs.getMetaData().getColumnName(i)));
				}
				jsonArr.put(obj);
			}
			return jsonArr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/UseCase3")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFareInfo(@QueryParam("flight_no") String flight_no) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/airline_reservation", "root",
					"tiger");

			PreparedStatement ps = con
					.prepareStatement("SELECT Airline,Fare_code, Amount, Restrictions "
							+ "FROM FARE f1,FLIGHT f2 WHERE f1.flight_number = f2.flight_number and f1.Flight_number = ?");
			ps.setString(1, flight_no);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			int total_cols = rs.getMetaData().getColumnCount();
			JSONArray jsonArr = new JSONArray();
			JSONObject obj = null;
			while (rs.next()) {
				obj = new JSONObject();
				for (int i = 1; i <= total_cols; i++) {
					obj.put(rs.getMetaData().getColumnName(i),
							rs.getString(rs.getMetaData().getColumnName(i)));
				}
				jsonArr.put(obj);
			}
			return jsonArr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/UseCase4")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPassengerManifest(
			@QueryParam("info_type") String info_type,
			@QueryParam("flight_date") String flight_date,
			@QueryParam("flight_pass") String flight_pass) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/airline_reservation", "root",
					"tiger");

			PreparedStatement ps;

			if (info_type.equals("flight")) {
				ps = con.prepareStatement("SELECT a.Customer_name, "
						+ "a.Customer_phone, a.Seat_number, c.Departure_airport_code, c.Arrival_airport_code "
						+ "FROM SEAT_RESERVATION a, FLIGHT_INSTANCE b, FLIGHT c "
						+ "where a.Flight_number = b.Flight_number AND b.Flight_number = c.Flight_number "
						+ "AND a.Date = b.Date AND a.Flight_number = ? AND a.Date = ?");
				ps.setString(1, flight_pass);
				ps.setString(2, flight_date);
				System.out.println(ps);
			} else {
				ps = con.prepareStatement("SELECT a.Flight_number, a.Seat_number, "
						+ "a.Date, c.Departure_airport_code, c.Arrival_airport_code "
						+ "FROM SEAT_RESERVATION a, FLIGHT_INSTANCE b, FLIGHT c "
						+ "WHERE b.Flight_number = c.Flight_number "
						+ "AND a.Flight_number = b.Flight_number AND a.Date = b.Date "
						+ "AND a.Customer_name = ?");
				ps.setString(1, flight_pass);
				System.out.println(ps);
			}
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			int total_cols = rs.getMetaData().getColumnCount();
			JSONArray jsonArr = new JSONArray();
			JSONObject obj = null;
			while (rs.next()) {
				obj = new JSONObject();
				for (int i = 1; i <= total_cols; i++) {
					obj.put(rs.getMetaData().getColumnName(i),
							rs.getString(rs.getMetaData().getColumnName(i)));
				}
				jsonArr.put(obj);
			}
			return jsonArr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/UseCase11")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFlightInfo(@QueryParam("depcode") String depcode,
			@QueryParam("arrcode") String arrcode,
			@QueryParam("flight_date") String flight_date,
			@QueryParam("legs") String legs) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/airline_reservation", "root",
					"tiger");

			JSONObject obj = null;
			JSONObject total_obj = null;
			JSONArray total_jsonArr = null, mid_Arr = null;
			total_obj = new JSONObject();

			// Convert String to date
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(format.parse(flight_date));
			System.out.println(cal.getTime());
			cal.add(Calendar.DATE, 1);
			System.out.println(Calendar.DATE);
			String newDate = format.format(cal.getTime());
			System.out.println(newDate);

			if (legs.equals("zero") || legs.equals("both")) {
				PreparedStatement ps = con
						.prepareStatement("select distinct f.flight_number,f.airline,f.Departure_airport_code,i.departure_time, "
								+ "f.Arrival_airport_code,i.arrival_time,f.weekdays "
								+ "from flight f, flight_instance i where "
								+ "i.flight_number = f.flight_number and f.arrival_airport_code = ? and f.departure_airport_code = ? and "
								+ "i.date = ?");
				ps.setString(1, arrcode);
				ps.setString(2, depcode);
				ps.setString(3, flight_date);
				System.out.println(ps);
				ResultSet rs = ps.executeQuery();
				if (rs.isBeforeFirst()) {
					System.out.println("Column Count: " + rs.isBeforeFirst());
					total_jsonArr = new JSONArray();
					while (rs.next()) {
						obj = new JSONObject();
						for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
							obj.put(rs.getMetaData().getColumnName(i), rs
									.getString(rs.getMetaData()
											.getColumnName(i)));
						}
						System.out.println(obj.toString());
						total_jsonArr.put(obj);
					}
					total_obj.put("Direct", total_jsonArr);
				}
				if (legs.equals("zero"))
					return total_obj.toString();
			}

			if (legs.equals("both") || legs.equals("one")) {

				PreparedStatement ps1 = con
						.prepareStatement("select distinct f.arrival_airport_code,i.arrival_time "
								+ "from flight f,flight_instance i where f.flight_number = i.flight_number "
								+ "and f.Arrival_airport_code "
								+ "in (select f.departure_airport_code from flight f,flight_instance i "
								+ "where f.flight_number = i.flight_number "
								+ "and f.arrival_airport_code = ? "
								+ "and (i.Date = ? or i.Date = ?)) "
								+ "and f.departure_airport_code = ? and i.Date = ?");

				ps1.setString(1, arrcode);
				ps1.setString(5, flight_date);
				ps1.setString(3, newDate);
				ps1.setString(2, flight_date);
				ps1.setString(4, depcode);
				System.out.println(depcode + "  " + arrcode + "  "
						+ flight_date);
				System.out.println(ps1);
				ResultSet rs1 = ps1.executeQuery();

				PreparedStatement ps2 = null, ps3 = null;
				ResultSet rs2, rs3;

				LinkedHashMap<String, List<ResultSet>> hm = new LinkedHashMap<String, List<ResultSet>>();

				List<ResultSet> ls = new ArrayList<ResultSet>();

				while (rs1.next()) {
					ps2 = con
							.prepareStatement("select distinct f.flight_number,f.airline,f.departure_airport_code,i.departure_time,f.arrival_airport_code,i.arrival_time,i.Date"
									+ ",f.weekdays "
									+ " from flight f,flight_instance i"
									+ " where f.flight_number=i.flight_number "
									+ "and f.departure_airport_code = ? and f.arrival_airport_code = ? "
									+ "and i.date = ?");
					ps2.setString(1, depcode);
					ps2.setString(3, flight_date);
					ps2.setString(2, rs1.getString(1));
					System.out.println(ps2);
					rs2 = ps2.executeQuery();

					ps3 = con
							.prepareStatement("select distinct f.flight_number,f.airline,f.departure_airport_code,i.departure_time,f.arrival_airport_code,i.arrival_time, i.Date, "
									+ "f.weekdays"
									+ " from flight f,flight_instance i"
									+ " where f.flight_number=i.flight_number and f.departure_airport_code = ? and f.arrival_airport_code = ?"
									+ " and ((i.date = ? and TIME_TO_SEC(i.departure_time) > TIME_TO_SEC(?)+3600) or i.date = ?)");
					ps3.setString(1, rs1.getString(1));
					ps3.setString(2, arrcode);
					ps3.setString(3, flight_date);
					ps3.setString(4, rs1.getString(2));
					ps3.setString(5, newDate);
					System.out.println(ps3.toString());
					rs3 = ps3.executeQuery();

					ls.add(rs2);
					ls.add(rs3);
					hm.put(rs1.getString(1), ls);
				}

				Iterator<Entry<String, List<ResultSet>>> it = hm.entrySet()
						.iterator();

				while (it.hasNext()) {
					total_jsonArr = new JSONArray();
					Entry<String, List<ResultSet>> entry = it.next();

					List<ResultSet> al = entry.getValue();
					for (int j = 0; j < al.size(); j++) {
						ResultSet r1 = al.get(j);
						mid_Arr = new JSONArray();
						int total_cols = r1.getMetaData().getColumnCount();

						while (r1.next()) {
							obj = new JSONObject();
							for (int cnt = 1; cnt <= total_cols; cnt++) {
								obj.put(r1.getMetaData().getColumnName(cnt),
										r1.getString(cnt));
							}
							if (!total_jsonArr.toString().contains(
									obj.toString()))
								mid_Arr.put(obj);
							// System.out.println("Mid:" + mid_Arr.toString());
						}
						total_jsonArr.put(mid_Arr);
						// System.out.println("Total : " +
						// total_jsonArr.toString());
					}
					total_obj.put(entry.getKey(), total_jsonArr);
				}
				return total_obj.toString();
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
