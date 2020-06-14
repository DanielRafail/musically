/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * UI Automation sample test 
 * @author Cerba Mihail
 */
@Ignore
public class ChromeTest {

	private WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@Before
	public void setupTest() {
		driver = new ChromeDriver();
	}

        @After
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void test() {
		driver.get("http://localhost:8080/musically");
                driver.findElement(By.id("searchForm:search_box")).sendKeys("Scorpion");
                driver.findElement(By.id("searchForm:searchBtn")).click();
                WebElement title = driver.findElement(By.id("album_title"));
                String AlbumTitle = title.getText();
                
                assertEquals(AlbumTitle,"Scorpion");
	}
        
}

