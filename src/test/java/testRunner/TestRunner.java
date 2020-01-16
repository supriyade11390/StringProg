package testRunner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
//import org.testng.asserts.SoftAssert;
//import org.junit.Assert.SoftAssert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;

import cucumber.api.junit.*;
import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import cucumber.runtime.junit.JUnitOptions;

import org.junit.runner.RunWith;

//@RunWith(ExtendedCucumber.class)
@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = "feature"
		//,plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/CrossMark_Report.html"}
		,monochrome = true			
		,glue={"stepDefination"}
		)

		public class TestRunner {
				
     public static Logger App_Log;
     public static Properties Config_Main;;
     public static WebDriver driver=null;
     public static Properties OR_FILE;

     public static ExtentTest test;

 	//public static Logger App_log;
     public static long waitTime=120;
     
		@BeforeClass
	 public   static void Folder_Creation() throws IOException {
		
		    	App_Log= Logger.getLogger("devpinoyLogger");
		    	System.out.println("Hello");
		    	
		    	//String Path_config_Main= System.getProperty(D:\\Automation SAMS\\SAM Workspace\\com.SAM\\configs);
		    	String Path_config_Main= System.getProperty("user.dir")+ "\\configs\\configuration.properties";
		    	System.out.println(Path_config_Main);
		    	FileInputStream fs_main =  new FileInputStream(Path_config_Main);
		    	BufferedReader reader = new BufferedReader(new FileReader(Path_config_Main));
	            Config_Main = new Properties();
	    	    Config_Main.load(reader);	    	   
	    	    reader.close();	    	 
	    	    //fs_main.read(b)
	    	    
	    	
		    	
	    	    //To read xpath file
	    	    String Path_OR_FILE=System.getProperty("user.dir")+ "\\OR\\OR_FILE.properties";
	    	    FileInputStream fs_or= new FileInputStream(Path_OR_FILE);
	    	    BufferedReader br=new BufferedReader(new FileReader(Path_OR_FILE));
	    	    OR_FILE=new Properties(System.getProperties());
	    	    OR_FILE.load(br);
	    	    br.close();
	    	    		
		    
		  }
					    }

	

