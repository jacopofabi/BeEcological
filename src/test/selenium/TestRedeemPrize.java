package test.selenium;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestRedeemPrize {
	
	//Danilo Dell'Orco 0245513
	public static void main(String[] args) {
		String expected1 = "Item acquired successfully.";
		String expected2 = "Not enough ecoPoints to buy this item.";
		String actual = "";
		String user = "user";
		String pass = "user";
		
		WebDriver webDriver = null;
		
		WebElement userField;
		WebElement passField;
		WebElement loginButton;
		WebElement logoutButton;
		WebElement shopButton;
		WebElement prizeButton;
		
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

			shopButton = webDriver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a"));
			shopButton.click();
			Thread.sleep(2000);
			
			prizeButton = webDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/form[1]/button"));
			prizeButton.click();
			Thread.sleep(2000);
			
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
		
		if(actual.equals(expected1) || actual.equals(expected2)) {
			Logger.getGlobal().log(Level.INFO, "Test Succesful");
		} else {
			Logger.getGlobal().log(Level.WARNING, "Test Failed");
		}
	}
}
