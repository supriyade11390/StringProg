package stepDefination;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import testRunner.TestRunner;
import utilities.GenericKeywords;



public class StepDefination extends TestRunner {
	
@Given("^User login with his credentials$")
	public void user_login_with_his_credentials() throws Throwable 

    {
		TestRunner.App_Log.debug("Launching the browser");
		GenericKeywords.Gen_openBrowser("BrowserName");
		GenericKeywords.Gen_navigate(TestRunner.Config_Main.getProperty("URL"));
	
		GenericKeywords.Gen_SendKeys(TestRunner.OR_FILE.getProperty("Username"), TestRunner.Config_Main.getProperty("username"));
		//Thread.sleep(5000);
		GenericKeywords.Gen_SendPassword(TestRunner.OR_FILE.getProperty("Password"),TestRunner.Config_Main.getProperty("password"));
		//Thread.sleep(5000);
		GenericKeywords.Gen_Click("Login to the application",TestRunner.OR_FILE.getProperty("btn_Login"));
		
    }
@Then("^User Select Space \"(.*?)\" in My Spaces at Home Page$")
	public void User_Select_Space_in_My_Spaces_at_Home_Page(String envName) throws InterruptedException
	
	{  
		//System.out.println(envName);
		Thread.sleep(5000);
		GenericKeywords.Gen_VerifyElementIsDisplayed("Verifying the Space", TestRunner.OR_FILE.getProperty(envName));
		GenericKeywords.Gen_MouseHover(TestRunner.OR_FILE.getProperty(envName));
		//GenericKeywords.Gen_ImplicitWait(1);
		GenericKeywords.Gen_Click("Click to the Sapce", TestRunner.OR_FILE.getProperty(envName));
		
		
	}
@Then("^User click to Dashboard and Dashboard page is Display$")
	public void User_click_to_Dashboard_and_Dashboard_page_is_Display() throws InterruptedException
	{
		Thread.sleep(5000);
		GenericKeywords.Gen_VerifyElementIsDisplayed("Verify the Dashbord link", TestRunner.OR_FILE.getProperty("lnk_Dashboard"));
		GenericKeywords.Gen_Click("Dashboard Link", TestRunner.OR_FILE.getProperty("lnk_Dashboard"));
	}
	
@When("^User Select Event and Execution dashboard$")
	public void User_Select_Event_and_Execution_dashboard()
	{
		GenericKeywords.Gen_VerifyElementIsDisplayed("OneHub", TestRunner.OR_FILE.getProperty("OneHub"));
		GenericKeywords.Gen_Click("OneHub", TestRunner.OR_FILE.getProperty("OneHub"));
		GenericKeywords.Gen_Click("Events",TestRunner.OR_FILE.getProperty("lnk_Event"));
		GenericKeywords.Gen_VerifyElementIsDisplayed("Execution Dashboard", TestRunner.OR_FILE.getProperty("Execution_Dashboard"));
		
	}
@Then("^User select \"(.*?)\" and \"(.*?)\" using date range filter$")
	public void user_select_and_using_date_range_filter(String StartDate, String EndDate) throws Throwable 
	{
	    GenericKeywords.Gen_VerifyElementIsDisplayed("Filter", TestRunner.OR_FILE.getProperty("btn_Filter"));
	  GenericKeywords.Gen_ImplicitWait(1);
	    GenericKeywords.Gen_Click("Filter", TestRunner.OR_FILE.getProperty("btn_Filter"));
	    GenericKeywords.Gen_ImplicitWait(1);
	    GenericKeywords.Gen_Click("DateRange", TestRunner.OR_FILE.getProperty("lnk_Filter_DateRange"));
	    GenericKeywords.Gen_ImplicitWait(1);
	    GenericKeywords.Gen_SendKeys(TestRunner.OR_FILE.getProperty("btn_startDate_tab"),StartDate);
	    GenericKeywords.Gen_ImplicitWait(2);
	    GenericKeywords.Gen_Click("End Date", TestRunner.OR_FILE.getProperty("btn_endDate_tab"));
	    GenericKeywords.Gen_SendKeys(TestRunner.OR_FILE.getProperty("btn_endDate_tab"),EndDate);
	    GenericKeywords.Gen_ImplicitWait(2);
	    //GenericKeywords.Gen_Click("Start Date of Month Tab", TestRunner.OR_FILE.getProperty("btn_endDate_tab"));
	    GenericKeywords.Gen_Click("Apply Filter button", TestRunner.OR_FILE.getProperty("btn_Filter_apply"));
	    
	    
	}
@Then("^Verify the KVI and tooltips available on Dashbaord$")
	public void verify_the_KVI_and_tooltips_available_on_Dashbaord(DataTable arg1) throws Throwable
	{
	  List<List<String>> data= arg1.raw();
	  int size= arg1.raw().size();
	  for(int i=0;i<size;i++) {
		 
		GenericKeywords.Gen_verifyText(data.get(i).get(0), TestRunner.OR_FILE.getProperty(data.get(i).get(1)));
		GenericKeywords.Gen_ImplicitWait(2);
		String dynxpath= "//h2[text()='"+data.get(i).get(0)+"']";
		GenericKeywords.Gen_VerifyElementIsDisplayed("KVI text", dynxpath);
		GenericKeywords.Gen_ImplicitWait(2);
		GenericKeywords.Gen_MouseHover(dynxpath);
		GenericKeywords.Gen_ImplicitWait(2);
		System.out.println("Tool tip for KVI" +data.get(i).get(0));
		String expectedToolTip=TestRunner.OR_FILE.getProperty(data.get(i).get(2));
		GenericKeywords.Gen_ImplicitWait(2);
	    String observedTooltip=GenericKeywords.Gen_getText("//div[@class='kpi-description popover-content']");
		//GenericKeywords.Gen_ImplicitWait(2);
	   System.out.println(observedTooltip);
	    GenericKeywords.Gen_verifyTextContains(expectedToolTip, observedTooltip);
	   
		
	 }
	}
 @Then("^Verify the Filters available on dashboard$")
	  public void verify_the_Filters_available_on_dashboard(DataTable arg1) throws Throwable {
	 
	    List<List<String>> data=arg1.raw();
	    int size= arg1.raw().size();
	    for(int i=0;i<size;i++)
	    {

		    GenericKeywords.Gen_VerifyElementIsDisplayed("Filter", TestRunner.OR_FILE.getProperty("btn_Filter"));
		    GenericKeywords.Gen_ImplicitWait(1);
		    GenericKeywords.Gen_Click("Filter", TestRunner.OR_FILE.getProperty("btn_Filter"));
		    GenericKeywords.Gen_ImplicitWait(2);
	    	GenericKeywords.Gen_VerifyElementIsDisplayed("Filetr Search Option", TestRunner.OR_FILE.getProperty("inp_search_filter"));
	    	GenericKeywords.Gen_VerifyElementIsDisplayed(data.get(i).get(0), TestRunner.OR_FILE.getProperty(data.get(i).get(0)));
	    	GenericKeywords.Gen_Click(data.get(i).get(0), TestRunner.OR_FILE.getProperty(data.get(i).get(1)));
	    	
	    	String value=GenericKeywords.Gen_getTextReturn(TestRunner.OR_FILE.getProperty("second_button_Filter"));
	    	
	    	GenericKeywords.Gen_ImplicitWait(4);
	    	GenericKeywords.Gen_Click("Select the value", TestRunner.OR_FILE.getProperty("second_button_Filter"));
	    	GenericKeywords.Gen_ImplicitWait(2);
	    	GenericKeywords.Gen_Click("Click on Aplay", TestRunner.OR_FILE.getProperty("btn_Filter_apply"));
	    	GenericKeywords.Gen_ImplicitWait(1);
	    	
	    	value = "= "+value;
	        //String dynXpath = "//span[text()='"+value+"']";
	        String dynXpath = "  //span[contains(text(),'"+value+"')]";
	    	//System.out.println(dynXpath);
	    	
	    	GenericKeywords.Gen_MouseHover(dynXpath);
	    	   String dnyXPathClose = "//span[contains(text(),'"+value+"')]//following-sibling::i";
	    	   System.out.println(dnyXPathClose);
	    	   GenericKeywords.Gen_ImplicitWait(1);
	    	   GenericKeywords.Gen_VerifyElementIsDisplayed("Clear Filter", dnyXPathClose);
	    	   GenericKeywords.Gen_ImplicitWait(2);
	    	   GenericKeywords.Gen_Click("Clear Filter", dnyXPathClose);
	    	   GenericKeywords.Gen_ImplicitWait(2);
	    	   System.out.println("Filter is verified" +data.get(i).get(0));
	    	   
	    	
	     }

	}
 @Then("^Verify Columns that are displayed of Event Summary Dashlet$")
 public void verify_Columns_that_are_displayed_of_Event_Summary_Dashlet(DataTable arg1) throws Throwable {
	 

	 GenericKeywords.Gen_ImplicitWait(1);
	 GenericKeywords.Gen_MouseHover(TestRunner.OR_FILE.getProperty("event_summary_dashlet"));
	 GenericKeywords.Gen_ImplicitWait(1);
	 GenericKeywords.Gen_VerifyElementIsDisplayed("Click on Maximize", TestRunner.OR_FILE.getProperty("icon_maximize"));
	 GenericKeywords.Gen_Click("Click on Maximize", TestRunner.OR_FILE.getProperty("icon_maximize"));
	 GenericKeywords.Gen_ImplicitWait(2);
	 
	 List<List<String>> data=arg1.raw();
	 int size= arg1.raw().size();
	 for(int i=0;i<size;i++)
	 {
		 
		 GenericKeywords.Gen_VerifyElementIsDisplayed(data.get(i).get(0), TestRunner.OR_FILE.getProperty(data.get(i).get(1)));
		 GenericKeywords.Gen_ImplicitWait(1);
		 //GenericKeywords.Gen_MouseHover("TestRunner.OR_FILE.getProperty(data.get(i).get(1)");
		 System.out.println("Column is displayed:" +data.get(i).get(0));
		 GenericKeywords.Gen_ImplicitWait(2);
		
		 
		 
	}
	 GenericKeywords.Gen_MouseHover(TestRunner.OR_FILE.getProperty("event_summary_dashlet"));
	 GenericKeywords.Gen_Click("Click on Maximize", TestRunner.OR_FILE.getProperty("icon_maximize"));
	 GenericKeywords.Gen_ImplicitWait(1);
 }

