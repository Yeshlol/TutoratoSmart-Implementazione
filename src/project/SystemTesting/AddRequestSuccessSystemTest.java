package project.SystemTesting;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class AddRequestSuccessSystemTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void addRequestSuccessSystem() {
	 
	driver.get("http://localhost:8080/TutoratoSmart/");
	driver.manage().window().setSize(new Dimension(867, 783));
	driver.findElement(By.linkText("Accedi")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).sendKeys("e.merola@studenti.unicampania.it");
	driver.findElement(By.id("password")).click();
	driver.findElement(By.id("password")).sendKeys("M12345678");
	driver.findElement(By.id("login")).click();
	    
    driver.get("http://localhost:8080/TutoratoSmart/home.jsp");
    driver.manage().window().setSize(new Dimension(1234, 785));
    driver.findElement(By.linkText("Prenota Appuntamento")).click();
    driver.findElement(By.id("requestDate")).click();
    driver.findElement(By.id("requestDate")).sendKeys("2020-01-29");
    driver.findElement(By.id("requestTime")).click();
    driver.findElement(By.id("requestTime")).sendKeys("01:00");
    driver.findElement(By.id("requestTime")).sendKeys("10:00");
    driver.findElement(By.id("comment")).click();
    driver.findElement(By.id("comment")).sendKeys("Mi serve aiuto per lâ€™immatricolazione");
    driver.findElement(By.id("create")).click();
  }
}
