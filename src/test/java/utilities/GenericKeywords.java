package utilities;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

//import org.elasticsearch.indices.recovery.PeerRecoverySourceService.Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

//import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

//import testRunner.SoftAssertions;
import testRunner.TestRunner;

public class GenericKeywords extends TestRunner {
	
	public static WebElement element;
	public static WebDriverWait wait;
	public static String dynamicText;
	public  JavascriptExecutor js;
	public  Actions act;
	public static long startTime, endTime, totalTime;
    public  static SoftAssertions softAssertions=new SoftAssertions();
	//public static SoftAssertions softAssertions = new SoftAssertions();

//to launch browser
//	public static void Gen_openBrowser(String data) 
//	{
//	
//       TestRunner.App_log.debug("Launching the Browser: " +data);
//    try {
//		if(TestRunner.Config_Main.get(data).equals("Chrome"))
//		{
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\src\\main\\java\\Drivers\\chromedriver.exe");
//			TestRunner.driver=new ChromeDriver();
//			TestRunner.App_log.debug("Chrome Browser Launched ");
//		}
//		else if(testRunner.TestRunner.Config_Main.get(data).equals("Mozilla"))
//		{
//			TestRunner.driver=new FirefoxDriver();
//			TestRunner.App_log.debug("Mozilla Browser Launched ");
//		}
//		else if(TestRunner.Config_Main.get(data).equals("InternetExplorer"))
//		{
//			TestRunner.driver =new InternetExplorerDriver();
//			TestRunner.App_log.debug("IE Browser Launched "); 
//		}
//		
//			
//	}catch(Exception e)
//    {
//		TestRunner.App_log.debug("Not able to launch the browser" +data+ ",xpath dfailed" +e.getMessage());
//		Assert.fail("Not able to click");
//    }
//   }
//===============================
	public static void Gen_openBrowser(String data) 
	{
		TestRunner.App_Log.debug("Launching the browser : "+data);
		try {
			/* To Launch Mozilla Browser */
			if (TestRunner.Config_Main.getProperty(data).equals("Mozilla")) {
				TestRunner.driver = new FirefoxDriver();
				TestRunner.App_Log.debug("Mozila browser is launched . . . .");
			} // End of if_statement
			/* To Launch IE Browser */
			else if (TestRunner.Config_Main.getProperty(data).equals("IE")) {

				TestRunner.driver = new InternetExplorerDriver();
				TestRunner.App_Log.debug("IE browser is launched . . . .");
			} // End of if_else statement
			/* To Launch Chrome Browser */
			 else if (TestRunner.Config_Main.getProperty(data).equals("Chrome")) 
			   {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
			    options.addArguments("disable-infobars");
			    System.setProperty("webdriver.chrome.driver",
			    System.getProperty("user.dir") + "\\src\\main\\java\\Drivers\\chromedriver.exe");
			    TestRunner.driver = new ChromeDriver(options);
			    TestRunner.App_Log.debug("Chrome browser is launched . . . .");
			   } // End of if_else_if statement
			//int implicitWaitTime = (10);
			//TestRunner.driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		} catch (Exception e) {
			TestRunner.App_Log.debug("Could not Launch the Browser : "+ data+ "Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_openBrowser(String data) =========================	
//  public static void Gen_navigate(String data)
//     {
//  TestRunner.driver.get((String) TestRunner.Config_Main.get(data));
//     }
//==========================2 > Method for navigating to specific URL */
		public static void Gen_navigate(String data) 
		{
			try 
			{
				TestRunner.App_Log.debug("Navigating to URL : "+data);
				TestRunner.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				TestRunner.driver.manage().window().maximize();
				TestRunner.driver.get(data);
			} catch (Exception e) {
				TestRunner.App_Log.debug("Could not navigate to : "+ data+ " Reason : " + e.getMessage());
			}// End of Try_Catch statement
		} // End of Gen_navigate(String data)
		
//================================
//  public static void Gen_SendsKey_Username(String data ,String Value)
//    {
//	   TestRunner.driver.findElement(By.xpath(data)).click();
//	   TestRunner.driver.findElement(By.xpath(data)).sendKeys(Value);
//    }
//  public static void Gen_SendsKey_Password(String data ,String Value)
//   {
//	   TestRunner.driver.findElement(By.xpath(data)).click();
//	   TestRunner.driver.findElement(By.xpath(data)).sendKeys(Value);
//   }
//=======================
		/* 3 > Method to perform send keys operation  */
		public static void Gen_SendKeys(String data, String value) 
		{
			try 
			{
				TestRunner.App_Log.debug("Entering text : "+value);
				wait = new WebDriverWait(TestRunner.driver, waitTime);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(data)));
				TestRunner.driver.findElement(By.xpath(data)).click();
				TestRunner.driver.findElement(By.xpath(data)).clear();
				TestRunner.driver.findElement(By.xpath(data)).sendKeys(value);
				//TestRunner.test.log(Status.PASS, "Send keys Passed to : "+data);
			} catch (Exception e) {
				//TestRunner.test.log(Status.FAIL, "Failed");
				Assert.fail("Send keys failed to : "+data);
				TestRunner.App_Log.debug("Not able to Enter text at "+ data+ " Reason : "  + e.getMessage());
			}// End of Try_Catch statement
		} //End of Gen_SendKeys(String data, String value)
//===========password
		/* 4 > Method to perform send keys operation for Only password */
		public static void Gen_SendPassword(String data, String value) 
		{
			try 
			{
				TestRunner.App_Log.debug("Password entered.");
				TestRunner.driver.findElement(By.xpath(data)).click();
				TestRunner.driver.findElement(By.xpath(data)).clear();
				TestRunner.driver.findElement(By.xpath(data)).sendKeys(value);
				//TestRunner.test.log(Status.PASS, "Password entered successfully. : "+data);
			}catch (Exception e){
				//TestRunner.test.log(Status.FAIL, "Failed");
				TestRunner.App_Log.debug("Password entered operation failed." +" Reason : "+  e.getMessage());
				Assert.fail("Password entered operation failed."+ " Reason : "+ e.getMessage());
			}// End of Try_Catch statement
		} //End of Gen_SendPassword(String data, String value) 
		
//   public static void Gen_click(String data,String value)
//  
//   {  
//	  try {
//	   
//     // TestRunner.App_log.debug("Cliking to the : " +data);
//      wait=new WebDriverWait(TestRunner.driver,TestRunner.waitTime);
//      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
//      wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
//      TestRunner.driver.findElement(By.xpath(value)).click();
//         }catch(Exception e)
//       {
//      // TestRunner.App_log.debug("Not able to the click" +data+ "xpath failed" +value);
//        //Assert.fail("Not able to the click" +data+ "xpath failed" +value);
//        	 System.out.println("xpath not found");
//       }
//   }
//	=================click=======
/* 5 > Method to perform click operation  */
		public static void Gen_Click(String data, String value) 
		{
			try {
				TestRunner.App_Log.debug("Clicking to : "+data);
				wait = new WebDriverWait(TestRunner.driver, TestRunner.waitTime);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
				TestRunner.driver.findElement(By.xpath(value)).click();
				//TestRunner.test.log(Status.PASS, "Clicking Passed to : "+data);
			}catch (Exception e){
				TestRunner.App_Log.debug("Not able to click "+data+", xpath failed. "+value+"" + e.getMessage());
				Assert.fail("Not able to click "+data+", \nxpath failed. "+value+""+ " Reason : "+ e.getMessage());
				//TestRunner.test.log(Status.FAIL, "Failed");
			}// End of Try_Catch statement
		} // End of Gen_Click(String data)
		
		
//   public static void Gen_VerifyElementIsDisplayed(String data,String value)
//   {
//	  try {
//		  //TestRunner.App_log.debug("Verify element is dipalyed" +data);
//		      wait=new WebDriverWait(TestRunner.driver,TestRunner.waitTime);
//		      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
//		      wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
//		    TestRunner.driver.findElement(By.xpath(value)).isDisplayed();
//		  
//		  
//	    }catch(Exception e)
//	  {
//	    	TestRunner.App_log.debug("Verify that element is displayed" +data+ "xpath failed" +value);
//	       // Assert.fail("Verify that element is displayed" +data+ "xpath failed" +value);
//	    	 System.out.println("xpath not found");
//	       }  
//	  }
//   ===========Verify
		
		/* 6 > Method to perform verification of element is displayed  */
		public static void Gen_VerifyElementIsDisplayed(String data, String value) 
		{
			try {
				wait = new WebDriverWait(TestRunner.driver, TestRunner.waitTime);
				//wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(value)))));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
				System.out.println("Verifying element is displayed : "+data);
				System.out.println("Verifying Value is displayed : "+value);
				TestRunner.App_Log.debug("Verifying element is displayed : "+data);
				TestRunner.driver.findElement(By.xpath(value)).isDisplayed();
				//TestRunner.test.log(Status.PASS, "Verification of Element is passed for : "+data);
			} catch (Exception e) {
				TestRunner.App_Log.debug("Verification of Element is failed for "+data+", \nxpath failed. "+value+"" + e.getMessage());
				Assert.fail("Verification of Element is failed for "+data+", \nxpath failed. "+value+ " Reason : "+ e.getMessage());
				//softAssertions.assertThat("Verification of Element is failed for "+data+", \nxpath failed. "+value+ " Reason : "+ e.getMessage());
				//TestRunner.test.log(Status.FAIL, "Failed");
			}// End of Try_Catch statement
		} // End of Gen_VerifyElementIsDisplayed(String data)
		
   
//   public static void Gen_Verifytxt(String data,String object)throws Exception
//   {
//	   String text=null;
//	   try
//	   {
//		wait=new WebDriverWait(TestRunner.driver,TestRunner.waitTime);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(object)));
//		text=driver.findElement(By.xpath(object)).getText().trim();
//		System.out.println("Observed " +text);
//		System.out.println("Expected " +data);
//		if(data.equals(text))
//		{
//			System.out.println("status is pass");//Report code Required
//		}
//		else
//		{
//			System.out.println("status is fail");//Report code Required
//		}
//		   
//		   
//	   }catch(Exception e )
//	   {
//		   System.out.println("Text not found");
//	   }
//   }
//   
//   public static void Gen_MouseHover(String object) 
//   {
//	    try 
//	    {
//	   wait=new WebDriverWait(TestRunner.driver,TestRunner.waitTime);
//	   wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(object))));
//	   wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object)));
//	   Actions action=new Actions(driver);
//	   action.moveToElement(driver.findElement(By.xpath(object))).build().perform();
//	   }catch(Exception e)
//	    {
//	    	//TestRunner.App_log.debug("Element not founsd");
//		   System.out.println("element not found");
//	    }
//	   
//   }
//   public static void Gen_webDriverWait_tillElementIsInvisible(String data, String value) throws Exception 
//	{
//		try {
//			//App_Log.debug("Waiting for invisibility of element present");
//			wait = new WebDriverWait(TestRunner.driver, TestRunner.waitTime);
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(value)));
//			//TestRunner.test.log(Status.PASS, "Wait for loader invisibility");
//			//System.out.println("=========> Wait for loader invisibility ");
//		} catch (Exception e) {
//			
//			System.out.println("Elemnet is visible");
//		}
//	
//
//	  
//	   
//   }
//   public  static void Gen_ImplicitWait(int value)
//   {  try 
//   
//       {
//	   int seconds=value*1000;
//	   
//	   Thread.sleep(seconds);
//	   }catch(Exception e)
//   
//         {
//    	   System.out.println("Implicit wait fail");
//         }
//      }
//   
//   public static void Gen_verifyTextContains(String expected ,String observed)
//   {
//	   wait=new WebDriverWait(TestRunner.driver,TestRunner.waitTime);
//	   System.out.println("Expected Text " +expected);
//	   System.out.println("Observed Text " +observed);
//	   
//	   if(observed.contains(expected))
//	   {
//		   System.out.println("Text is verified");
//	   }
//	   else
//	   {
//		   System.out.println("text is not correct");
//	   }
//   }
//   public static String Gen_getText(String object)
//   { 
//	   String str=null;
//	   try {
//	   wait=new WebDriverWait(TestRunner.driver,TestRunner.waitTime);
//	   wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(object))));
//	     str = driver.findElement(By.xpath(object)).getText();
//	   }
//	   catch(Exception e)
//	   {
//		   System.out.println("text notfound");
//	   }
//		return str;
//   }
//	========verify text

		/* 15 > Method to verify the actual result with the expected result */
	 	public static void Gen_verifyText(String data, String object)throws Exception 
	 	{
	 	String text=null;
		 	try 
		 	{
		 		App_Log.debug("Verifying test matching for : "+data);
		 		wait = new WebDriverWait(TestRunner.driver, waitTime);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(object)));
				text = driver.findElement(By.xpath(object)).getText().trim();
				System.out.println(" Expected Text : " +data);
				System.out.println(" Observed Text : " +text);
				if(data.equals(text))
				{
		 			TestRunner.test.log(Status.PASS, "Text verified Successfully.");
				}else{
					throw new Exception("Expected text:"+data+" -----     "+"Actual text:"+text);
				} // End if_else statement
		 	}catch (Exception e){
		 		TestRunner.App_Log.debug("Expected Text : "+data+ " Observed Text : "+text);
				TestRunner.App_Log.debug("Not able to verify --- " + " Reason : "+  e.getMessage());
				//Assert.fail("Unable to verify Text; Expected Text : "+data+ " Observed Text : "+text+ " Reason : "+ e.getMessage());
				softAssertions.fail("Nat able to Verify text" +data+ "Reason: "+e.getMessage());
				
			}// End of Try_catch statement
	 	}//
	 	public static void Gen_verifyTextContains(String expected, String observed)throws Exception 
	 	{
	 		try 
		 		{
		 		App_Log.debug("Verifying test matching for : "+expected);
		 		wait = new WebDriverWait(TestRunner.driver, waitTime);
				System.out.println("Expected Text : " +expected);
				System.out.println("Observed Text : " +observed);
				if(observed.contains(expected))
				{
		 			TestRunner.test.log(Status.PASS, "Text verified Successfully.");
		 			//System.out.println("Text verified Successfully +++++++++++++++++ ");
				}else{
		 			System.out.println("-------------------- Text NOT verified -------------------- ");
					throw new Exception("Expected text:"+expected+" -----     "+"Actual text:"+observed);
				} // End of if_else statement
		 	}catch (Exception e)
		 		{
		 		TestRunner.App_Log.debug("Expected Text : "+expected+ " Observed Text : "+observed);
				TestRunner.App_Log.debug("Not able to verify --- " + " Reason : "+  e.getMessage());
				Assert.fail("Unable to verify Text Expected Text : "+expected+ " Observed Text : "+observed+ " Reason : "+ e.getMessage());
				//softAssertions.assertThat("Unable to verify Text Expected Text : "+expected+ " Observed Text : "+observed+ " Reason : "+ e.getMessage());
			} // End of try Catch statement
	 	} // End of Gen_verifyTextContains(String expected, String observed)