 @Then("^Verify the navigations on Execution Dashboard from KVI$")
 public void verify_the_navigations_on_Execution_Dashboard_from_KVI(DataTable arg1) throws Throwable {
    
	 List<List<String>> data=arg1.raw();
	 int size= arg1.raw().size();
	 
	 for(int i=0;i<size;i++)
		
	 {  
		 GenericKeywords.Gen_webDriverWait_tillElementIsInvisible("loader", TestRunner.OR_FILE.getProperty("dashboard_loader"));
        GenericKeywords.Gen_MouseHover(TestRunner.OR_FILE.getProperty(data.get(i).get(1)));	
        GenericKeywords.Gen_ImplicitWait(2);
		 GenericKeywords.Gen_Click(data.get(i).get(0), TestRunner.OR_FILE.getProperty(data.get(i).get(1)));
		 GenericKeywords.Gen_webDriverWait_tillElementIsInvisible("loader", TestRunner.OR_FILE.getProperty("dashboard_loader"));
		// GenericKeywords.Gen_ImplicitWait(5);
	     TestRunner.driver.navigate().back();
	     
	     System.out.println("Navigations verified " +data.get(i).get(0));
	 }
	 
}
 @Then("^Verify that Total Event Count of Event summary matches with the Events KVI$")
 
