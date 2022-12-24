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

	
	

	Smoke_Tests() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	
	@Test(priority = 1)
	public void TC_01_verify_json_data_received_as_required() {
		try {
			System.out.println("ini data: "+Team_Name+" Location: "+Location);
			Assert.assertEquals(jobj.get("name"), Team_Name);
			Assert.assertEquals(jobj.get("location"), Location);
			
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
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(priority = 2)
	public void TC_02_verify_all_key_value_pairs_received() {
		try {
		
			boolean check_tc2=false;
			JSONArray array = (JSONArray) jobj.get("player");
			ExtentTest TC_02 = report.createTest("TC 02 verify all key value pairs received");
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
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(priority = 2)
	public void TC_03_verify_at_least_one_player_is_foraigner_in_team() {
		
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
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test(priority = 2)
	public void TC_04_verify_at_least_one_wicket_keeper_available_in_team() {
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
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
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
