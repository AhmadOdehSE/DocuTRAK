package dataConf;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestSetUp 
{
   //Define the webdriver object
   public WebDriver driver ;
   
   //Define objects and strings for reading files
   public FileInputStream Reader ;
   public Properties pro = new Properties() ;
   
   //Define the test data
   public static String Dt_Path ;
   public static String User_Name ;
   public static String Password ;
	   
   //This method is to setup the data
   @Test(description = "This function is to read all data from Data.properties file")
   public void GettData() 
   {
	
	  try {
		  //Read and load the propereties file
		  Reader = new FileInputStream (System.getProperty("user.dir") + "\\Data.properties");
		  pro.load(Reader);
		
	} catch (Exception e) {
		System.out.println("The file has not been read correctly");
	} 
    
	  //Retrieve data to the current string we have
	  Dt_Path = pro.getProperty("Dt_Path") ;
	  User_Name = pro.getProperty("User_Name") ;
	  Password = pro.getProperty("Password") ;
   }
 
   //This method is to open Chrome browser and navigate to DT
   @Test(description = "Start the Chrome browser and navigate to DT", dependsOnMethods = "GettData")
   public void StartChromeBrowser()
   {
		  WebDriverManager.chromedriver().setup(); 
		  driver = new ChromeDriver() ;
		  driver.manage().window().maximize();
		  driver.get(Dt_Path);
   }
 
   //This method is to open IE browser and navigate to DT
   @Test(description = "Start the IE browser and navigate to DT", dependsOnMethods = "GettData")
   public void StartIEBrowser()
   {
	      WebDriverManager.iedriver().setup();
		  driver = new InternetExplorerDriver() ;
		  driver.manage().window().maximize();
		  driver.get(Dt_Path);

   }

}
