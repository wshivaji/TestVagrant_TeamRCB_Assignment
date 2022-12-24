package pom;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import test_cases.A_Initializations;

public class Team_RCB_Object extends A_Initializations {
	
	public static Ini log;
	public String Team_Name, Location, Country_Name, Role;
	
	protected Team_RCB_Object(){
		super();
		log = new Ini();
		try {
			log.load(new FileReader("Utilities//data_variables.ini"));
			
			Team_Name = log.get("team_variables", "team_name", String.class);
			Location = log.get("team_variables", "location", String.class);
			Country_Name = log.get("player", "country",String.class);
			Role = log.get("player", "role", String.class);
			
		
		} catch (InvalidFileFormatException e) {
		
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

}