 public void verify_that_Total_Event_Count_of_Event_summary_matches_with_the_Events_KVI() throws Throwable {
	 
	 GenericKeywords.Gen_MouseHover(TestRunner.OR_FILE.getProperty("Total_event_Summary"));
	 GenericKeywords.Gen_ImplicitWait(2);
	 GenericKeywords.Gen_verifyValue(TestRunner.OR_FILE.getProperty("txt_kvi_Events"), TestRunner.OR_FILE.getProperty("Total_event_Summary"));
	 
}
 @Then("^Verify that Completion % count  of Event summary matches with the Completed % KVI$")
 
 public void verify_that_Completion_count_of_Event_summary_matches_with_the_Completed_KVI() throws Throwable {
	 GenericKeywords.Gen_MouseHover(TestRunner.OR_FILE.getProperty("Total_completion%_summary"));
	 GenericKeywords.Gen_ImplicitWait(2);
	 GenericKeywords.Gen_verifyValue(TestRunner.OR_FILE.getProperty("txt_kvi_CompletedPercentage"), TestRunner.OR_FILE.getProperty("Total_completion%_summary"));
 }
 @Then("^Verify that Scheduled % count of Event Summary matches with thw scheduled % KVI$")
 public void verify_that_Scheduled_count_of_Event_Summary_matches_with_thw_scheduled_KVI() throws Throwable {
	 GenericKeywords.Gen_MouseHover(TestRunner.OR_FILE.getProperty("Total_schedule%_summary"));
	 GenericKeywords.Gen_ImplicitWait(2);
	 GenericKeywords.Gen_verifyValue(TestRunner.OR_FILE.getProperty("txt_kvi_dashletSchedulepercentage"), TestRunner.OR_FILE.getProperty("Total_schedule%_summary"));
    
    }
@Then("^Verify the data on  Event Type Performance Dashlet with other dashlet$")

