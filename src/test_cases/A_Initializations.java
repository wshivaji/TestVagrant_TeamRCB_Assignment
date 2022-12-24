package test_cases;



import java.io.InputStream;


import org.ini4j.Wini;
import org.json.simple.JSONObject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class A_Initializations {

	public static JSONObject jobj; // create public json object to fetch data from json file.
	public static ExtentReports report; // create public report object for extent report
	public static ExtentSparkReporter spark; // create spark object to update extent report.
	public static JSONObject players;
	
	A_Initializations() {
		A_Initializations.jobj = new JSONObject();
		A_Initializations.report = new ExtentReports();
		A_Initializations.spark = new ExtentSparkReporter("Extent_Report//test_reports.html");
		A_Initializations.spark.config().setDocumentTitle("TeamRCB_Assignment_Test_Report");
		A_Initializations.spark.config().setReportName("TeamRCB JSON Data Verification & Validation Tests");
		A_Initializations.spark.config().setTheme(Theme.DARK);
		A_Initializations.spark.config().setEncoding("utf-8");
		A_Initializations.report.attachReporter(A_Initializations.spark);		
		
	}
}
