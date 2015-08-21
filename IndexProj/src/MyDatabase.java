import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;

public class MyDatabase {

	static HashMap<Integer, String> hm1 = new HashMap<Integer, String>();
	static HashMap<String, String> hm2 = new HashMap<String, String>();
	static HashMap<String, String> hm3 = new HashMap<String, String>();
	static HashMap<Integer, String> hm4 = new HashMap<Integer, String>();
	static HashMap<Integer, String> hm5 = new HashMap<Integer, String>();
	static HashMap<Integer, String> hm6 = new HashMap<Integer, String>();
	static HashMap<Float, String> hm7 = new HashMap<Float, String>();
	static HashMap<String, String> hm8 = new HashMap<String, String>();
	static HashMap<String, String> hm9 = new HashMap<String, String>();
	static HashMap<String, String> hm10 = new HashMap<String, String>();
	static HashMap<String, String> hm11 = new HashMap<String, String>();
	static File fcsv = new File("PHARMA_TRIALS_1000B.csv");

	public static void main(String[] args) {
		int choice = 1111;
		File f_index = new File("id.ndx");
		File f_data = new File("data.db");
		System.out.println("1.  Make the binary file");
		System.out.println("2. Make Index file");
		System.out.println("3. Search the ID");
		System.out.println("4. Search for Comapny");
		System.out.println("5. Search for Drug_id");
		System.out.println("6. Search for Trails");
		System.out.println("7. Search for patients");
		System.out.println("8. Search for Dosage_mg ");
		System.out.println("9.  Search for Reading");
		System.out.println("10.  Search for Double_Blind");
		System.out.println("11. Search for Controlled_study ");
		System.out.println("12.  Search for Govt_Funded");
		System.out.println("13.  Search for Fda_Approved");
		BufferedReader br;
		int op;	
		br = new BufferedReader(new InputStreamReader(System.in));

		while (choice != 0) {
			try {
				System.out.print("Select any option : ");
				choice = Integer.parseInt(br.readLine());
				if (choice >= 2 && choice <= 13) {
					if (!f_index.exists() && !f_data.exists() && choice >= 3
							&& choice <= 13) {
						System.out
								.println("Binary and Index Files not yet created !!");
						continue;
					} else if (!f_data.exists()) {
						System.out.println("Binary File not yet created !!");
						continue;
					} else if (!f_index.exists() && choice != 2) {
						System.out.println("Index Files not yet created !!");
						continue;
					}
				}
				switch (choice) {
				// Convert Binary

				case 1:
					MyDatabase.convertBinary();
					break;

				// Create index files
				case 2:
					MyDatabase.createIndex();
					break;

				// Search by Id
				case 3:
					System.out.print("Enter key for Id: ");
					int id = Integer.parseInt(br.readLine());
					System.out
							.println("1. '='  2. '<'  3. '>'  4. '<=' 5. '>=' 6. '!='");
					System.out.print("Select Operator : ");
					op = Integer.parseInt(br.readLine());
					MyDatabase.searchValueId(id, op);
					break;

				// Search By COmpany Name
				case 4:
					System.out.print("Enter key for Company: ");
					String company = br.readLine();
					System.out.println("1. '='  2. '!=' >>> ");
					System.out.print("Select Operator : ");
					op = Integer.parseInt(br.readLine());
					MyDatabase.searchValueCompany(company, op);
					break;

				// Search by Drug Id
				case 5:
					System.out.print("Enter key for Drug Id: ");
					String drug_id = br.readLine();
					System.out.println("1. '='  2. '!=' >>> ");
					System.out.print("Select Operator : ");
					op = Integer.parseInt(br.readLine());
					MyDatabase.searchValueDrugId(drug_id, op);
					break;

				// Search by Trials
				case 6:
					System.out.print("Enter key for Trials: ");
					int trials = Integer.parseInt(br.readLine());
					System.out
							.println("1. '='  2. '<'  3. '>'  4. '<=' 5. '>=' 6. '!=' >>> ");
					System.out.print("Select Operator : ");
					op = Integer.parseInt(br.readLine());
					MyDatabase.searchValueTrials(trials, op);
					break;

				// Search by Patients
				case 7:
					System.out.print("Enter key for Patients: ");
					int patients = Integer.parseInt(br.readLine());
					System.out
							.println("1. '='  2. '<'  3. '>'  4. '<=' 5. '>=' 6. '!=' >>> ");
					System.out.print("Select Operator : ");
					op = Integer.parseInt(br.readLine());
					MyDatabase.searchValuePatients(patients, op);
					break;

				// Search by Dosage_mg
				case 8:
					System.out.print("Enter key for Dosage_mg: ");
					int dosage_mg = Integer.parseInt(br.readLine());
					System.out
							.println("1. '='  2. '<'  3. '>'  4. '<=' 5. '>=' 6. '!=' >>> ");
					System.out.print("Select Operator : ");
					op = Integer.parseInt(br.readLine());
					MyDatabase.searchValueDosage(dosage_mg, op);
					break;

				// Search by Reading
				case 9:
					System.out.print("Enter key for Reading: ");
					float reading = Float.parseFloat(br.readLine());
					System.out
							.println("1. '='  2. '<'  3. '>'  4. '<=' 5. '>=' 6. '!=' >>> ");
					System.out.print("Select Operator : ");
					op = Integer.parseInt(br.readLine());
					MyDatabase.searchValueReading(reading, op);
					break;

				// Search by Double_Blind
				case 10:
					System.out
							.print("Select key for Double Blind (1. TRUE   2. FALSE) : >>> ");
					int double_blind = Integer.parseInt(br.readLine());
					MyDatabase.searchValueDoubleBlind(double_blind);
					break;

				// Search by Controlled Study
				case 11:
					System.out
							.print("Select key for Controlled Study (1. TRUE   2. FALSE) : >>> ");
					int controlled_study = Integer.parseInt(br.readLine());
					MyDatabase.searchValueControlledStudy(controlled_study);
					break;

				// Search by Govt_Funded
				case 12:
					System.out
							.print("Select key for Govt. Funded (1. TRUE   2. FALSE) : >>> ");
					int govt_funded = Integer.parseInt(br.readLine());
					MyDatabase.searchValueGovtFunded(govt_funded);
					break;

				// Search by FDA Approved
				case 13:
					System.out
							.print("Select key for FDA Approved (1. TRUE   2. FALSE) : >>> ");
					int fda_approved = Integer.parseInt(br.readLine());
					MyDatabase.searchValueDoubleBlind(fda_approved);
					break;

				case 0:
					System.out.println("Thanks for using Indexing. !!");
					System.exit(0);
					break;

				default:
					System.out.println("Invalid Choice/ Try Again");
					break;
				}
			} catch (Exception e) {
				if (!fcsv.exists())
					System.out
							.println("Check PHARMA_TRIALS_1000B.csv is available in the jar path");
				else
					System.out.println("Invalid/Illegal Input");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void searchValueFdaApproved(int bool)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		long seekpos = 11111;
		@SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"fda_approved.ndx"));
		hm11 = (HashMap<String, String>) ois.readObject();

		String val = "false";
		if (bool == 1)
			val = "true";

		if (hm11.containsKey(val)) {
			for (Map.Entry<String, String> entry : hm11.entrySet()) {
				if (val.equalsIgnoreCase(entry.getKey())) {
					String value[] = entry.getValue().split("#");
					for (int i = 0; i < value.length; i++) {
						seekpos = Long.parseLong(value[i]);
						printMatch(seekpos);
					}
				}
			}
		} else {
			System.out.println("Invalid Key Input");
		}
	}