     public void verify_the_data_on_Event_Type_Performance_Dashlet_with_other_dashlet() throws Throwable 
    {
    GenericKeywords.Gen_MouseHover(TestRunner.OR_FILE.getProperty("Event_Type_Perf_Dashlet"));
    GenericKeywords.Gen_ImplicitWait(2);
    GenericKeywords.Gen_Click("views Of dashlest", TestRunner.OR_FILE.getProperty("selector_btn"));
    GenericKeywords.Gen_ImplicitWait(2);
    GenericKeywords.Gen_Click("views Of dashlest", TestRunner.OR_FILE.getProperty("txt_ViewSelector"));
    GenericKeywords.Gen_ImplicitWait(2);
    GenericKeywords.Gen_Click("views Of dashlest", TestRunner.OR_FILE.getProperty("table"));
    GenericKeywords.Gen_ImplicitWait(2);
    GenericKeywords.Gen_verifyValue(TestRunner.OR_FILE.getProperty("txt_kvi_Events"), TestRunner.OR_FILE.getProperty("dashlet_event_value"));
    
    }

@When("^User Select Event By Category and Event Sales$")
public void user_Select_Event_By_Category_and_Event_Sales() throws Throwable {
	
	GenericKeywords.Gen_VerifyElementIsDisplayed("Onehub", TestRunner.OR_FILE.getProperty("OneHub"));
	GenericKeywords.Gen_Click("Collection Link ", TestRunner.OR_FILE.getProperty("Collection_link"));
    GenericKeywords.Gen_Click("Event Sales", TestRunner.OR_FILE.getProperty("lnk_EventSales"));
    GenericKeywords.Gen_ImplicitWait(2);
    GenericKeywords.Gen_VerifyElementIsDisplayed("Sales dashboard", TestRunner.OR_FILE.getProperty("sales_dashboard"));
    GenericKeywords.Gen_ImplicitWait(2);
    GenericKeywords.Gen_Click("Dashboard Link", TestRunner.OR_FILE.getProperty("Dashbaord_link"));
    GenericKeywords.Gen_ImplicitWait(2);
    GenericKeywords.Gen_Click("Event By Category", TestRunner.OR_FILE.getProperty("sales_by_category_link"));
}


@Then("^User select \"([^\"]*)\" and \"([^\"]*)\" using Event Date filter$")
public void user_select_and_using_Event_Date_filter(String StartDate, String EndDate) throws Throwable {
  GenericKeywords.Gen_VerifyElementIsDisplayed("Filter", TestRunner.OR_FILE.getProperty("btn_Filter"));
  GenericKeywords.Gen_ImplicitWait(2);
  GenericKeywords.Gen_Click("Filter", TestRunner.OR_FILE.getProperty("btn_Filter"));
  GenericKeywords.Gen_ImplicitWait(2);
  GenericKeywords.Gen_Click("Event Date", TestRunner.OR_FILE.getProperty("lnk_Filter_DateRange"));
  GenericKeywords.Gen_SendKeys(TestRunner.OR_FILE.getProperty("btn_startDate_tab"),StartDate);
  GenericKeywords.Gen_ImplicitWait(2);
  GenericKeywords.Gen_Click("EndDate", TestRunner.OR_FILE.getProperty("btn_endDate_tab"));
  GenericKeywords.Gen_SendKeys(TestRunner.OR_FILE.getProperty("btn_endDate_tab"),EndDate);
  GenericKeywords.Gen_Click("Apply Filter button", TestRunner.OR_FILE.getProperty("btn_Filter_apply"));
  
  }

@Then("^Verify the KVI available on the Dashboard$")
public void verify_the_KVI_available_on_the_Dashboard(DataTable arg1) throws Throwable {
  
	TestRunner.App_Log.debug("Verify the KVi available on dashboard");
    List<List<String>> data =arg1.raw();
    int size= arg1.raw().size();
    
    for (int i=0;i<size;i++)
    {
    	GenericKeywords.Gen_verifyText(data.get(i).get(0), TestRunner.OR_FILE.getProperty(data.get(i).get(1)));
    }
    
 }

@Then("^Verify the tooltips available on Dashbaord$")
public void verify_the_tooltips_available_on_Dashbaord(DataTable arg1) throws Throwable {

	 List<List<String>> data=arg1.raw();
	 int size=arg1.raw().size();
	 for (int i=0;i<size;i++)
	 {
		 TestRunner.App_Log.debug("Verify the tooltip on dashboard");
		 String dynxapthDashlet="//h2[text()='"+data.get(i).get(0)+"']";
		 GenericKeywords.Gen_VerifyElementIsDisplayed("KVI text", dynxapthDashlet);
		 GenericKeywords.Gen_ImplicitWait(2);
		 GenericKeywords.Gen_MouseHover(dynxapthDashlet);
		 GenericKeywords.Gen_ImplicitWait(2);
		 System.out.println("Verifying tooltip for KVI " +data.get(i).get(0));
		 String ExpectedTooltiptxt=TestRunner.OR_FILE.getProperty(data.get(i).get(1));
		 String ObservedTooltiptxt=GenericKeywords.Gen_getText("//div[@class='b-dropdown popover bottom']");
		 GenericKeywords.Gen_verifyText(TestRunner.OR_FILE.getProperty(data.get(i).get(1)),"//div[@class='b-dropdown popover bottom']");
		 
	 }
}
	 
