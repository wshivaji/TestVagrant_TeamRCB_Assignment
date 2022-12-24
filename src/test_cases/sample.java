package test_cases;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ini4j.InvalidFileFormatException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class sample extends A_Initializations {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(new FileReader("bin\\TeamRCB.json"));
			jobj = (JSONObject) obj;
			System.out.println(jobj.get("name"));
			System.out.println(jobj.get("location"));
			ArrayList<String> list = new ArrayList<>();
			String name[] = {};
			String country[] = {};
			String role[] = {};
			String price[] = {};
			
			JSONArray array = (JSONArray) jobj.get("player");
			HashMap<String, String> map = new HashMap<>();
			
			HashMap<String, ArrayList<String>> mulmap = new HashMap<String, ArrayList<String>>();
			for (int i=0; i<array.size();i++) {
				players = (JSONObject) array.get(i);
				map.put("name"+i,  players.get("name").toString());
				map.put("country"+i, players.get("country").toString());
				map.put("role"+i, players.get("role").toString());
				map.put("price"+i, players.get("price-in-crores").toString());
				
				mulmap.put("Name", new ArrayList<String>());
				mulmap.get("name").add(players.get("name").toString());
				mulmap.put("country", new  ArrayList<String>());
				mulmap.get("country").add(players.get("country").toString());
				mulmap.put("role", new ArrayList<String>());
				mulmap.get("role").add(players.get("role").toString());
				mulmap.put("price", new ArrayList<String>());
				mulmap.get("price").add(players.get("price-in-crores").toString());
			}
			System.out.println(map);
			System.out.print(map.get("name"));
			
			
			
			
			
		}
		
		catch (Exception ex){
			ex.printStackTrace();
			
		}
	}
	
	
	

}
