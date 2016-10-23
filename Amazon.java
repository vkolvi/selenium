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

public class Quikr {

	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "D:\\selenium-server-2.53.1\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.amazon.in");
		driver.findElement(By.id("nav-link-yourAccount")).click();
		driver.findElement(By.name("email")).sendKeys("vkolvi@gmail.com");
		driver.findElement(By.name("password")).sendKeys("varunk@123");
		driver.findElement(By.id("signInSubmit")).click();
		
		//D:\Datadriven.xls
		
		WebElement username = driver.findElement(By.name("email"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement Signin = driver.findElement(By.id("signInSubmit"));
		try {
		    
			  FileInputStream file = new FileInputStream(new File("D:\\Datadriven.xls")); 
			  HSSFWorkbook workbook = new HSSFWorkbook(file);
			  HSSFSheet sheet = workbook.getSheetAt(0);
		
	for (int i=1; i <= sheet.getLastRowNum(); i++)
	
	{
 
        String un = sheet.getRow(i).getCell(0).getStringCellValue();
        String pw = sheet.getRow(i).getCell(1).getStringCellValue();
        
        username.sendKeys(un);
        password.sendKeys(pw);
        Signin.submit();

////////////// VALIDATE FOR INVALID LOGIN
        try
        {	
        WebElement invalid = driver.findElement(By.className("a-box-inner a-alert-container")).getText();
        											
        String text = invalid.getText();
        if(text.equals("There was a problem"))
        {
        	System.out.println("----------Signin successfull with user: " +un);
        }
        	else 
        	{
        		System.out.println("----------Signwasn't successful with user: " +un);
        	}
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        }
        catch(Throwable e)
        {
	System.out.println("Invalid credentials: "+e.getMessage());
        }

	}
	 workbook.close();
	  file.close();
	 
	 } 
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
	if (str.equals("iphone"))
	{
		System.out.println(" appropriate products disaplyed in the results for " +str);
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
WebElement selectitem = driver.findElement(By.className("a-size-base a-color-null s-inline  s-access-title  a-text-normal"));

try 
{
	
String item = selectitem.getText();
if(item.equals("selecteitem"))
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
}
}