	 @Then("^Verify Filters are available on dashboard$")
	 
	 public void Verify_Filters_are_available_on_dashboard(DataTable arg1) throws Throwable 
	 {
		 
		 List<List<String>> data=arg1.raw();
		 int size=arg1.raw().size();
		 for (int i=0;i<size;i++) 
	    {
	    	GenericKeywords.Gen_VerifyElementIsDisplayed("Filter", TestRunner.OR_FILE.getProperty("btn_Filter"));
            GenericKeywords.Gen_ImplicitWait(2);
            GenericKeywords.Gen_Click("Filter", TestRunner.OR_FILE.getProperty("btn_Filter"));
            GenericKeywords.Gen_ImplicitWait(2);
            GenericKeywords.Gen_VerifyElementIsDisplayed("Search button", TestRunner.OR_FILE.getProperty("inp_search_filter"));
            GenericKeywords.Gen_VerifyElementIsDisplayed(data.get(i).get(0), TestRunner.OR_FILE.getProperty(data.get(i).get(1)));
            GenericKeywords.Gen_Click(data.get(i).get(0), TestRunner.OR_FILE.getProperty(data.get(i).get(1)));
            String value=GenericKeywords.Gen_getText(TestRunner.OR_FILE.getProperty("second_button_Filter"));
            GenericKeywords.Gen_Click("Select the Filter", TestRunner.OR_FILE.getProperty("second_button_Filter"));
            GenericKeywords.Gen_ImplicitWait(2);
            GenericKeywords.Gen_Click("Apply Filter", TestRunner.OR_FILE.getProperty("btn_Filter_apply"));
            value = "= "+value;
            //String dynXpath = "//span[text()='"+value+"']";
            String dynXpath = "  //span[contains(text(),'"+value+"')]";
            System.out.println(dynXpath);
            
            GenericKeywords.Gen_VerifyElementIsDisplayed(data.get(i).get(0), dynXpath);
            GenericKeywords.Gen_ImplicitWait(2);

            //String dynXpathMouseHover = "//span[not(contains(text(),'Between'))][text()='"+value+"']";
            //GenericKeywords.Gen_MouseHover(dynXpathMouseHover);
            GenericKeywords.Gen_MouseHover(dynXpath);

             //GenericKeywords.Gen_MouseHover(TestRunner.OR_FILE.getProperty("lnk_Mouse_Hover"));
            
            //String dnyXPathClose = "//span[text()='"+value+"']//following-sibling::i[@class='filter-card-icon icon-close-x icon-xs']";
            String dnyXPathClose = "//span[contains(text(),'"+value+"')]//following-sibling::i";
            
            GenericKeywords.Gen_VerifyElementIsDisplayed("Clear Filter",dnyXPathClose);
            //GenericKeywords.Gen_MouseHover(dnyXPathClose);
            GenericKeywords.Gen_ImplicitWait(2);
            GenericKeywords.Gen_Click("Clear Filter",dnyXPathClose);
            GenericKeywords.Gen_ImplicitWait(2);
            
            System.out.println("Filter verified : "+data.get(i).get(0));
            
	    	
	    }
	 }
	 