//   
//	public static String Gen_getTextReturn(String object)
//	{
//		try 
//		{
//			//App_Log.debug("Checking presence of element " + object);
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object)));
//			dynamicText = TestRunner.driver.findElement(By.xpath(object)).getText();
//			//TestRunner.test.log(Status.PASS, "Successfully got text from web page");
//        return dynamicText;
//		}catch (Exception e){
//		Assert.fail("Clipboard method has failed."+ " Reason : "+ e.getMessage());
//		return null;
//		}// End of Try_Catch statement
//	} 
//
//	
//	public static void Gen_VerifyValue(String data,String object)
//	
//    {
//		try {
//			
//		   Thread.sleep(1000);
//		  String str1= TestRunner.driver.findElement(By.xpath(data)).getText();
//		   //Thread.sleep(5000);
//		   Thread.sleep(1000);
//		   System.out.println("kvi:" + str1);
//		   String str2 =TestRunner.driver.findElement(By.xpath(object)).getText();
//		   System.out.println(str2);
//		     if(str1.equals(str2))
//		  {
//			System.out.println("Value matched");
//		   }
//		   else
//		  {
//			System.out.println("Value not matched");
//		   }
//		
//          } catch(Exception e)
//             {
//     	System.out.println("error");
//	       
//             }


		/* 7 > Method to close browser */
		public static void Gen_CloseBrowser() 
		{
			try {
				TestRunner.App_Log.debug("Closing browser");
				TestRunner.driver.quit();
			} catch (Exception e) {
				TestRunner.App_Log.debug("Not able to close browser." + " Reason : "+  e.getMessage());
			}// End of Try_Catch statement
		} //End of Gen_CloseBrowser() 
		/* 8 > Method for verification of text is displayed  */
		public static void Gen_VerifyTextIsDisplayed(String data) 
		{
			try {
				TestRunner.App_Log.debug("Verifying text is displayed : "+data);
				TestRunner.driver.findElement(By.xpath(data)).isDisplayed();
				//TestRunner.driver.findElement(By.xpath(data)).getText();
				TestRunner.test.log(Status.PASS, "Verification of text is passed for : "+data);
			} catch (Exception e) {
				TestRunner.App_Log.debug("Not able to Verify text" + " Reason : "+  e.getMessage());
				//TestRunner.test.log(Status.FAIL, "Failed");
				Assert.fail("Not able to Verify text"+ " Reason : "+ e.getMessage());
			}// End of Try_Catch statement
		} // End of Gen_VerifyTextIsDisplayed(String data)
		/* 9 > Method to refresh the page */
		public static void Gen_Refresh(String object, String data) 
		{
			try {
				TestRunner.App_Log.debug("Refreshing page ");
				driver.navigate().refresh();
				TestRunner.test.log(Status.PASS, "Refreshing is passed for : "+data);
			} catch (Exception e) {
				TestRunner.App_Log.debug("Refreshing of page is failed." + " Reason : "+  e.getMessage());
				Assert.fail("Refreshing of page is failed. "+ " Reason : "+ e.getMessage());
			}// End of Try_Catch statement
		} // End of Gen_Refresh(String object, String data)
		
		/* 10 > Method to perform Scroll up or down */
		public static void Gen_scrollUpDown(String data) 
		{
			try{
				App_Log.debug("Scrolling to Web Element");
				wait = new WebDriverWait(driver, waitTime);
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(data)));
				TestRunner.test.log(Status.PASS, "Scrolling to Web Element is successful");
				Thread.sleep(2000);
			}catch (Exception e){
				TestRunner.App_Log.debug("Not able to scroll to Web Element" + " Reason : "+  e.getMessage());
				Assert.fail("Not able to scroll to Web Element"+ " Reason : "+ e.getMessage());
			} // End of Try Catch statement
		} // End of Gen_scrollUpDown(String data)
		
		/* 11 > Method to perform click operation by using link text */
		public static void Gen_clickByLinkText(String object, String data) 
		{
			try {
				App_Log.debug("Clicking on Webelement using link text" + object);
				wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.linkText((data))))));
				driver.findElement(By.linkText(data)).click();
				TestRunner.test.log(Status.PASS, "Successful clicking on link");
			}catch (Exception e){
				TestRunner.App_Log.debug("Clicking on Webelement using link text is failed  " + " Reason : "+  e.getMessage());
				Assert.fail("Clicking on Webelement using link text is failed. "+ " Reason : "+ e.getMessage());
			}// End of Try_Catch statement
		} // End of Gen_clickByLinkText(String object, String data) 

		/* 12 > Method to verify button clickable or not*/
		public static void Gen_VerifyButton(String object, String data) {
			try {
				App_Log.debug("Verifying Button is Enabled or Not " + object);
				Gen_webDriverWaitByPresence(object);
				driver.findElement(By.xpath(object)).isEnabled();
				TestRunner.test.log(Status.PASS, "Button is clickable");
			}catch (Exception e){
				TestRunner.App_Log.debug("Button is not clickable" + " Reason : "+  e.getMessage());
				Assert.fail("Button is not clickable"+ " Reason : "+ e.getMessage());
			}// End of Try_Catch statement
		} // End of Gen_VerifyButton(String object, String data)
		/* 13 > Method to enter text in textfield */
		public static void Gen_input(String object, String data) throws Exception 
		{
			try {
				App_Log.debug("Entering the text in " + object);

				wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));

				driver.findElement(By.xpath(object)).sendKeys(data);
				TestRunner.test.log(Status.PASS, "Entered Text");
			} catch (StaleElementReferenceException e) {
				wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
				driver.findElement(By.xpath(object)).sendKeys(data);
				TestRunner.test.log(Status.PASS, "Entered Text successfully.");
			} catch (Exception e) {
				TestRunner.App_Log.debug("Not able to Enter text " + " Reason : "+  e.getMessage());
				Assert.fail("Not able to Enter text"+ " Reason : "+ e.getMessage());
			}  // End of Try_catch statement
		} // End of Gen_input(String object, String data)

		/* 18 > Method to switch tab */
		public static void Gen_switchTab(String object, String data) throws Exception 
		{
			try {
				App_Log.debug("Verifying Switching to Tab");
				int tab = Integer.parseInt(data);
				Set<String> wind_Ids = driver.getWindowHandles();
				Iterator<String> it = wind_Ids.iterator();
				int i;
				for (i = 0; i < wind_Ids.size(); i++) {
					String curr_window = (String) it.next();
					if (i + 1 == tab) {
						driver.switchTo().window(curr_window);
					}
				} // End of For loop
				TestRunner.test.log(Status.PASS, "Tab Switched Successfully.");
			} catch (Exception e) {
				TestRunner.App_Log.debug("Unable to switch to tab " + " Reason : "+  e.getMessage());
				Assert.fail("Unable to switch to tab"+ " Reason : "+ e.getMessage());
			} // End of try Catch statement
		} // End of Gen_switchTab(String object, String data)
		/* 20 > Method to Mouse hover on Web Element*/
		public static void Gen_MouseHover(String object) 
		{
			try {
				App_Log.debug("Checking presence of element " + object);
				System.out.println("Checking presence of element " + object);
				wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object)));
				Thread.sleep(2000);
				Actions builder = new Actions(driver);
	            builder.moveToElement(driver.findElement(By.xpath(object))).build().perform();
	            //TestRunner.test.log(Status.PASS, "Mouse Hovered Successfully.");
			}catch (Exception e){
				TestRunner.App_Log.debug("Enter is not present to Mouse Hover " + " Reason : "+  e.getMessage());
				Assert.fail("Element not present to Mouse Hover"+ " Reason : "+ e.getMessage());
				//softAssertions.assertThat("Element not present to Mouse Hover"+ " Reason : "+ e.getMessage());
			} // End of try Catch statement
		}  // End of Gen_MouseHover(String object) 
		
		/* 21 > Get css value */
		public static void Gen_verifyCSS(String object, String data,String dataOne) throws Exception 
		{
			try {
				App_Log.debug("Verifying CSS colour value");
				wait = new WebDriverWait(driver, waitTime);
				
				wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
				String text = driver.findElement(By.xpath(object)).getCssValue(data);
				if(dataOne.equals(text))
				{
					TestRunner.test.log(Status.PASS, "CSS verification completed.");
				}else{
					throw new Exception("Expected CSS value :"+data+" -----     "+"Actual CSS value :"+text);
				} // End of if_else statement
				TestRunner.test.log(Status.PASS, "CSS verification completed.");
			} catch (Exception e) {
				TestRunner.App_Log.debug("CSS value verification failed." + " Reason : "+  e.getMessage());
				Assert.fail("CSS value verification failed."+ " Reason : "+ e.getMessage());
			} //  End of try Catch statement
		}  // End of Gen_verifyCSS(String object, String data,String dataOne) 
		/* 22 > Get page title*/
		public static void Gen_pageTitleVerify(String data) throws Exception
		{
			String text = null;
			try {
				App_Log.debug("Verifying page title");
				text = driver.getTitle();
				
				System.out.println(text);
				System.out.println(data);
				if(text.equals(data))
				{
					App_Log.debug("Verify page title completed.");
					//TestRunner.test.log(Status.PASS, "Verify page title completed.");
				}else{
					throw new Exception("Expected page title:"+data+" -----     "+"Actual page title:"+text);
				} // End of if_else statement
			} catch (Exception e){
				//TestRunner.App_Log.debug("Webpage title verify failed, Expected page title:"+data+" - "+"Actual page title:"+text);
				Assert.fail("Webpage title verify failed, Expected page title:"+data+" - "+"Actual page title:"+text+ " Reason : "+ e.getMessage());
			}// End of Try_Catch statement
		} // End of Gen_pageTitleVerify(String data)
		
		/* 23 >  To perform click operation by xpath */
		public static void Gen_JavaScriptclick(String object, String data) 
		{
			try {
				App_Log.debug("Clicking on Webelement using Javascript executor" + object);
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(object)));
				TestRunner.test.log(Status.PASS, "Clicked on Webelement using Javascript executor Successfully.");
			}catch (Exception e){
				TestRunner.App_Log.debug("Not able to click on Webelement using Javascript executor." + " Reason : "+  e.getMessage());
				Assert.fail("Not able to click on Webelement using Javascript executor."+ " Reason : "+ e.getMessage());
			}// End of Try_Catch statement
		} // End of Gen_JavaScriptclick(String object, String data) 
		/* 24 > Navigate back to previous page */
	    public static void Gen_navigateBack()
	    {
	          try {
	                App_Log.debug("Verifying Navigation back to previous page ");
	                driver.navigate().back();
	               // TestRunner.test.log(Status.PASS, "Navigation to previous page Successfully, by clicking to Back button");
	          } catch (Exception e) {
	              	  TestRunner.App_Log.debug("Unable to click to Back button " + " Reason : "+  e.getMessage());
	                  Assert.fail("Unable to click to Back button"+ " Reason : "+ e.getMessage());
	          }// End of Try_Catch statement
	    } // End of Gen_navigateBack()

	    /* 25 > To verify disabled button*/
	    public static void Gen_VerifyButtonFalse(String object, String data) 
	    {
	          try {
	                App_Log.debug("Verifying button is Enable or Not" + object);
	                //Gen_webDriverWaitByPresence(object);
	                //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object)));
	                driver.findElement(By.xpath(object)).isEnabled();
	                TestRunner.App_Log.debug("Element is clickable");
	                Assert.fail("Element is clickable");
	                TestRunner.test.log(Status.FAIL, "Element is not clickable");
	          }catch (Exception e){
	                TestRunner.test.log(Status.PASS, "Element is not clickable");
	                
	          }// End of Try_Catch statement
	    }  // End of Gen_VerifyButtonFalse(String object, String data) 
	    
	    /* 26 > Method to verify attribute field */
	    public void Gen_verifyAttribute(String object, String data,String dataTwo)throws Exception 
	    {
	    	try 
	    	{
	          App_Log.debug("Verifying attribute");
	          wait = new WebDriverWait(driver, waitTime);
	          wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
	          String text = driver.findElement(By.xpath(object)).getAttribute(data).trim();
	          if(dataTwo.equals(text))
	          {
	        	  TestRunner.test.log(Status.PASS, "Attribute Verified Successfully.");
	          }else{
	              throw new Exception("Expected attribute value:"+dataTwo+" -----     "+"Actual attribute value:"+text);
	          } // End of if_else statement
	    	}catch (Exception e){
	              TestRunner.App_Log.debug("Unable to verify attribute value" + " Reason : "+  e.getMessage());
	              Assert.fail("Unable to verify attribute value"+ " Reason : "+ e.getMessage());
	    	}// End of Try_Catch statement
	    } // End of Gen_verifyAttribute(String object, String data,String dataTwo)
	    
	    /* 27 > To get text from web page */
		public static String Gen_getText(String object)
		{
			String str=null;
			try {
				App_Log.debug("Collecting text from element " + object);
				wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
				str = driver.findElement(By.xpath(object)).getText();
				TestRunner.test.log(Status.PASS, "Successfully got text from element");
			} catch (Exception e) {
				 TestRunner.App_Log.debug("Unable to get text from element" + " Reason : "+  e.getMessage());
				softAssertions.assertThat("Unable to get text from element"+ " Reason : "+ e.getMessage());
			}// End of Try_Catch statement
			return str;
		} // End of Gen_getText(String object)
		
		/* 28 > Clip board for robot class */
	    public static void Gen_Clipboard(String object, String data) throws Exception 
	    {
		      try 
		      {
		            App_Log.debug("Entering the data from clipboard in ");
		            StringSelection stringSelection = new StringSelection(data);
		            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		            Robot robot = new Robot();
		            robot.keyPress(KeyEvent.VK_CONTROL);
		            robot.keyPress(KeyEvent.VK_V);
		            robot.keyRelease(KeyEvent.VK_V);
		            robot.keyRelease(KeyEvent.VK_CONTROL);
		            robot.keyPress(KeyEvent.VK_ENTER);
		            robot.keyRelease(KeyEvent.VK_ENTER);
		            //driver.findElement(objectLocator(object)).sendKeys(data);
		            TestRunner.test.log(Status.PASS, "Successfully copied text to Clip board");
		      }catch (Exception e){
		            TestRunner.App_Log.debug("Clipboard method has failed. " + " Reason : "+  e.getMessage());
		            Assert.fail("Clipboard method has failed."+ " Reason : "+ e.getMessage());
		      }// End of Try_Catch statement
	    } // End of Gen_Clipboard(String object, String data)
	    
	    /* 29 > To get text from web page and return value*/
	   	public static String Gen_getTextReturn(String object)
	   	{
	   		try 
	   		{
	   			App_Log.debug("Checking presence of element " + object);
	   			System.out.println("Checking presence of element " + object);
	   			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object)));
	   			dynamicText = TestRunner.driver.findElement(By.xpath(object)).getText();
	   			TestRunner.test.log(Status.PASS, "Successfully got text from web page");
	            return dynamicText;
	   		}catch (Exception e){
	   		 
	   			//softAssertions.assertThat("Clipboard method has failed."+ " Reason : "+ e.getMessage());
				Assert.fail("Clipboard method has failed."+ " Reason : "+ e.getMessage());
				return null;
	   		}// End of Try_Catch statement
	   	} // End of Gen_getTextReturn(String object)
		/* 30 > To select first option from drop down and verify */
		public static void Gen_firstSelectedOption(String object, String data) 
		{
			try 
			{ 
				App_Log.debug("Selecting First Text");
				wait=new WebDriverWait(driver,waitTime);
				wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
				Select ele=new Select(driver.findElement(By.xpath(object)));
				ele.getFirstSelectedOption().getText().equals(data);
				TestRunner.test.log(Status.PASS, "Successfully selected first option.");
			} catch (Exception e) {
				TestRunner.App_Log.debug("Not able to selected first option from dropdown " + " Reason : "+  e.getMessage());
				Assert.fail("Not able to selected first option from dropdown"+ " Reason : "+ e.getMessage());
			}// End of Try_Catch statement
				
				
				
				
			
		} // End of Gen_firstSelectedOption(String object, String data) 
		
