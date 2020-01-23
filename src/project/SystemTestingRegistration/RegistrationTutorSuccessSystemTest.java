package project.SystemTestingRegistration;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class RegistrationTutorSuccessSystemTest {
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
  public void registrationTutorSuccessSystem() {
	driver.get("http://localhost:8080/TutoratoSmart/");
	driver.manage().window().setSize(new Dimension(867, 783));
	driver.findElement(By.cssSelector("div:nth-child(1) > .btn")).click();
	driver.findElement(By.id("email")).click();
	driver.findElement(By.id("email")).sendKeys("d.molinaro@commissione.unicampania.it");
	driver.findElement(By.id("password")).click();
	driver.findElement(By.id("password")).sendKeys("D12345678");
	driver.findElement(By.id("login")).click();
	
    driver.get("http://localhost:8080/TutoratoSmart/commission/tutorRegistration.jsp");
    driver.manage().window().setSize(new Dimension(1200, 1024));
    driver.findElement(By.id("Email")).click();
    driver.findElement(By.id("Email")).sendKeys("f.pagano@studenti.unicampania.it");

    {
      WebElement element = driver.findElement(By.id("RegistrationNumber"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("RegistrationNumber")).click();
    driver.findElement(By.id("RegistrationNumber")).sendKeys("A512101213");

    {
      WebElement element = driver.findElement(By.id("Password"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("Password")).click();
    driver.findElement(By.id("Password")).sendKeys("M12345678");

    {
      WebElement element = driver.findElement(By.id("VerifyPassword"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("VerifyPassword")).click();
    driver.findElement(By.id("VerifyPassword")).sendKeys("M12345678");

    {
      WebElement element = driver.findElement(By.id("FirstName"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("FirstName")).click();
    driver.findElement(By.id("FirstName")).sendKeys("Francesco");
    driver.findElement(By.id("LastName")).sendKeys("Pagano");
    driver.findElement(By.id("TelephoneNumber")).sendKeys("3465891013");
    {
      WebElement element = driver.findElement(By.id("TotalHours"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("TotalHours")).click();
    driver.findElement(By.id("TotalHours")).sendKeys("60");
    driver.findElement(By.id("StartDate")).click();
    driver.findElement(By.id("StartDate")).sendKeys("2020-01-01");
    {
      WebElement element = driver.findElement(By.id("register"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.id("register")).click();
  }
}
