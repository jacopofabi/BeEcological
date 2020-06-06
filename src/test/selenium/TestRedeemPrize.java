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
		
		WebDriver webDriver1 = null;
		
		WebElement userField1;
		WebElement passField1;
		WebElement loginButton1;
		WebElement logoutButton1;
		WebElement shopButton1;
		WebElement prizeButton1;
		
		try {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			webDriver1 = new ChromeDriver();
			webDriver1.get("http://localhost:8080/BeEcological/index.jsp");
			webDriver1.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a")).click();
			Thread.sleep(2000);
			
			userField1 = webDriver1.findElement(By.xpath("//*[@id=\"usr\"]"));
			passField1 = webDriver1.findElement(By.xpath("//*[@id=\"pwd\"]"));
			loginButton1 = webDriver1.findElement(By.xpath("/html/body/div/div/div[1]/form/div[3]/button"));
			userField1.sendKeys(user);
			passField1.sendKeys(pass);
			loginButton1.click();
			Thread.sleep(2000);

			shopButton1 = webDriver1.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[1]/a"));
			shopButton1.click();
			Thread.sleep(2000);
			
			prizeButton1 = webDriver1.findElement(By.xpath("/html/body/div/div/div/div[1]/form[1]/button"));
			prizeButton1.click();
			Thread.sleep(2000);
			
			actual = webDriver1.switchTo().alert().getText();
			Thread.sleep(2000);
			webDriver1.switchTo().alert().accept();
			
			webDriver1.findElement(By.xpath("//*[@id=\"navbarDropdown\"]")).click();
			Thread.sleep(1000);
			logoutButton1 = webDriver1.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/div/a[2]"));
			logoutButton1.click();
		
		} catch(Exception e) {
			Logger.getGlobal().log(Level.SEVERE, "Test Exception");
		} finally {
			if(webDriver1 != null) {
				webDriver1.close();
			}
		}
		
		if(actual.equals(expected1) || actual.equals(expected2)) {
			Logger.getGlobal().log(Level.INFO, "Test Succesful");
		} else {
			Logger.getGlobal().log(Level.WARNING, "Test Failed");
		}
	}
}
