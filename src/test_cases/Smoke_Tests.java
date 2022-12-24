package test_cases;

import java.io.FileReader;

import org.json.simple.*;
import org.json.simple.parser.*;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pom.Team_RCB_Object;


public class Smoke_Tests extends Team_RCB_Object {

	//constructor of this class to inherit properties of parent class
	Smoke_Tests() {
		super();
		
	}
	
	
	//Before Test Section which contains basically initialization of and
	//json object which reads data from json file stored inside bin folder.
	
	@BeforeTest
	public void before_test_init() {
		try {
			
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(new FileReader("bin\\TeamRCB.json"));
			jobj = (JSONObject) obj;	
			
		}		
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
		
	
	//THis is TEst cases section contains all the test cases which are required to
	//execute. Each test case is given a test case number. 
	
	@Test(priority = 1)
	public void TC_01_verify_json_data_received_as_required() {
		
		//test case TC_01 verifies data file which is under test whether extected data 
		//is under test or not. code is written into try and catch block. 
		
		try {
			
			//assertion by verifying test data heading and and location.
			
			System.out.println("ini data: "+Team_Name+" Location: "+Location);
			Assert.assertEquals(jobj.get("name"), Team_Name);
			Assert.assertEquals(jobj.get("location"), Location);
			
			//updating test case result inside extent report with proper test case details
			
			ExtentTest TC_01 = report.createTest("TC 01 verify json data received as required");
			
			if(jobj.get("name").equals(Team_Name)  && jobj.get("location").equals(Location)) {
				TC_01.log(Status.PASS, "TeamRCB Data Received TC-01 pass");
				TC_01.log(Status.INFO, "Test Case TC-01 Pass");
				report.flush();
			}
			else {
				TC_01.log(Status.FAIL, "TeamRCB Data Not Received As Expected TC-01 Fail");
				TC_01.log(Status.INFO, "Test Case TC-01 Fail");
				report.flush();
			}
			
			//catch block starts below
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		//test case TC_01 End
	}
	
	
	//second test case verifying all the key value pairs are received as per requirement
	//or not. if any field is not receied when data under test will be invalid.
	
	@Test(priority = 2)
	public void TC_02_verify_all_key_value_pairs_received() {
		
		//here we are verifying all the key value pairs are received...
		
		try {
		
			boolean check_tc2=false;
			JSONArray array = (JSONArray) jobj.get("player");
			ExtentTest TC_02 = report.createTest("TC 02 verify all key value pairs received");
			
			//for loop to check if data is valid.
			
			for (int i=0; i<array.size();i++) {
				players = (JSONObject) array.get(i);
				if(players.get("name")=="" && players.get("country")=="" && players.get("role")=="" && players.get("price-in-crores")=="") {
					check_tc2 = true;
					break;
				}
				else {
					check_tc2 = false;
				}
			}
			
			//a flag bit status will be set(true) if data is received complete without any loss. 
			// Here we are updating test case result into extent reports.
			
			
			if(check_tc2==true) {
				TC_02.log(Status.FAIL, "TeamRCB data not received as expected TC-02 Fail");
				TC_02.log(Status.INFO,"Test Case TC-02 Fail");
				report.flush();
				Assert.assertEquals(check_tc2, true);
			}
			else {
				TC_02.log(Status.PASS, "Team data received as expected TC-02 Pass");
				TC_02.log(Status.INFO, "Test Case TC-02 Pass");
				report.flush();
				Assert.assertEquals(check_tc2, false);
			}
			
		}
		
		//here catch block starts
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	//Test case number 3 which will actually perform verification of at least one foraigner
	//player is in team or not. If at least one foreigner player found in data test case
	// will get pass else fail.
	
	
	@Test(priority = 2)
	public void TC_03_verify_at_least_one_player_is_foraigner_in_team() {
		
		// here we are verifying whether data receved has one player form foreign origin 
		// by checking its country variable. 
		
		try {
		
			JSONArray array = (JSONArray) jobj.get("player");
			boolean check_tc03 = false;
			ExtentTest TC_03 = report.createTest("TC-03 verify at least one player is foraigner in team");
			for (int i=0; i<array.size();i++) {
				players = (JSONObject) array.get(i);
				if(players.get("country")!=Country_Name) {
					check_tc03 = true;
					break;
				}
				else {
					check_tc03 = false;	
				}
			}
			
			
			//Here we re updating test case result into extent report.
			
			if(check_tc03 == true) {
				TC_03.log(Status.PASS, "One player found form foraign country TC-03 Pass");
				TC_03.log(Status.INFO, "Test Case TC-03 Pass");
				report.flush();
			}
			else {
				TC_03.log(Status.FAIL, "no One player found from foraign country in Team TC-03 Fail ");
				TC_03.log(Status.INFO, "Test Case TC-03 Fail");
				report.flush();
			}
			Assert.assertEquals(check_tc03, true);

		}
		
		// Catch block starts form here
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	//This is last test case in which we verifying whether at least one wicket keeper 
	// is available in team or not.
	
	
	@Test(priority = 2)
	public void TC_04_verify_at_least_one_wicket_keeper_available_in_team() {
		
		//To verify the data we are checking each players role in team.
		//with role variable we will get to know if requirement is fulfilled or not.
		
		try {
			JSONArray array = (JSONArray) jobj.get("player");
			boolean check = false;
			ExtentTest TC_04 = report.createTest("TC-04 verify at least one wicket keeper available in team");
			for (int i=0; i<array.size();i++) {
				players = (JSONObject) array.get(i);
				while(players.get("role")!=Role) {
					check = true;
					break;
				}
			}
			Assert.assertEquals(check, true);
			
			
			
			//Here we are updating test case result into extent reports.
						
			
			if(check == true) {
				TC_04.log(Status.PASS, "one wicket keeper is availale in team TC-04 Pass");
				TC_04.log(Status.INFO, "Test Case TC-04 Pass ");
				report.flush();
			}
			else {
				TC_04.log(Status.FAIL, "No one wicket keeper is available in team TC-04 Fail");
				TC_04.log(Status.INFO, "Test Case TC-04 Fail");
				report.flush();
			}
		}
		
		//catch block starts below.
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	//This is after test block, closing operations are generally performed in this block. 
	
	@AfterTest
	public void after_test_closing() {
		try {
			System.out.println("");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
}
