import java.util.List;
import java.util.Scanner;

import Soils.Soil;
import SoilsAccess.SoilsAccessor;

public class RunSoils {

	static Scanner in = new Scanner(System.in);
	static SoilsAccessor sa = new SoilsAccessor();
	
	//add soil record
	private static void addSoilRecord() {
		System.out.print("Enter a SMS: ");
		String sms = in.nextLine();
		System.out.print("Enter a Name: ");
		String name = in.nextLine();
		System.out.print("Enter a CSR: ");
		int csr = in.nextInt();
		
		Soil toAdd = new Soil (sms, name, csr);
		sa.insertRecord(toAdd);
		System.out.println("Record added");
	}
	
	//delete soil record
	private static void deleteSoilRecord() {
		System.out.print("Enter the SMS to delete: ");
		String sms = in.nextLine();
		System.out.print("Enter the Name to delete: ");
		String name = in.nextLine();
		System.out.print("Enter the CSR to delete: ");
		int csr = in.nextInt();
		
		Soil toDelete = new Soil(sms, name, csr);
		sa.deleteSoil(toDelete);
		System.out.println("Record deleted");
	}
	
	//edit soil record
	private static void editSoilRecord() {
		System.out.println("What do you want to search by?");
		System.out.println("1: Search by SMS");
		System.out.println("2: Search by Name");
		System.out.println("3: Search by CSR");
		int searchBy = in.nextInt();
		in.nextLine();
		List<Soil> soilRecords;
		
		if (searchBy == 1) {
			System.out.print("Enter soil SMS: ");
			String sms = in.nextLine();
			soilRecords = sa.searchSoilBySMS(sms);
		}
		else if (searchBy == 2) {
			System.out.print("Enter soil Name: ");
			String name = in.nextLine();
			soilRecords = sa.searchSoilByName(name);
		}
		else {
			System.out.println("Enter soil CSR: ");
			int csr = in.nextInt();
			soilRecords = sa.searchSoilByCSR(csr);
		}
		System.out.println("Record updated");
	}
	
	public static void main(String [] args){
		runMenu();
	}
	public static void runMenu() {
		boolean keepRunning = true;
		System.out.println("--POLK COUNTY SOILS LIST");
		while (keepRunning) {
			System.out.println("--- Select Soil Record Option ---");
			System.out.println("1. Add");
			System.out.println("2. Edit");
			System.out.println("3. Delete");
			System.out.println("4. View All Soil Records");
			System.out.println("5. Exit");
			System.out.println("Your selection: ");
			
			int selection = in.nextInt();
			in.nextLine();
			
			if (selection == 1) {
				addSoilRecord();
			}
			else if (selection ==2 ) {
				editSoilRecord();
			}
			else if (selection == 3) {
				deleteSoilRecord();
			}
			else if (selection == 4) {
				showAllSoils();
			}
			else {
				sa.cleanUp();
				System.out.println("Bye!");
				keepRunning = false;
			}
		}
	}

	private static void showAllSoils() {
		List<Soil> allSoils = sa.showAllSoils();
		for(Soil soil : allSoils) {
			System.out.println(soil.returnSoilRecord());
		}
	}

}
