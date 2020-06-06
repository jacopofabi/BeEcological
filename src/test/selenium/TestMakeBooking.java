package test.selenium;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestMakeBooking {
	
	//Jacopo Fabi 0243948
	public static void main(String[] args) {
		String expected = "Your booking request is completed successfully!";
		String actual = "";
		String user = "user";
		String pass = "user";
		
		WebDriver webDriver = null;
		
		WebElement userField;
		WebElement passField;
		WebElement loginButton;
		WebElement logoutButton;
		WebElement bookButton;
		WebElement searchField;
		WebElement centerRow;
		WebElement datePicker;
		WebElement timePicker;
		
		try {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			webDriver = new ChromeDriver();
			webDriver.get("http://localhost:8080/BeEcological/index.jsp");
			webDriver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a")).click();
			Thread.sleep(2000);
			
			userField = webDriver.findElement(By.xpath("//*[@id=\"usr\"]"));
			passField = webDriver.findElement(By.xpath("//*[@id=\"pwd\"]"));
			loginButton = webDriver.findElement(By.xpath("/html/body/div/div/div[1]/form/div[3]/button"));
			userField.sendKeys(user);
			passField.sendKeys(pass);
			loginButton.click();
			Thread.sleep(2000);

			searchField = webDriver.findElement(By.xpath("//*[@id=\"src\"]"));
			searchField.sendKeys("Centro smaltimento acacia");
			searchField.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
			centerRow = webDriver.findElement(By.xpath("/html/body/div/table/tbody/tr"));
			centerRow.click();
			Thread.sleep(2000);

			datePicker = webDriver.findElement(By.xpath("//*[@id=\"datePicker\"]"));
			datePicker.sendKeys("26/06/2020");
			Thread.sleep(1000);
			
			timePicker = webDriver.findElement(By.xpath("//*[@id=\"exampleFormControlSelect1\"]"));
			timePicker.sendKeys("18:00");
			Thread.sleep(1000);
			
			bookButton = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[3]/div/form/button"));
			bookButton.click();
			
			actual = webDriver.switchTo().alert().getText();
			Thread.sleep(2000);
			webDriver.switchTo().alert().accept();
			
			webDriver.findElement(By.xpath("//*[@id=\"navbarDropdown\"]")).click();
			Thread.sleep(1000);
			logoutButton = webDriver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/div/a[2]"));
			logoutButton.click();
		
		} catch(Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "Test Exception");
		} finally {
			if(webDriver != null) {
				webDriver.close();
			}
		}
		
		if(actual.equals(expected)) {
			Logger.getGlobal().log(Level.INFO, "Test Succesful");
		} else {
			Logger.getGlobal().log(Level.WARNING, "Test Failed");
		}
	}
}
