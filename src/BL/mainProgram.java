package BL;

import java.awt.JobAttributes.DestinationType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class mainProgram {
	static String origin = "";
	static String destination = "";
	
	public void userInput(){
		Scanner sc=new Scanner(System.in);  
		
		 System.out.println("Enter your origin: ");  
		 origin=sc.next();  
		 
		 System.out.println("Enter your Destination: ");  
		 destination=sc.next();  
		 
		 System.out.println("Origin entered: " + origin);
		 System.out.println("Destination entered: " + destination);
		 
		 sc.close();
		
		
	}
	public void openBrowsergetLowRes(String website){
		ArrayList<Integer> list = new ArrayList<Integer>();
	
		 WebDriver driver=new ChromeDriver();
		 
		 driver.manage().window().maximize();
		 driver.get(website);
		 Actions actions = new Actions(driver);
		
		 
		 try{

		
		WebElement originTexbox = driver.findElement(By.xpath("//*[@id='root']/div[3]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div/div/div[1]/div[5]/table/tbody/tr[1]/td[1]/div/div[1]/div"));
		
		actions.moveToElement(originTexbox);
		actions.click();
		actions.sendKeys(origin);
		actions.build().perform();
		
		}
		catch (Exception e){
			System.out.println("cannot find origin textbox");
			System.out.println(e);
		}
		try{
		WebElement destinationTextbox = driver.findElement(By.xpath("//*[@id='root']/div[3]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div/div/div[1]/div[5]/table/tbody/tr[1]/td[2]/div"));
		actions.moveToElement(destinationTextbox);
		actions.click();
		actions.sendKeys(destination);
		actions.build().perform();
		
//		destinationTextbox.sendKeys(Keys.RETURN);
		}
		catch(Exception e){
			System.out.println("cannot find destination textbox");
			System.out.println(e);
		}
		 
		
		 driver.findElement(By.xpath("//*[@id='root']/div[3]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/div/div/div[1]/div[5]/table/tbody/tr[2]/td[1]/div/div[1]/div[2]/input")).click();
		 ///html/body/div[3]/div/div/table/tbody/tr[2]/td/div/table[1]
		 //WebElement table_element = driver.findElements(By.xpath("/html/body/div[3]/div/div/table/tbody/tr[2]/td/div/table[1]/html/body/div[3]/div/div/table/tbody/tr[2]/td/div/table[1]"));
	    // System.out.println(driver.findElements(By.xpath("/html/body/div[3]/div/div/table/tbody/tr[2]/td/div/table[1]/tbody/tr[2]")).size());
		 
	     for(int i =0; i<=6; i++){
	    	 
	 
	     try{
	     Thread.sleep(3000);
	    
	     //month
	     ///html/body/div[3]/div/div/table/tbody/tr[1]/td/div/div[3]
	     String month = driver.findElement(By.xpath("/html/body/div[3]/div/div/table/tbody/tr[1]/td/div/div[3]")).getText().toString();

	     WebElement table = driver.findElement(By.xpath("/html/body/div[3]/div/div/table/tbody/tr[2]/td/div/table[1]"));
	     //System.out.println(driver.findElement(By.xpath("/html/body/div[3]/div/div/table/tbody/tr[2]/td/div/table[1]")).getText());
	     
	     
	     List<WebElement> allRows = table.findElements(By.tagName("tr")); 
	    
///html/body/div[3]/div/div/table/tbody/tr[2]/td/div/table[1]/tbody/tr[5]/td[4]/div[2]
	  // And iterate over them, getting the cells 
	  for (WebElement row : allRows) { 
		
	      List<WebElement> cells = row.findElements(By.tagName("td")); 
	      
            
	      // Print the contents of each cell
	      for (WebElement cell : cells) { 
	    	
	    	  if(cell.getText().toString().replaceAll("\\s+","").length()>=5){
	    		  
//	    	  System.out.println(cell.getCssValue("color"));
	          System.out.println(month + " " + cell.getText().toString().replaceAll("\\s+",""));
	          String amount = cell.getText().toString().replaceAll("\\s+","");
	         String amountTrim = amount.substring(amount.lastIndexOf('$') + 1);
	         Integer result = Integer.valueOf(amountTrim);
	         list.add(result);
	         //System.out.println(result);
	    	  }
	    	  }
	      
	   

	  }
	     
	     
	     
	     }
	     catch(Exception e)
	     {
	    	 System.out.println("no calendar found " + e);
	     }
	     
	     //clicks next month arrow
		 driver.findElement(By.xpath("/html/body/div[3]/div/div/table/tbody/tr[1]/td/div/div[2]")).click();
		
	     
		
	     }
		 
	      System.out.println("Minimum Ticket Price from " + origin + "to " + destination + " " +  "$" + Collections.min(list));
		 driver.quit();
		
		
	}

	public static void main(String[] args) 
	{
		mainProgram obj = new mainProgram();
		obj.userInput();
		obj.openBrowsergetLowRes("https://www.google.com/flights/");

	}

}