/* 31 > Clear the field */
public static void Gen_clearField(String object, String data)
{
      try {
            App_Log.debug("Verifying clear the text field functionality");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.xpath(object)).clear();
            TestRunner.test.log(Status.PASS, "Successfully cleared the field");
      } catch (Exception e) {
    	  TestRunner.App_Log.debug("Not able cleared the field " + " Reason : "+  e.getMessage());
            Assert.fail("Not able cleared the field "+ " Reason : "+ e.getMessage());
      }// End of Try_Catch statement
} // End of Gen_clearField(String object, String data)

/* 32 > Verify list of elements count verify*/
public static void Gen_VerifyListOfElementsCount(String object, String data) 
{
     try 
     {
      App_Log.debug("Validating count for list of elements");
      GenericKeywords.Gen_webDriverWaitByPresence(object);
      wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
      List<WebElement> list=GenericKeywords.driver.findElements(By.xpath(object));
         int count = list.size();
         String text=Integer.toString(count);
         if(data.equals(text))
          {
        	 TestRunner.test.log(Status.PASS, "Successfully verified count");
          }
          else
          {
                throw new Exception("Expected count size :"+data+" -----     "+"Actual count size  :"+text);
          } // End of if_else statement
       } catch (Exception e){
         TestRunner.App_Log.debug("Not able to verify list count " + " Reason : "+  
       e.getMessage());
         Assert.fail("Unable to verify list count"+ " Reason : "+ e.getMessage());
       }
       }// End of Try_Catch statement
     
     /*33 > Method to verify the actual result not matching with expected result */
  	public static void Gen_verifyTextNotEqual(String object, String data)throws Exception 
  	{
 	 	try 
 	 	{
 	 		App_Log.debug("Verifying test not matching");
 			wait = new WebDriverWait(driver, waitTime);
 			wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
 	 		String text = driver.findElement(By.xpath(object)).getText().trim();
 	 		if(!data.equals(text))
 			{
 	 			TestRunner.test.log(Status.PASS, "Text NOT obeserved, and test case passed.");
 			}
 			else
 			{
 				throw new Exception("Text observed, and test case failed.");
 			} // End of if_else statement
 	 	}catch (Exception e){
 			TestRunner.App_Log.debug("Text observed, and test case failed." + " Reason : "+  e.getMessage

 ());
 			Assert.fail("Text observed, and test case failed. Reason : "+ e.getMessage());
 		}// End of Try_Catch statement
  	}// End of Gen_verifyTextNotEqual(String object, String data)
  	
  	/*34 > Verify list of elements present*/
     public static void Gen_VerifyListOfElementsExist(String object, String data) 
     {
          try {
           App_Log.debug("Validating list of elements");
           GenericKeywords.Gen_webDriverWaitByPresence(object);
           
           List<WebElement> list=GenericKeywords.driver.findElements(By.xpath(object));
           
           for(int i=0;i<list.size();i++)
           {
         	  int p=list.size();
         	  String text=list.get(i).getText().trim();
             if(text.equals(data))
             {
             	
             }else{
             	throw new Exception(data+" Data not present");
             } // End of if_else statement
           } // End of for loop
             TestRunner.test.log(Status.INFO, "Verified data  present");
             TestRunner.test.log(Status.PASS, "Successfully verified.");
           }catch (Exception e){
         	TestRunner.App_Log.debug("Data not present" + " Reason : "+  e.getMessage());
  			Assert.fail("Data not present"+ " Reason : "+ e.getMessage());
          }// End of Try_Catch statement
        } // End of Gen_VerifyListOfElementsExist(String object, String data) 
     
     /*35 > Verify list of elements not present*/
     public static void Gen_VerifyListOfElementsNotExist(String object, String data) 
     {
          try {
           App_Log.debug("Validating list of elements");
           GenericKeywords.Gen_webDriverWaitByPresence(object);
           
           List<WebElement> list=GenericKeywords.driver.findElements(By.xpath(object));
           
           for(int i=0;i<list.size();i++)
           {
         	  int p=list.size();
         	  String text=list.get(i).getText().trim();
             if(!text.equals(data))
             {
             	
             }
             else
             {
             	throw new Exception(data+" Data not present");
             } // End of if_else statement
           } // End of For loop
           	TestRunner.test.log(Status.INFO, "Verified data present");
             TestRunner.test.log(Status.PASS, "Successfully verified.");
           }catch (Exception e){
         	TestRunner.App_Log.debug("Data not present " + " Reason : "+  e.getMessage());
  			Assert.fail("Data not present"+ " Reason : "+ e.getMessage());
          }// End of Try_Catch statement
        } // End of Gen_VerifyListOfElementsNotExist(String object, String data) 
     
     /* 36 > Method to verify to dynamic text to web page*/
  	public static void Gen_verifyTextTwoDynamic(String object)throws Exception 
  	{
 	 	try 
 	 	{
 	 		App_Log.debug("Verifying test matching");
 			wait = new WebDriverWait(driver, waitTime);
 			wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
 	 		String text = driver.findElement(By.xpath(object)).getText().trim();
 			if(dynamicText.equals(text))
 			{
 	 			TestRunner.test.log(Status.PASS, "Successfully matched");
 			}else{
 				throw new Exception("Data are not matching");
 			} // End of if_else statement
 	 	}catch (Exception e){
 				TestRunner.App_Log.debug("Unable to verify text " +" Reason : "+   e.getMessage());
 				Assert.fail("Unable to verify text"+ " Reason : "+ e.getMessage());
 		}// End of Try_Catch statement
  	} // End of Gen_verifyTextTwoDynamic(String object) 
  	
  	/* 37 > Verify check box selected or not */
     public static void Gen_IsSelected(String object, String data) 
     {
           try {
                 App_Log.debug("Verify checkbox " + object);
                 boolean Value = driver.findElement(By.xpath(object)).isSelected();
                 String ActualValue= String.valueOf(Value);
                 if(ActualValue.equals(data))
                 {
                 	TestRunner.test.log(Status.PASS, "Successfully checked.");
                 }
                 else
                 {
                   throw new Exception("Expected check box value:"+data+" -----     "+"Actual check box  value:"+ActualValue);
                 } // End of if_else statement
           }catch (Exception e){
                 TestRunner.App_Log.debug("Unable to verify checkbox " + " Reason : "+  e.getMessage());
                 Assert.fail("Unable to verify checkbox"+ " Reason : "+ e.getMessage());
           }// End of Try_Catch statement
     } // End of Gen_IsSelected(String object, String data)
     
     /* 38 > Method to verify to dynamic text to web page are not matching*/
  	public static void Gen_verifyTextTwoDynamicMissmatching(String object, String data)throws Exception 
  	{
 	 	try 
 	 	{
 	 		App_Log.debug("Verifying test are not matching");
 			wait = new WebDriverWait(driver, waitTime);
 			wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
 	 		String text = driver.findElement(By.xpath(object)).getText().trim();
 	 		if(!(dynamicText.equals(text)))
 			{
 	 			TestRunner.test.log(Status.PASS, "Successfully verified.");
 			}else{
 				throw new Exception("Data are not matching");
 			} // End of if_else statement
 	 	}catch (Exception e){
 				TestRunner.App_Log.debug("Unable to verify text " + " Reason : "+  e.getMessage());
 				Assert.fail("Unable to verify text"+ " Reason : "+ e.getMessage());
 		}  // End of try_catch statement
  	} // End of Gen_verifyTextTwoDynamicMissmatching(String object, String data)
  	
  	/* 39 > Method to get value from text field*/
  	public static void Gen_verifyValue(String object, String data)
  	{
  		try 
  	 	{
  	 		App_Log.debug("Verifying Value"+object);
  			wait = new WebDriverWait(driver, waitTime);
  			wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
  			String text= driver.findElement(By.xpath(object)).getAttribute("value");
  	 		if(text.equals(data))
  	 		{
  	 			TestRunner.test.log(Status.PASS, "Successfully got value.");
  	 		}else{
               throw new Exception("Expected value:"+data+" -----     "+"Actual value:"+text);
             } // End of if_else statement
  	 	}catch (Exception e){
  				TestRunner.App_Log.debug("Unable to verify Value " + " Reason : "+  e.getMessage());
  				Assert.fail("Unable to verify Value"+ " Reason : "+ e.getMessage());
  		}// End of Try_Catch statement
  	} // End of Gen_verifyValue(String object, String data)
  	
  	/* 40 > To get text from input tag and return value*/
 	public static String Gen_getInputTextReturn(String object, String data) 
 	{
 		try 
 		{
 			App_Log.debug("Checking presence of element " + object);
 			wait = new WebDriverWait(driver, waitTime);
 			wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
 			dynamicText = driver.findElement(By.xpath(object)).getAttribute("value");
 			TestRunner.test.log(Status.PASS, "Successfully got value.");
             return dynamicText;
 		} catch (Exception e) {
 			Assert.fail("Unable to return Value"+ " Reason : "+ e.getMessage());
             return null;
 		}// End of Try_Catch statement
 	} // End of Gen_getInputTextReturn(String object, String data) 
 	/* 42 > To perform Scroll Left to right */
	public static void Gen_scrollLeftRight(String data)
	{
		try {
			App_Log.debug("Verifying scroll leftRigth");
			wait = new WebDriverWait(driver, waitTime);
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(1000,0)", driver.findElement(By.xpath(data)));
			TestRunner.test.log(Status.PASS, "Scrolling is successful");
		} catch (Exception e) {
			TestRunner.App_Log.debug("Could not scroll from left to right" + " Reason : "+  e.getMessage());
			Assert.fail("Could not scroll from left to right"+ " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_scrollLeftRight(String data)
	
	/* 43 > Dynamic locator creation using text and other attributes*/
	public static void Gen_dynamicLocatorSpecified(String object, String data,String dataOne,String dataTwo)
	{
		try {
			App_Log.debug("Checking presence of element " + object);
			String newData = "'" + data + "'";
			//replaceObjectInProperty(object, "(//"+dataTwo+"[text()=" + newData + "])["+dataOne+"]");
			//Constants.test1.log(LogStatus.PASS,DriverScript.sDescription);
			 driver.findElement(By.xpath("(//"+dataTwo+"[text()=" + newData + "])["+dataOne+"]"));
			TestRunner.test.log(Status.PASS, "Element displayed successfully.");
			App_Log.debug("Element displayed successfully.");
		} catch (Exception e) {
			App_Log.debug("Enter is not present --- " + " Reason : "+  e.getMessage());
			Assert.fail("Enter is not present --- " + " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_dynamicLocatorSpecified(String object, String data,String dataOne,String dataTwo)
	/* 58 > To wait for */
	public static void Gen_waitFor() throws Exception {
		try {
			App_Log.debug("Wait for 5 seconds");
			Thread.sleep(5000);
			//sWaitTime = Config_Main.getProperty("waitTime");  
			TestRunner.test.log(Status.PASS, "Wait Successfully.");
		} 	catch (Exception e) {
			TestRunner.App_Log.debug("Not able to Wait" + " Reason : "+  e.getMessage());
			Assert.fail("Wait not working"+ " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_waitFor()

	/* 59 > Explicit wait By visibility*/
	public static void Gen_webDriverWaitByVisibility(String object) throws Exception 
	{
		try {
			App_Log.debug("Waiting for element presence");
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOf((driver.findElement(By.xpath(object)))));
			TestRunner.test.log(Status.PASS, "Wait Successfully.");
		} catch (Exception e) {
			TestRunner.App_Log.debug("Explicit wait By visibility failed. " + " Reason : "+  e.getMessage

());
			Assert.fail("Explicit wait By visibility failed."+ " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_webDriverWaitByVisibility(String object)

	/* 60 > PageLoad wait */
	public static void Gen_pageLoadWait(long data) throws Exception 
	{
		try {
			App_Log.debug("Waiting for element presence");
			Thread.sleep(data);
			TestRunner.test.log(Status.PASS, "Waited Successfully.");
		} catch (Exception e) {
			TestRunner.App_Log.debug("Pageload timeout wait failed. " + " Reason : "+  e.getMessage());
			Assert.fail("Pageload timeout wait failed."+ " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	}// End of Gen_pageLoadWait(long data)

	/* 61 > Explicit wait By visibility*/
	public static void Gen_Explicitwait(String object) throws Exception 
	{
		try {
			App_Log.debug("Waiting for element presence");
			wait = new WebDriverWait(driver, waitTime);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(object)));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object)));
			TestRunner.test.log(Status.PASS, "Waited Successfully.");
		} catch (Exception e) {
			TestRunner.App_Log.debug("Explicit wait failed." +" Reason : "+   e.getMessage());
			Assert.fail("Explicit wait failed");
		}// End of Try_Catch statement
	} // End of Gen_Explicitwait(String object)

	/* 62 > PageLoad wait without thread*/
	public static void Gen_waitForPageLoad(String object, String data) throws Exception
	{
		try {
			App_Log.debug("Waiting for element presence");
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			TestRunner.test.log(Status.PASS, "Waited Successfully.");
		} catch (Exception e) {
			TestRunner.App_Log.debug("PageLoad wait without thread failed. " + " Reason : "+  e.getMessage

());
			Assert.fail("Pageload timeout without thread failed."+ " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_waitForPageLoad(String object, String data)

	/* 63 > Specified time wait */
	public static void Gen_wait(String object, String data) throws Exception 
	{
		try {
			App_Log.debug("Wait method called");
			long time= Integer.parseInt(data);
			Thread.sleep(time);
			TestRunner.test.log(Status.PASS, "Waited Successfully.");
		}catch (Exception e) {
			App_Log.debug("Specified time wait failed." + " Reason : "+  e.getMessage());
			Assert.fail("Specified time wait failed."+ " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_wait(String object, String data)

	 /* 64 > To perform thread wait  */
	public static void Gen_ImplicitWait(int value) 
	{
		try {
			int seconds = value * 1000;
			App_Log.debug("Waiting for seconds : "+seconds);
			Thread.sleep(seconds);
		} catch (Exception e) {
			App_Log.debug("Could not wait" + " Reason : "+  e.getMessage());
			Assert.fail("Could not wait"+ " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_ImplicitWait(String value)
	
	/* 65 > Verify End date */
	public static void Gen_VerifyStartDateLessThanEndDate(String startDate, String endDate)
	{
		try 
		{
			/* Verify With two dates */
			App_Log.debug("Verify Start Dates " + startDate+" and "+endDate);
			
			Date dateStartDate = new Date();
			Date dateEndDate = new Date();
			DateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
			//DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			
			dateStartDate = sdf.parse(startDate);
			dateEndDate = sdf.parse(endDate);
			
			App_Log.debug("Start Date => "+dateStartDate);
			App_Log.debug("End Date => "+dateEndDate);
			
			System.out.println("Start Date => "+dateStartDate);
			System.out.println("End Date => "+dateEndDate);
			
			if(dateStartDate.before(dateEndDate)) 
			{
				App_Log.debug("Start date is less than End Date");
			}else{
				App_Log.debug("Start date is NOT less than End Date");
				Assert.fail("Start date is NOT less than End Date");
			} // End of if_else statement
		} catch (Exception e) {
			App_Log.debug("Date comparison failed. " + " Reason : "+  e.getMessage());
			Assert.fail("Date comparison failed. " + " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_VerifyStartDateLessThanEndDate()
	
	/* 66 > Method to verify the actual result not matched with the expected result */
 	public static void Gen_verifyTextNotContains(String expected, String observed)throws Exception 
 	{
 		try 
	 		{
	 		App_Log.debug("Verifying test NOT matching for : "+expected);
	 		wait = new WebDriverWait(TestRunner.driver, waitTime);
			System.out.println("Expected Text : " +expected);
			System.out.println("Observed Text : " +observed);
			if(!observed.contains(expected))
			{
	 			TestRunner.test.log(Status.PASS, "Text Not observed, Test case passed.");
	 			//System.out.println("Text verified Successfully +++++++++++++++++ ");
			}else{
	 			System.out.println("-------------------- Text NOT verified -------------------- ");
				throw new Exception("Expected text:"+expected+" -----     "+"Actual text:"+observed);
			} // End of if_else statement
	 	}catch (Exception e)
	 		{
	 		TestRunner.App_Log.debug("Expected Text : "+expected+ " Observed Text : "+observed);
			TestRunner.App_Log.debug("Not able to verify --- " + " Reason : "+  e.getMessage());
			Assert.fail("Unable to verify Text Expected Text : "+expected+ " Observed Text : "+observed+ " Reason : "+ e.getMessage());
		} // End of try Catch statement
 	} // End of Gen_verifyTextNotContains(String expected, String observed)
	/* 52 > Verify sorting order*/
	public static void Gen_sortingOrder(String object, String dashletName, String dataType, int col, int valueAt, 

String sortType)
	{
		try {
			App_Log.debug("Verifing sorting order");
	
			String dynXpath;
			//System.out.println("Here..");
			List<WebElement> totalDiv = driver.findElements(By.xpath("//h2[@data-title='"+dashletName

+"']/../report//div[@class='abc']//div[@class='table-cell-outer-div bodyText']//span[@class='cell-value-text-span']"));
			int sizeTotal = totalDiv.size();
			int size = sizeTotal/col;
			App_Log.debug("Total Rows are : "+size);
			
			ArrayList<String> obtainedListString = new ArrayList<String>(); 
			ArrayList<Integer> obtainedListInt = new ArrayList<Integer>();

			ArrayList<String> sortedListString = new ArrayList<String>();
			ArrayList<Integer> sortedListInt = new ArrayList<Integer>();
				
			for(int i=col;i<=sizeTotal;)
			{
				dynXpath= "(//h2[@data-title='"+dashletName+"']/../report//div[@class='abc']//div[@class='table-cell-outer-div bodyText']//span[@class='cell-value-text-span'])["+valueAt+"]";
				if(dataType.equalsIgnoreCase("Integer"))
				{
					String str = driver.findElement(By.xpath(dynXpath)).getText();
					if(str.contains(","))
					{
						str = str.replaceAll(",", "");
					}
					int e = Integer.parseInt(str);
					obtainedListInt.add(e);
				}else{
					obtainedListString.add(driver.findElement(By.xpath(dynXpath)).getText());
				} // End of if_else statement
				i = i +col;
				valueAt = valueAt + col;
			} // End of for loop
			
			if(dataType.equalsIgnoreCase("String"))
			{
				for(String s:obtainedListString)
				{
					sortedListString.add(s);
				}
				Collections.sort(sortedListString);
				if(sortType.equalsIgnoreCase("DSC"))
				{
					Collections.reverse(sortedListString);
				}
				Assert.assertTrue(sortedListString.equals(obtainedListString));
			} // End of if statement
			
			if(dataType.equalsIgnoreCase("Integer"))
			{
				for(int s:obtainedListInt)
				{
					sortedListInt.add(s);
				}
				Collections.sort(sortedListInt);
				if(sortType.equalsIgnoreCase("DSC"))
				{
					Collections.reverse(sortedListInt);
				}
				Assert.assertTrue(sortedListInt.equals(obtainedListInt));
			} // End of if statement
			
			TestRunner.test.log(Status.PASS, "Sorting order validated.");
			App_Log.debug("Sorting order validated.");
		} catch (Exception e) {
			App_Log.debug("Sorting order NOT validated.- " + " Reason : "+  e.getMessage());
			Assert.fail("Sorting order NOT validated. " + " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_sortingInDescendingOrder()

	/* 53 > Method to verify the actual result with the expected result */
 	public static void Gen_compareText(String object, String expected, String observed)throws Exception 
 	{
 	 	try 
	 	{
	 		App_Log.debug("Verifying test matching for : "+object);
	 		System.out.println("\nVerifying test matching for : "+object);
	 		
	 		wait = new WebDriverWait(TestRunner.driver, waitTime);
	 		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(observed)));
	 		observed = driver.findElement(By.xpath(observed)).getText().trim();
			
			if(observed.contains(expected))
			{
	 			TestRunner.test.log(Status.PASS, "Text verified Successfully.");
	 			System.out.println("Text verified Successfully.");
			}else{
				TestRunner.App_Log.debug("Unable to verify Text for "+object +" \nExpected Text : "+expected+ " Observed Text : "+observed);
				System.out.println("Unable to verify Text for "+object +" \nExpected Text : "+expected+ " \nObserved Text : "+observed);
				Assert.fail("Unable to verify Text for "+object +" \nExpected Text : "+expected+ " \nObserved Text : "+observed);	
				} // End of if else statement
	 	}catch (Exception e){
	 		TestRunner.App_Log.debug("Not able to verify "+object +" reason " + e.getMessage());
			Assert.fail("Not able to verify "+object +" reason " + e.getMessage());
		} // End of try catch statement
 	} // End of Gen_compareText()

 	/* 54 > Method to check Multiple Projects are displayed or not*/
 	public static void Gen_verifyMultipleProjectDisplay(int value)
 	{
 		try 
 	 	{
 	 		App_Log.debug("Verifying Value"+value);
 			wait = new WebDriverWait(driver, waitTime);
	 	 		if(value>1)
	 	 		{
	 	 			TestRunner.test.log(Status.PASS, "Multiple Projects are displayed in Dashboard.");
	 	 		}else{
	                throw new Exception("Multiple Projects are  not prsesent in Dashboard.");
	            } // End of if_else statement
 	 	}catch (Exception e){
			TestRunner.App_Log.debug("Not able check Multiple Projects are displayed " + " Reason : "+e.getMessage());
			Assert.fail("Unable to verify Multiple Projects are displaye"+ " Reason : "+ e.getMessage());
 		} // End of try catch statement
 	} // End of Gen_verifyMultipleProjectDisplay(int value)
 	
	/* 55 > Dynamic locator creation using text and other attributes*/
    public static void Gen_dynamicLocatorSpecified(String object, String newData) 
    {
          try {
                App_Log.debug("Checking presence of element " + newData);
                
                replaceObjectInProperty(object, "(//*[text()='" + newData + "'])[1]");
                //Constants.test1.log(LogStatus.PASS,DriverScript.sDescription);
                //driver.findElement(By.xpath("(//*[text()=" + newData + "])[1]")).isDisplayed();
                TestRunner.test.log(Status.PASS, "Element displayed successfully.");
                App_Log.debug("Element displayed successfully.");
          } catch (Exception e) {
                App_Log.debug("Enter is not present --- " + " Reason : "+  e.getMessage());
                Assert.fail("Enter is not present --- " + " Reason : "+ e.getMessage());
          } // End of try catch statement
    } // End of Gen_dynamicLocatorSpecified(String object, String newData) 
    
    /* 56 > To replace locator value in run time property with new value */
    public static void replaceObjectInProperty(String logicalName, String newValue) throws Exception
    {
          try 
          {
	          TestRunner.OR_FILE.setProperty(logicalName, newValue);
	          System.out.println(TestRunner.OR_FILE.getProperty(logicalName));
          }catch (Exception e){
	          TestRunner.App_Log.debug("Unable to update xpath " + " Reason : "+e.getMessage());
	          Assert.fail("Unable to update xpath");
          }// End of Try_Catch statement
    } // End of replaceObjectInProperty(String logicalName, String newValue)
 
    /* 57 > Explicit wait By presence*/
	public static void Gen_webDriverWaitByPresence(String object) throws Exception 
	{
		try {
			App_Log.debug("Waiting for element presence");
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(object)));
			TestRunner.test.log(Status.PASS, "Explicit wait called");
		} catch (Exception e) {
			TestRunner.App_Log.debug("Wait by presence failed" + e.getMessage());
			Assert.fail("Wait by presence failed."+ " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_webDriverWaitByPresence(String object)
    
	/* 57 > Explicit wait By till Element Is Invisible*/
	public static void Gen_webDriverWait_tillElementIsInvisible(String data, String value) throws Exception 
	{
		try {
			App_Log.debug("Waiting for invisibility of element present");
			wait = new WebDriverWait(TestRunner.driver, TestRunner.waitTime);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(value)));
			TestRunner.test.log(Status.PASS, "Wait for loader invisibility");
			//System.out.println("=========> Wait for loader invisibility ");
		} catch (Exception e) {
			TestRunner.App_Log.debug("loader invisibility verification failed." + " Reason : "+  

e.getMessage());
			Assert.fail("loader invisibility verification failed."+ " Reason : "+ e.getMessage());
		} // End of try catch statement
	} // End of Gen_webDriverWait_TillElementIsInvisible(String object)
	
	/* 44 > Executing query */
	public static String Gen_ExecuteQuery(String object, String query, String columnName, int expected)
	{
		String queryResult = null;
		Integer expectedInt=null;
		Integer ObservedInt=null;
		try {
			App_Log.debug("\nExecuting Query for " + object);
		
			//Load MySQL JDBC Driver
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			
			//App_Log.debug("URL ===>  "+TestRunner.Config_Main.getProperty("DBUrl"));
			//App_Log.debug("USERNAME ===>  "+TestRunner.Config_Main.getProperty("DBUsername"));
			//App_Log.debug("PASSWORD ===>  "+TestRunner.Config_Main.getProperty("DBPassword"));
			
			connection = DriverManager.getConnection(TestRunner.Config_Main.getProperty("DBUrl"), 

TestRunner.Config_Main.getProperty("DBUsername"), TestRunner.Config_Main.getProperty("DBPassword"));
			
			Statement st = connection.createStatement();
			App_Log.debug("Connection established");

			System.out.println("\n ------------- Query "+object+"------------- \n "+query);
			//System.out.println("\n ------------- Query "+object+"------------- \n ");
			startTime = System.currentTimeMillis();

			String selectquery = query;
					
			//Executing the SQL Query and store the results in ResultSet
			ResultSet rs = st.executeQuery(selectquery);
			//While loop to iterate through all data and print results
			App_Log.debug("Query is executing . . . ");


			while (rs.next()) {
				//App_Log.debug(rs.getString("name"));
				App_Log.debug(" ==> "+rs.getString(columnName));
				queryResult = rs.getString(columnName);
			}
			
			endTime = System.currentTimeMillis();
		    totalTime = (endTime - startTime);
		    //System.out.println("Query execution time in miliSeconds : "+totalTime);
		    
		    totalTime = totalTime/1000;
		    System.out.println("Query execution time in Seconds : "+totalTime);
		    
		    totalTime = totalTime/60;
		    System.out.println("Query execution time in Minutes : "+totalTime);
			
			//queryResult ="92.6";
			if(queryResult.contains(".")){
				String[] result = queryResult.split("\\.");
				queryResult = result[0];
			}
			//expectedInt = Integer.parseInt(expected);
			ObservedInt = Integer.parseInt(queryResult);
			
			App_Log.debug("expected "+object+" at dashboard : "+expected);
			App_Log.debug("Observed "+object+" at database : "+ObservedInt);
			System.out.println("expected "+object+" at dashboard : "+expected);
			System.out.println("Observed "+object+" at database : "+ObservedInt);
			
			int difference=0;
			if(expected>ObservedInt)
			{
				difference = expected-ObservedInt;
				System.out.println("Difference is => "+difference);
			}else{
				difference = ObservedInt-expected;
				System.out.println("Difference is => "+difference);
			}
			
			//if(expected==ObservedInt)
			if(difference<=20)
			{
				App_Log.debug("Values validated in Database for : "+object);
				//TestRunner.test.log(Status.INFO, "Values validated in Database for : "+object);
				TestRunner.test.log(Status.INFO, "Values validated in Database for : "+object);
			}else{
				App_Log.debug("Values are NOT validated in Database for : "+object);
				TestRunner.test.log(Status.ERROR, "Values are NOT validated in Database for : "+object);
				
				if(expected>ObservedInt){
				App_Log.debug("Dashboard value is greater by "+(expected-ObservedInt)+" than database value");
				TestRunner.test.log(Status.INFO, "Dashboard value is greater by "+(expected-

ObservedInt)+" than database value");
				}else{
				App_Log.debug("Database value is greater by "+(ObservedInt-expected)+" than dashboard value");
				TestRunner.test.log(Status.INFO, "Database value is greater by "+(ObservedInt-expected)+" than dashboard value");
				} // End of inner if_else
				System.out.println("Results not matched Expected "+object+" at dashboard : "+expected+ "\n Observed "+object+" at database : "+ObservedInt);
				
				//SoftAssert s= new SoftAssert();
				//s.fail("Results not matched Expected "+object+" at dashboard : "+expected+ "\n Observed "+object+" at database : "+ObservedInt);
				Assert.fail("Results not matched Expected "+object+" at dashboard : "+expected+ "\n Observed "+object+" at database : "+ObservedInt);
				test.log(Status.FAIL, "Results not matched Expected "+object+" at dashboard : "+expected+ "\n Observed "+object+" at database : "+ObservedInt);
			} // End of outer if_else
			
			//Closing DB Connection
			connection.close();
			TestRunner.test.log(Status.PASS, "Query executed successfully.");
			App_Log.debug("Query executed successfully.");
		} catch (Exception e) {
			App_Log.debug("Query NOT executed." + " Reason : "+ e.getMessage());
			System.out.println("Query NOT executed.");
			TestRunner.test.log(Status.ERROR, "Query NOT executed");
			Assert.fail("Query NOT executed." + " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
		return queryResult;
	} // End of Gen_ExecuteQuery()
	
	/* 45 > Dynamic locator creation using text and other attributes*/
	public static void Gen_VerifyStartDate(String expectedStartDate, String observedStartDate)
	{
		try {
		/* Verify With two dates */
		App_Log.debug("Verify Start Dates " + expectedStartDate+" and "+observedStartDate);
		Date expectedDate = new Date();
		Date observedDate = new Date();
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		expectedDate = sdf.parse(expectedStartDate);
		observedDate = sdf.parse(observedStartDate);
		
		App_Log.debug("Expected Date => "+expectedDate);
		App_Log.debug("Observed Date => "+observedDate);
		
			if(expectedDate.after(observedDate)||expectedDate.equals(observedDate))
			{
				App_Log.debug("Start date is greater.");
			}else{
				App_Log.debug("Start date is less.");
				Assert.fail("Start date is less.");
			} // End of if_else statement
		} catch (Exception e) {
			App_Log.debug("Date comparison failed. " + " Reason : "+ e.getMessage());
			Assert.fail("Date comparison failed. " + " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_VerifyStartDate(String expectedStartDate, String observedStartDate)
	
	/* 46 > Verify End date */
	public static void Gen_VerifyEndDate(String expectedEndDate, String observedEndDate)
	{
		try 
		{
			/* Verify With two dates */
			App_Log.debug("Verify Start Dates " + expectedEndDate+" and "+observedEndDate);
			
			Date expectedDate = new Date();
			Date observedDate = new Date();
			DateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
	
			expectedDate = sdf.parse(expectedEndDate);
			observedDate = sdf.parse(observedEndDate);
			
			App_Log.debug("Expected Date => "+expectedEndDate);
			App_Log.debug("Observed Date => "+observedEndDate);
			
			if(expectedDate.before(observedDate)) 
			{
				App_Log.debug("Observed End date is lesser than expected End date");
			}else{
				App_Log.debug("Observed End date is greater than expected End date");
				Assert.fail("Observed End date is greater than expected End date");
			} // End of if_else statement
		} catch (Exception e) {
			App_Log.debug("Date comparison failed. " + " Reason : "+  e.getMessage());
			Assert.fail("Date comparison failed. " + " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_VerifyEndDate()
	
	/* 47 > Executing SQL query for Percentage calculation */
	public static String Gen_ExecuteQuery_ForPercentage(String object, String query, String columnName, float 

expectedFloat)
	{
		String queryResult = null;
		Float ObservedFloat=null;
		try {
			App_Log.debug("\nExecuting Query for " + object);
			
			//Load MySQL JDBC Driver
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(TestRunner.Config_Main.getProperty("DBUrl"), 

TestRunner.Config_Main.getProperty("DBUsername"), TestRunner.Config_Main.getProperty("DBPassword"));
			Statement st = connection.createStatement();
			System.out.println("\n ------------- Query "+object+"------------- \n "+query);
			//System.out.println("\n ------------- Query "+object+"------------- \n ");
			
			startTime = System.currentTimeMillis();
			String selectquery = query;
			ResultSet rs = st.executeQuery(selectquery);
			//While loop to iterate through all data and print results
			
			while (rs.next()) {
				queryResult = rs.getString(columnName);
			}
			
			endTime = System.currentTimeMillis();
		    totalTime = (endTime - startTime);
		    //System.out.println("Query execution time in miliSeconds : "+totalTime);
		    
		    totalTime = totalTime/1000;
		    System.out.println("Query execution time in Seconds : "+totalTime);
		    
		    totalTime = totalTime/60;
		    System.out.println("Query execution time in Minutes : "+totalTime);
		    
			ObservedFloat = Float.valueOf(queryResult);
			
			App_Log.debug("Expected value at dashboard for "+object+" : "+expectedFloat);
			App_Log.debug("Observed value at database for "+object+" : "+ObservedFloat);
			System.out.println("Expected value at dashboard for "+object+" : "+expectedFloat);
			System.out.println("Observed value at database for "+object+" : "+ObservedFloat);
			
			float difference = 0;
			if(expectedFloat>ObservedFloat)
			{
				difference = expectedFloat-ObservedFloat;
				System.out.println("Difference => "+difference);
			}else{
				difference = ObservedFloat-expectedFloat;
				System.out.println("Difference => "+difference);
			}
				
			//if(expectedFloat==ObservedFloat)
			if(difference<=5)
			{
				App_Log.debug("Values validated in Database for : "+object);
			}else{
				App_Log.debug("Values are NOT validated in Database for : "+object);
				
				if(expectedFloat>ObservedFloat){
					App_Log.debug("Dashboard value is greater by "+(expectedFloat-ObservedFloat)+" than database value");
				}else{
					App_Log.debug("Database value is greater by "+(ObservedFloat-expectedFloat)+" than dashboard value");
				} // End of inner if_else
				System.out.println("Results not matched Expected "+object+" at dashboard : "+expectedFloat+ "\n Observed "+object+" at database : "+ObservedFloat);
				Assert.fail("Results not matched Expected "+object+" at dashboard : "+expectedFloat+ 

"Observed "+object+" at database : ");
			} // End of outer if_else
			
			//Closing DB Connection
			connection.close();
			TestRunner.test.log(Status.PASS, "Query executed successfully.");
			App_Log.debug("Query executed successfully.");
		} catch (Exception e) {
			App_Log.debug("Results not matched Expected "+object+" at dashboard : "+expectedFloat+ "\n Observed "+object+" at database : "+ObservedFloat+ e.getMessage());
			//System.out.println("Query NOT executed.");
			System.out.println("Results not matched Expected "+object+" at dashboard : "+expectedFloat+ " \n Observed "+object+" at database : "+ObservedFloat+ e.getMessage());
			Assert.fail("Results not matched Expected "+object+" at dashboard : "+expectedFloat+ "\n Observed "+object+" at database : "+ObservedFloat+ e.getMessage());
		}// End of Try_Catch statement
		return queryResult;
	} // End of Gen_ExecuteQuery_ForPercentage()

	/* 48 >  Compare float values */
	public static void Gen_CompareFloatValue(String object, float expectedFloat, float ObservedFloat) 
	{
		try {
			App_Log.debug("Comparing float values of "+object);
			if(expectedFloat==ObservedFloat)
			{
				App_Log.debug("Float Values validated");
			}else{
				App_Log.debug("Float Values are NOT validated");
				if(expectedFloat>ObservedFloat)
				{
					App_Log.debug("Expected float value is greater by "+(expectedFloat-

ObservedFloat)+" than Observed value");
				}else{
					App_Log.debug("Observed float value is greater by "+(ObservedFloat-

expectedFloat)+" than Expected value");
				} // End of inner if_else
			} // End of outer if_else
		} catch (Exception e){
			TestRunner.App_Log.debug("Float Values are not matching" + " Reason : "+  e.getMessage());
		}// End of Try_Catch statement
	} // End of Gen_CompareFloatValue(String object, float expectedFloat, float ObservedFloat)
	
	/* 49 > To get all web elements text into list  */
	public static int Gen_TextOfAllElements(String data, String value)
	{
		List<WebElement> textValue;
		//List<String> values;
		int size=0;
		try {
			TestRunner.App_Log.debug("Collecting all text elements of "+data);
			
			//System.out.println("======> "+value);
			textValue = TestRunner.driver.findElements(By.xpath(value));
			size = textValue.size();
			 for ( WebElement we: textValue) 
			 { 
				 //values.add(we.getText());
				 //App_Log.debug("===> "+we.getText());
			 }
			Thread.sleep(3000);
			TestRunner.test.log(Status.PASS, "Collected text of All element for : "+data);
		} catch (Exception e) {
			TestRunner.App_Log.debug("Not able to get all text element --- " + " Reason : "+  e.getMessage

());
			Assert.fail("Not able to get all text element from : "+data+ " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
		return size;
	} // End of Gen_TextOfAllElements(String data)
	
	/* 50 > Dynamic locator creation using text and other attributes */
	public static List<String> Gen_ExecuteQuery_ForMultipleRecords(String object, String query, String columnName)
	{
		String queryResult = null;
		List<String> dbValues=null;
		Integer expectedInt;
		try {
			App_Log.debug("\nExecuting Query for " + object);
		
			//Load MySQL JDBC Driver
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(TestRunner.Config_Main.getProperty("DBUrl"), 

TestRunner.Config_Main.getProperty("DBUsername"), TestRunner.Config_Main.getProperty("DBPassword"));
			
			Statement st = connection.createStatement();
			//App_Log.debug("Connection established");
			
			String selectquery = query;
					
			//Executing the SQL Query and store the results in ResultSet
			ResultSet rs = st.executeQuery(selectquery);
			
			while (rs.next()) {
				queryResult = rs.getString(columnName);
			}
			
			//Closing DB Connection
			connection.close();
			TestRunner.test.log(Status.PASS, "Query executed successfully.");
			App_Log.debug("Query executed successfully.");
		} catch (Exception e) {
			App_Log.debug("Query NOT executed successfully." + " Reason : "+  e.getMessage());
			Assert.fail("Query NOT executed successfully." + " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
		return dbValues;
	} // End of Gen_ExecuteQuery_ForMultipleRecords()
	
	/* 51 > Executing SQL query and get the string value from column */
	public static String Gen_ExecuteQuery_GetString(String object, String query, String columnName)
	{
		String queryResult = null;
		try {
			App_Log.debug("\nExecuting Query for " + object);
			
			//Load MySQL JDBC Driver
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			//System.out.println("\n ------------- Query "+object+"------------- \n ");
			System.out.println("\n ------------- Query "+object+"------------- \n "+query);
			connection = DriverManager.getConnection(TestRunner.Config_Main.getProperty("DBUrl"), 

TestRunner.Config_Main.getProperty("DBUsername"), TestRunner.Config_Main.getProperty("DBPassword"));
			Statement st = connection.createStatement();
			String selectquery = query;
			//startTime = System.currentTimeMillis();
			ResultSet rs = st.executeQuery(selectquery);
			while (rs.next()) {
				queryResult = rs.getString(columnName);
				//System.out.println("===> "+queryResult);
			}
			//endTime = System.currentTimeMillis();
		    //totalTime = (endTime - startTime)/1000;
		    //System.out.println("Query execution time : "+totalTime);
		
			//Closing DB Connection
			connection.close();
			TestRunner.test.log(Status.PASS, "Query executed successfully.");
			App_Log.debug("Query executed successfully.");
		} catch (Exception e) {
			App_Log.debug("Query NOT executed successfully." + " Reason : "+  e.getMessage());
			Assert.fail("Query NOT executed successfully." + " Reason : "+ e.getMessage());
		}// End of Try_Catch statement
		return queryResult;
	} // End of Gen_ExecuteQuery_ForPercentage()
	

		
}

