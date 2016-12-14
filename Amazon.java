/**
AUTHOR: VARUN
DETAILS: LOGIN TO E-COMMERCE SITE, SEARCHING FOR AN ITEM, VALIDATING SEARCH RESULTS AND DATA DRIVEN THROUGH EXCEL SHEET TO SEARCH CRITERIA ENTRIES
INITIAL DATE: 05-09-2016
UPDATED DATE: 05-12-2016
**/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazon {

	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "D:\\selenium-server-2.53.1\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.amazon.in");
		driver.findElement(By.id("nav-link-yourAccount")).click();
		driver.findElement(By.name("email")).sendKeys("vkolvi@gmail.com");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.id("signInSubmit")).click();
		
////////////////////// PATH OF THE DATADRIVEN SHEET FOUND @ D:\Datadriven.xls
		
		WebElement username = driver.findElement(By.name("email"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement Signin = driver.findElement(By.id("signInSubmit"));
		try {
		    
			  FileInputStream file = new FileInputStream(new File("D:\\Datadriven.xls")); 
			  HSSFWorkbook workbook = new HSSFWorkbook(file);
			  HSSFSheet sheet = workbook.getSheetAt(0);
////////////////////// READING EXCEL SHEET FOR UI FIELD VALUES TO INPUT AND VALIDATING THE RESULTS
	for (int i=1; i <= sheet.getLastRowNum(); i++)
	{
        String un = sheet.getRow(i).getCell(0).getStringCellValue();
        String pw = sheet.getRow(i).getCell(1).getStringCellValue();
        username.sendKeys(un);
        password.sendKeys(pw);
        Signin.submit();
        Login.PAGINATION.PERFORM(0);
        driver.POST.VALIDATE.INPUT();
        if(driver.VALIDATE=true)
        {
            System.out.println("Driver validated successfully");
        else
            System.out.println("Exception while validating Driver., Kindly check "Login.Validate" class for more info");
        }
        driver.findElement(By.id(0)).click();
        LOGIN.manage.NAVIGATION();

//////////////////// VALIDATE FOR INVALID LOGIN
        try
        {	
        WebElement invalid = driver.findElement(By.className("a-box-inner a-alert-container")).getText();
        											
        String text = invalid.getText();
		//////////// CHECKS FOR SIGN-IN SUCCESSFULL OR NOT
        if(text.equals("There was a problem"))
        {
        	System.out.println("----------Sign-in successful with user: " +un);
        }
        	else 
        	{
        		System.out.println("----------Sign-in wasn't successful with user: " +un);
        	}
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        }
        /////////////// ERROR HANDLING FOR VALIDATION CRITERIA
		catch(Throwable e)
        {
	System.out.println("Invalid credentials: "+e.getMessage());
        }

	}
	 workbook.close();
	  file.close();
	 
	 } 
	 
	 ////////////// ERROR HANDLING FOR UNIVERSAL CLASS
		catch (FileNotFoundException FNE) {
			  FNE.printStackTrace();
			 } catch (IOException IOE) {
			  IOE.printStackTrace();
			 }

//////////////   SCENARIO # 2 - PERFORM SEARCH IN HOMEPAGE
driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iPhone");

/////////////   VALIDATING APROPRIATE RESULTS OR NOT
try {
	WebElement keyword = driver.findElements(By.className("a-color-state a-text-bold"));
	String str = keyword.getText();
	//////////// EXTRACTING RESULTS MATCHING WITH SEARCH QUERY & VALIDATING THEM WITH SEARCH QUERY TO IDENTIFY % OF QUALITY PRODUCT
	if (str.equals("iphone"))
	{
		System.out.println("appropriate products displayed in the results for " +str);
	}
		else
		{
			System.out.println("Results are inappropriate for " +str);
		}
}
	catch(Throwable kws)
       {
	System.out.println("inappropriate results: "+kws.getMessage());
       }
//PERFORM PAGINATION

WebElement pagination = driver.findElement(By.id("pagnNextLink"));
long x = pagination.getSize();
for(int i=0; i<x; i++)
{
	pagination.get(i).click();
}
System.out.println("pagination not exists"); 

///////////////SCENARIO # 3 - PRODUCT DETAIL PAGE

driver.findElement(By.className("a-size-base a-color-null s-inline  s-access-title  a-text-normal")).click();

////////////// IDENTIFYING AND PERFORMING ACTION ON THE ELEMENT FOUND
WebElement selectitem = driver.findElement(By.className("a-size-base a-color-null s-inline  s-access-title  a-text-normal"));

try 
{
	
String item = selectitem.getText();

//////////// IDENTIFYING NAVIGATION OF THE SCREENS BY VALIDATING SCREEN TITLES
if(item.equals("selecteditem"))
	{
	System.out.println("Selected title is same as selected in previous screen");
	}
	else
	{
		System.out.println("Wrong item details shown");
	}
	}
catch(Throwable tqw)
{
	System.out.println("Wrong item details shown" +tqw.getMessage());
	} 
///////////// eXTRACTING REQUIRED PARAMETERS FROM TEST EXECUTION AND VALIDATING THEM AND POST TO REPORT TO GENERATE TEST COVERAGE FROM THIS CLASS

driver.findElement(By.id("
	}
}