	@SuppressWarnings("unchecked")
	public static void searchValueGovtFunded(int bool)
			throws ClassNotFoundException, IOException {
		long seekpos = 11111;
		@SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"govt_funded.ndx"));
		hm10 = (HashMap<String, String>) ois.readObject();

		String val = "false";
		if (bool == 1)
			val = "true";

		if (hm10.containsKey(val)) {
			for (Map.Entry<String, String> entry : hm10.entrySet()) {
				if (val.equalsIgnoreCase(entry.getKey())) {
					String value[] = entry.getValue().split("#");
					for (int i = 0; i < value.length; i++) {
						seekpos = Long.parseLong(value[i]);
						printMatch(seekpos);
					}
				}
			}
		} else {
			System.out.println("Invalid Key Input");
		}
	}

	@SuppressWarnings("unchecked")
	public static void searchValueControlledStudy(int bool)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		long seekpos = 11111;
		@SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"controlled_study.ndx"));
		hm9 = (HashMap<String, String>) ois.readObject();

		String val = "false";
		if (bool == 1)
			val = "true";

		if (hm9.containsKey(val)) {
			for (Map.Entry<String, String> entry : hm9.entrySet()) {
				if (val.equalsIgnoreCase(entry.getKey())) {
					String value[] = entry.getValue().split("#");
					for (int i = 0; i < value.length; i++) {
						seekpos = Long.parseLong(value[i]);
						printMatch(seekpos);
					}
				}
			}
		} else {
			System.out.println("Invalid Key Input");
		}
	}

	@SuppressWarnings("unchecked")
	public static void searchValueDoubleBlind(int bool) throws IOException,
			ClassNotFoundException {
		long seekpos = 11111;
		@SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"double_blind.ndx"));
		hm8 = (HashMap<String, String>) ois.readObject();

		String val = "false";
		if (bool == 1)
			val = "true";

		if (hm8.containsKey(val)) {
			for (Map.Entry<String, String> entry : hm8.entrySet()) {
				if (val.equalsIgnoreCase(entry.getKey())) {
					String value[] = entry.getValue().split("#");
					for (int i = 0; i < value.length; i++) {
						seekpos = Long.parseLong(value[i]);
						printMatch(seekpos);
					}
				}
			}
		} else {
			System.out.println("Invalid Key Input");
		}
	}

	@SuppressWarnings("unchecked")
	public static void searchValueReading(float reading, int cmp)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		long seekpos = 11111;
		@SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"reading.ndx"));
		hm7 = (HashMap<Float, String>) ois.readObject();

		if (hm7.containsKey(reading)) {
			if (cmp == 1) {
				for (Map.Entry<Float, String> entry : hm7.entrySet()) {
					if (reading == entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 2) {
				for (Map.Entry<Float, String> entry : hm7.entrySet()) {
					if (reading > entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 3) {
				for (Map.Entry<Float, String> entry : hm7.entrySet()) {
					if (reading < entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 4) {
				for (Map.Entry<Float, String> entry : hm7.entrySet()) {
					if (reading >= entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 5) {
				for (Map.Entry<Float, String> entry : hm7.entrySet()) {
					if (reading <= entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 6) {
				for (Map.Entry<Float, String> entry : hm7.entrySet()) {
					if (reading != entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			} else {
				System.out.println("Invalid Operator Selection");
			}
		} else {
			System.out.println("Invalid Key Input");
		}
	}

	@SuppressWarnings("unchecked")
	public static void searchValueDosage(int dosage, int cmp)
			throws ClassNotFoundException, IOException {
		long seekpos = 11111;
		@SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"dosage_mg.ndx"));
		hm6 = (HashMap<Integer, String>) ois.readObject();

		if (hm6.containsKey(dosage)) {
			if (cmp == 1) {
				for (Map.Entry<Integer, String> entry : hm6.entrySet()) {
					if (dosage == entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 2) {
				for (Map.Entry<Integer, String> entry : hm6.entrySet()) {
					if (dosage > entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 3) {
				for (Map.Entry<Integer, String> entry : hm6.entrySet()) {
					if (dosage < entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 4) {
				for (Map.Entry<Integer, String> entry : hm6.entrySet()) {
					if (dosage >= entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 5) {
				for (Map.Entry<Integer, String> entry : hm6.entrySet()) {
					if (dosage <= entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 6) {
				for (Map.Entry<Integer, String> entry : hm6.entrySet()) {
					if (dosage != entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			} else {
				System.out.println("Invalid Operator Selection");
			}
		} else {
			System.out.println("Invalid Key Input");
		}
	}

	@SuppressWarnings("unchecked")
	public static void searchValuePatients(int patient, int cmp)
			throws IOException, ClassNotFoundException {
		long seekpos = 11111;
		@SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"patients.ndx"));
		hm5 = (HashMap<Integer, String>) ois.readObject();

		if (hm5.containsKey(patient)) {
			if (cmp == 1) {
				for (Map.Entry<Integer, String> entry : hm5.entrySet()) {
					if (patient == entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 2) {
				for (Map.Entry<Integer, String> entry : hm5.entrySet()) {
					if (patient > entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 3) {
				for (Map.Entry<Integer, String> entry : hm5.entrySet()) {
					if (patient < entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 4) {
				for (Map.Entry<Integer, String> entry : hm5.entrySet()) {
					if (patient >= entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 5) {
				for (Map.Entry<Integer, String> entry : hm5.entrySet()) {
					if (patient <= entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 6) {
				for (Map.Entry<Integer, String> entry : hm5.entrySet()) {
					if (patient != entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			} else {
				System.out.println("Invalid Operator Selection");
			}
		} else {
			System.out.println("Invalid Key Input");
		}
	}

	@SuppressWarnings("unchecked")
	public static void searchValueTrials(int trial, int cmp)
			throws IOException, ClassNotFoundException {
		long seekpos = 11111;
		@SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"trials.ndx"));
		hm4 = (HashMap<Integer, String>) ois.readObject();

		if (hm4.containsKey(trial)) {
			if (cmp == 1) {
				for (Map.Entry<Integer, String> entry : hm4.entrySet()) {
					if (trial == entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 2) {
				for (Map.Entry<Integer, String> entry : hm4.entrySet()) {
					if (trial > entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 3) {
				for (Map.Entry<Integer, String> entry : hm4.entrySet()) {
					if (trial < entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 4) {
				for (Map.Entry<Integer, String> entry : hm4.entrySet()) {
					if (trial >= entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 5) {
				for (Map.Entry<Integer, String> entry : hm4.entrySet()) {
					if (trial <= entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 6) {
				for (Map.Entry<Integer, String> entry : hm4.entrySet()) {
					if (trial != entry.getKey()) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			} else {
				System.out.println("Invalid Operator Selection");
			}
		} else {
			System.out.println("Invalid Key Input");
		}
	}

	@SuppressWarnings("unchecked")
	public static void searchValueDrugId(String drug_id, int cmp)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		long seekpos = 111111;
		@SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"drug_id.ndx"));
		hm3 = (HashMap<String, String>) ois.readObject();

		if (hm3.containsKey(drug_id)) {
			if (cmp == 1) {
				for (Map.Entry<String, String> entry : hm3.entrySet()) {
					if (drug_id.equals(entry.getKey())) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			} else if (cmp == 2) {
				for (Map.Entry<String, String> entry : hm3.entrySet()) {
					if (drug_id.equals(entry.getKey())) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			} else {
				System.out.println("Invalid Operator Selection");
			}
		} else {
			System.out.println("Invalid Key entered");
		}
	}

	@SuppressWarnings("unchecked")
	public static void searchValueCompany(String cname, int cmp)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		long seekpos = 111111;
		@SuppressWarnings("resource")
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"company.ndx"));
		hm2 = (HashMap<String, String>) ois.readObject();

		if (hm2.containsKey(cname)) {
			if (cmp == 1) {
				for (Map.Entry<String, String> entry : hm2.entrySet()) {
					if (cname.equals(entry.getKey())) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			}

			else if (cmp == 2) {
				for (Map.Entry<String, String> entry : hm2.entrySet()) {
					if (!cname.equals(entry.getKey())) {
						String value[] = entry.getValue().split("#");
						for (int i = 0; i < value.length; i++) {
							seekpos = Long.parseLong(value[i]);
							printMatch(seekpos);
						}
					}
				}
			} else {
				System.out.println("Invalid Operator Selection");
			}
		} else {
			System.out.println("Invalid Key entered");
		}
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public static void searchValueId(int value, int cmp) throws IOException,
			ClassNotFoundException {
		long seekpos = 11111;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"id.ndx"));
		hm1 = (HashMap<Integer, String>) ois.readObject();

		if (hm1.containsKey(value)) {
			if (cmp == 1) {
				for (Map.Entry<Integer, String> entry : hm1.entrySet()) {
					if (value == entry.getKey()) {
						seekpos = Long.parseLong(entry.getValue());
						printMatch(seekpos);
					}
				}
			}

			else if (cmp == 2) {
				for (Map.Entry<Integer, String> entry : hm1.entrySet()) {
					if (value > entry.getKey()) {
						seekpos = Long.parseLong(entry.getValue());
						printMatch(seekpos);
					}
				}
			}

			else if (cmp == 3) {
				for (Map.Entry<Integer, String> entry : hm1.entrySet()) {
					if (value < entry.getKey()) {
						seekpos = Long.parseLong(entry.getValue());
						printMatch(seekpos);
					}
				}
			}

			else if (cmp == 4) {
				for (Map.Entry<Integer, String> entry : hm1.entrySet()) {
					if (value >= entry.getKey()) {
						seekpos = Long.parseLong(entry.getValue());
						printMatch(seekpos);
					}
				}
			}

			else if (cmp == 5) {
				for (Map.Entry<Integer, String> entry : hm1.entrySet()) {
					if (value <= entry.getKey()) {
						seekpos = Long.parseLong(entry.getValue());
						printMatch(seekpos);
					}
				}
			}

			else if (cmp == 6) {
				for (Map.Entry<Integer, String> entry : hm1.entrySet()) {
					if (value != entry.getKey()) {
						seekpos = Long.parseLong(entry.getValue());
						printMatch(seekpos);
					}
				}
			} else {
				System.out.println("Invalid operator for search");
			}
		} else {
			System.out.println("Invalid Key");
		}
	}

	public static void printMatch(long seekpos) throws IOException {
		@SuppressWarnings("resource")
		RandomAccessFile raf = new RandomAccessFile("data.db", "r");
		raf.seek(seekpos);
		System.out.print(raf.readInt() + " | ");
		int len = raf.readByte();
		for (int i = 0; i < len; i++) {
			System.out.print((char) raf.readByte());
		}

		System.out.print(" | ");
		for (int i = 0; i < 6; i++) {
			System.out.print((char) raf.readByte());
		}
		System.out.print(" | ");
		System.out.print(raf.readShort() + " | ");
		System.out.print(raf.readShort() + " | ");
		System.out.print(raf.readShort() + " | ");
		System.out.print(raf.readFloat() + " | ");

		byte bool = raf.readByte();
		byte v1 = (byte) (0x08 & bool);
		byte v2 = (byte) (0x04 & bool);
		byte v3 = (byte) (0x02 & bool);
		byte v4 = (byte) (0x01 & bool);

		boolean val1 = false, val2 = false, val3 = false, val4 = false;
		if (v1 == 0x08)
			val1 = true;
		if (v2 == 0x04)
			val2 = true;
		if (v3 == 0x02)
			val3 = true;
		if (v4 == 0x01)
			val4 = true;

		System.out.print(val1 + " | ");
		System.out.print(val2 + " | ");
		System.out.print(val3 + " | ");
		System.out.print(val4);
		System.out.println();
	}

	@SuppressWarnings({ "resource" })
	public static void createIndex() throws IOException {

		// index for id
		File f1 = new File("id.ndx");
		if (!f1.exists()) {
			RandomAccessFile raf = new RandomAccessFile("data.db", "rw");

			ObjectOutputStream oos1 = new ObjectOutputStream(
					new FileOutputStream("id.ndx"));
			ObjectOutputStream oos2 = new ObjectOutputStream(
					new FileOutputStream("company.ndx"));
			ObjectOutputStream oos3 = new ObjectOutputStream(
					new FileOutputStream("drug_id.ndx"));
			ObjectOutputStream oos4 = new ObjectOutputStream(
					new FileOutputStream("trials.ndx"));
			ObjectOutputStream oos5 = new ObjectOutputStream(
					new FileOutputStream("patients.ndx"));
			ObjectOutputStream oos6 = new ObjectOutputStream(
					new FileOutputStream("dosage_mg.ndx"));
			ObjectOutputStream oos7 = new ObjectOutputStream(
					new FileOutputStream("reading.ndx"));
			ObjectOutputStream oos8 = new ObjectOutputStream(
					new FileOutputStream("double_blind.ndx"));
			ObjectOutputStream oos9 = new ObjectOutputStream(
					new FileOutputStream("controlled_study.ndx"));
			ObjectOutputStream oos10 = new ObjectOutputStream(
					new FileOutputStream("govt_funded.ndx"));
			ObjectOutputStream oos11 = new ObjectOutputStream(
					new FileOutputStream("fda_approved.ndx"));

			long seekpos;
			for (int i = 1; i <= 1000; i++) {
				ArrayList<Long> globalList = new ArrayList<Long>();
				seekpos = raf.getFilePointer();
				globalList.add(seekpos);

				// Create index of id
				int id = raf.readInt();
				if (!hm1.containsKey(id)) {
					hm1.put(id, String.valueOf(seekpos));
				}

				// Create index of company
				int len = raf.readByte();
				byte cname[] = new byte[len];
				for (int j = 0; j < len; j++)
					cname[j] = raf.readByte();

				String comp = new String(cname, "UTF-8");
				if (hm2.containsKey(comp)) {
					String s = hm2.get(comp);
					hm2.put(comp, s + "#" + String.valueOf(seekpos));
				} else {
					hm2.put(comp, String.valueOf(seekpos));
				}

				// Create index of drug_id
				byte drug_id[] = new byte[6];
				for (int k = 0; k < 6; k++)
					drug_id[k] = raf.readByte();

				String did = new String(drug_id);
				if (hm3.containsKey(did)) {
					String s = hm3.get(did);
					hm3.put(did, s + "#" + String.valueOf(seekpos));
				} else {
					hm3.put(did, String.valueOf(seekpos));
				}

				// Create index of trials
				int trials = raf.readShort();
				if (hm4.containsKey(trials)) {
					String s = hm4.get(trials);
					hm4.put(trials, s + "#" + String.valueOf(seekpos));
				} else {
					hm4.put(trials, String.valueOf(seekpos));
				}

				// Create index of patients
				int patients = raf.readShort();
				if (hm5.containsKey(patients)) {
					String s = hm5.get(patients);
					hm5.put(patients, s + "#" + String.valueOf(seekpos));
				} else {
					hm5.put(patients, String.valueOf(seekpos));
				}

				// Create index of dosage
				int dosage_mg = raf.readShort();
				if (hm6.containsKey(dosage_mg)) {
					String s = hm6.get(dosage_mg);
					hm6.put(dosage_mg, s + "#" + String.valueOf(seekpos));
				} else {
					hm6.put(dosage_mg, String.valueOf(seekpos));
				}

				// Create index of reading
				float reading = raf.readFloat();
				if (hm7.containsKey(reading)) {
					String s = hm7.get(reading);
					hm7.put(reading, s + "#" + String.valueOf(seekpos));
				} else {
					hm7.put(reading, String.valueOf(seekpos));
				}

				byte bool = raf.readByte();
				String b1 = "false", b2 = "false", b3 = "false", b4 = "false";
				byte v1 = 0x08, v2 = 0x04, v3 = 0x02, v4 = 0x01;
				v1 = (byte) (bool & v1);
				v2 = (byte) (bool & v2);
				v3 = (byte) (bool & v3);
				v4 = (byte) (bool & v4);
				if (v1 != 0x00)
					b1 = "true";
				if (v2 != 0x00)
					b2 = "true";
				if (v3 != 0x00)
					b3 = "true";
				if (v4 != 0x00)
					b4 = "true";

				// Create index of double_blind
				if (hm8.containsKey(b1)) {
					String s = hm8.get(b1);
					hm8.put(b1, s + "#" + String.valueOf(seekpos));
				} else {
					hm8.put(b1, String.valueOf(seekpos));
				}

				// Create index of controlled_study
				if (hm8.containsKey(b2)) {
					String s = hm9.get(b2);
					hm9.put(b2, s + "#" + String.valueOf(seekpos));
				} else {
					hm9.put(b2, String.valueOf(seekpos));
				}

				// Create index of govt_funded
				if (hm10.containsKey(b3)) {
					String s = hm10.get(b3);
					hm10.put(b3, s + "#" + String.valueOf(seekpos));
				} else {
					hm10.put(b3, String.valueOf(seekpos));
				}

				// Create index fda_approved
				if (hm11.containsKey(b4)) {
					String s = hm11.get(b4);
					hm11.put(b4, s + "#" + String.valueOf(seekpos));
				} else {
					hm11.put(b4, String.valueOf(seekpos));
				}
			}

			// Serialize HashMap objects into index files
			oos1.writeObject(hm1);
			oos2.writeObject(hm2);
			oos3.writeObject(hm3);
			oos4.writeObject(hm4);
			oos5.writeObject(hm5);
			oos6.writeObject(hm6);
			oos7.writeObject(hm7);
			oos8.writeObject(hm8);
			oos9.writeObject(hm9);
			oos10.writeObject(hm10);
			oos11.writeObject(hm11);

			System.out.println("Done ... All Index files Serialized !");
		} else {
			System.out.println("All Index files are already created");
		}
	}

	public static void convertBinary() throws IOException {
		File bfile = new File("data.db");
		if (!bfile.exists()) {
			@SuppressWarnings("resource")
			CSVReader reader = new CSVReader(new FileReader(
					"PHARMA_TRIALS_1000B.csv"));
			RandomAccessFile raf = new RandomAccessFile("data.db", "rw");
			String field[];
			reader.readNext();
			while ((field = reader.readNext()) != null) {
				// Field : id > integer
				raf.writeInt(Integer.parseInt(field[0]));

				// Field : company > varchar
				raf.writeByte(field[1].length());
				raf.writeBytes(field[1]);

				// Field : drug_id > char(6)
				raf.writeBytes(field[2]);

				// Field : trials > short int
				raf.writeShort(Short.parseShort(field[3]));

				// Field : patients > short int
				raf.writeShort(Short.parseShort(field[4]));

				// Field : dosage_mg > short int
				raf.writeShort(Short.parseShort(field[5]));

				// Field : reading > float
				raf.writeFloat(Float.parseFloat(field[6]));

				byte b = 0x00;
				byte tmp;

				if (field[7].equalsIgnoreCase("false")) {
					tmp = 0x00;
					b = (byte) (b | tmp);
				} else {
					tmp = 0x08;
					b = (byte) (b | tmp);
				}

				if (field[8].equalsIgnoreCase("false")) {
					tmp = 0x00;
					b = (byte) (b | tmp);
				} else {
					tmp = 0x04;
					b = (byte) (b | tmp);
				}

				if (field[9].equalsIgnoreCase("false")) {
					tmp = 0x00;
					b = (byte) (b | tmp);
				} else {
					tmp = 0x02;
					b = (byte) (b | tmp);
				}

				if (field[10].equalsIgnoreCase("false")) {
					tmp = 0x00;
					b = (byte) (b | tmp);
				} else {
					tmp = 0x01;
					b = (byte) (b | tmp);
				}

				raf.writeByte(b);
			}
			raf.close();
			System.out.println("Binary File 'data.db' created successfully");
		} else {
			System.out.println("Binary file already created as \"data.db\"");
		}
	}
}
