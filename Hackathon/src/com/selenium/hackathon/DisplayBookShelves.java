package com.selenium.hackathon;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;



public class DisplayBookShelves {

	
	private WebDriver driver=null;
	
	@Parameters("browser")
    @BeforeMethod
    public void initializeBrowser(String browserName) {
    	
    	try {
        if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "E:\\eclipse-jee-2023-03-R-win32-x86_64\\eclipse\\eclipse-workspace\\Hackathon\\Drivers\\msedgedriver.exe");
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver","E:\\eclipse-jee-2023-03-R-win32-x86_64\\eclipse\\eclipse-workspace\\Hackathon\\Drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser name");
        }
    	}catch(Exception e) {
    		System.out.println("We couldn't find the reqested browser: By default opening edge");
    		System.setProperty("webdriver.edge.driver", "E:\\eclipse-jee-2023-03-R-win32-x86_64\\eclipse\\eclipse-workspace\\Hackathon\\Drivers\\msedgedriver.exe");
            driver = new EdgeDriver();
    	}
        driver.manage().window().maximize();
    }
	
	 @Test(priority=1)
	    public void validateTitle() {
	    	
	    	System.out.println("Opening urbanladder.com website");
	    	driver.get("https://www.urbanladder.com/");
	        String expectedTitle = "Up to 70% off on Online Furniture | Full House Sale - Urban Ladder";
	        String actualTitle = driver.getTitle();
	      
	        if(expectedTitle.equals(actualTitle)) {
	        	System.out.println("Title verified");
	        	
	        }
	        else {
	        	System.out.println("Title verification failed");
            }

	        
	       
	        //to select Bookshelves
	        WebElement bookshelves=driver.findElement(By.xpath("//*[name()='path' and contains(@d,'M24.5 3h-1')]"));
	        bookshelves.click();
	        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	        
	        
	        //for closing the sign up pop up
	        WebElement close=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"authentication_popup\"]/div[1]/div/div[2]/a[1]")));
	        close.click();

	        
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	       
	        //to check Exclude out of Stock checkBox
	        WebElement checkbox=driver.findElement(By.id("filters_availability_In_Stock_Only"));
	        checkbox.click();

	
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        //choosing open from storageType
	        WebElement storageType = driver.findElement(By.xpath("//li[@data-group='storage type']"));
	        Actions action=new Actions(driver);
			action.moveToElement(storageType).perform();
			WebElement checkboxForOpenStorage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='filters_storage_type_Open']")));
	        checkboxForOpenStorage.click();
			
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    
	        
	        //setting maxPrice as 15000
	    	WebElement price=driver.findElement(By.xpath("//div[normalize-space()='Price']"));
	        Actions bookshelvesPrice=new Actions(driver);
			bookshelvesPrice.moveToElement(price).perform();
			WebElement upperHandle=driver.findElement(By.xpath("//*[@id=\"filters-form\"]/div[1]/div/div/ul/li[1]/div[2]/div/div/ul/li[1]/div/div[2]/div[2]/div/div[2]/div"));
			new Actions(driver).dragAndDropBy(upperHandle,-238,0).perform();
			
			//
			
			 try {
		            Thread.sleep(5000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
			    List<WebElement> listOfBookshelves =driver.findElements(By.xpath("//div[@"));
			    for(int i=0;i<listOfBookshelves.size() && i<3;i++) {
				WebElement bookshelf = listOfBookshelves.get(i);	
				bookshelf.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='product-title']")));

				WebElement nameOfBookshelves = driver.findElement(By.xpath("//h2[@class='product-title']"));
				WebElement priceOfBookshelves = driver.findElement(By.xpath("//div[@class='price discounted-price']"));
				
				String name = nameOfBookshelves.getText();
	            String priceWithDiscount = priceOfBookshelves.getText();

	            System.out.println("Bookshelf Name: " + name);
	            System.out.println("Price: " + priceWithDiscount);
	            driver.navigate().back();
			
			}
			
}
}