	 @Then("^Verify the navigations from category Link$")
	 public void verify_the_navigations_from_category_Link() throws Throwable {
		 TestRunner.App_Log.debug("Verifying the navigations from Category Link");
		 GenericKeywords.Gen_Click("Click on Category Name ", TestRunner.OR_FILE.getProperty("nav_Category_lnk"));
		 GenericKeywords.Gen_ImplicitWait(2);
		 GenericKeywords.Gen_pageTitleVerify("Sales By Item");
		 GenericKeywords.Gen_navigateBack();
	     }



@Then("^Verify Columns that are displayed of Sales By Category Dashlet$")
public void verify_Columns_that_are_displayed_of_Sales_By_Category_Dashlet(DataTable arg1) throws Throwable {
	
	TestRunner.App_Log.debug("Verify the columns of sales by category");
	
	List<List<String>> data=arg1.raw();
	int size= arg1.raw().size();
	
		GenericKeywords.Gen_MouseHover(TestRunner.OR_FILE.getProperty("Dashlet_lnk"));
		GenericKeywords.Gen_ImplicitWait(2);
		GenericKeywords.Gen_VerifyElementIsDisplayed("Maximize icon",TestRunner.OR_FILE.getProperty("icon_maximize"));
		GenericKeywords.Gen_ImplicitWait(2);
		GenericKeywords.Gen_Click("Click on maximize", TestRunner.OR_FILE.getProperty("icon_maximize"));
		
		for(int i=0;i<=size;i++)
		{	
		
	    GenericKeywords.Gen_VerifyElementIsDisplayed(data.get(i).get(0), TestRunner.OR_FILE.getProperty(data.get(i).get(1)));
	    GenericKeywords.Gen_ImplicitWait(2);
	    System.out.println("column is displayed :" +data.get(i).get(0));
		
		
	    }
 
}
 

@When("^User select \"([^\"]*)\" and \"([^\"]*)\" Collection$")
public void user_select_and_Collection(String dash, String coll) throws Throwable {
	
	TestRunner.App_Log.debug("click on dashboard");
	GenericKeywords.Gen_verifyText("Onehub", TestRunner.OR_FILE.getProperty("OneHub"));
	GenericKeywords.Gen_ImplicitWait(2);
	GenericKeywords.Gen_Click("Click on Collection", TestRunner.OR_FILE.getProperty("coll_dropdown"));
	GenericKeywords.Gen_Click("Events", TestRunner.OR_FILE.getProperty(coll));
	GenericKeywords.Gen_ImplicitWait(2);
	GenericKeywords.Gen_VerifyElementIsDisplayed("Execution Dashboard", TestRunner.OR_FILE.getProperty("Execution_Dashboard"));

	GenericKeywords.Gen_Click("Click on Execution dashboard", TestRunner.OR_FILE.getProperty("dash_dropdown"));
	GenericKeywords.Gen_ImplicitWait(2);
	GenericKeywords.Gen_Click("Club Analysis", TestRunner.OR_FILE.getProperty(dash));
	
	

}

@Then("^Verify the list of column names in dashlet \"(.*?)\"$")
public void verify_the_list_of_column_names_in_dashlet(String dashlet, DataTable arg2) throws Throwable {
	GenericKeywords.Gen_ImplicitWait(5);
	TestRunner.App_Log.debug("Verify the Columns Available on Dashlet");
	String dynXpathDashlet = "//dashlet[@jpath-title='" + dashlet + "']";
	GenericKeywords.Gen_VerifyElementIsDisplayed("dashlet", dynXpathDashlet);
	List<List<String>> data = arg2.raw();
	int size = arg2.raw().size();
	for (int i = 0; i < size; i++) {
		GenericKeywords.Gen_verifyText(data.get(i).get(0), TestRunner.OR_FILE.getProperty(data.get(i).get(1)));
	} // End of For loop
}// End of Verify the list of column names in dashlet



@When("^User click on Event Exception KVI navigate to the Event Exception Analysis$")
public void user_click_on_Event_Exception_KVI_navigate_to_the_Event_Exception_Analysis() throws Throwable {
 TestRunner.App_Log.debug("Verify the navigation");
 GenericKeywords.Gen_VerifyElementIsDisplayed("Event Exception KVI", TestRunner.OR_FILE.getProperty("txt_club_kvi_Event_Exceptions"));
 GenericKeywords.Gen_Click("Event Exception KVI",  TestRunner.OR_FILE.getProperty("txt_club_kvi_Event_Exceptions"));
 GenericKeywords.Gen_ImplicitWait(5);
 GenericKeywords.Gen_pageTitleVerify("Event Exception Analysis");
 GenericKeywords.Gen_ImplicitWait(3);
 GenericKeywords.Gen_navigateBack();
 
}

@When("^User click on ClubNo navigate to the Club Events Report$")
public void user_click_on_ClubNo_navigate_to_the_Club_Events_Report() throws Throwable {
   
	 TestRunner.App_Log.debug("Verify the navigation");
	 GenericKeywords.Gen_VerifyElementIsDisplayed("Club No", TestRunner.OR_FILE.getProperty("nav_club_no"));
	 GenericKeywords.Gen_Click("Club No",  TestRunner.OR_FILE.getProperty("nav_club_no"));
	 GenericKeywords.Gen_ImplicitWait(5);
	 GenericKeywords.Gen_pageTitleVerify("Club Events Report");
	 GenericKeywords.Gen_ImplicitWait(3);
	 GenericKeywords.Gen_navigateBack();
    
}

}


	
	

