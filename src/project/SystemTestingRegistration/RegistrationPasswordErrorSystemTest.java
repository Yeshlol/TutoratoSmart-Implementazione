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

public class RegistrationPasswordErrorSystemTest {
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
  public void registrationPasswordErrorSystem() {
    driver.get("http://localhost:8080/TutoratoSmart/");
    driver.manage().window().setSize(new Dimension(867, 783));
    driver.findElement(By.cssSelector("div:nth-child(2) > .btn")).click();
    driver.findElement(By.cssSelector(".row:nth-child(2) > .form-group:nth-child(1)")).click();
    driver.findElement(By.id("Email")).click();
    driver.findElement(By.id("Email")).click();
    driver.findElement(By.id("Email")).sendKeys("a.oliviei@studenti.unicampania.it");
    driver.findElement(By.id("RegistrationNumber")).click();
    driver.findElement(By.id("RegistrationNumber")).sendKeys("A512101212");
    driver.findElement(By.id("Password")).click();
    driver.findElement(By.id("Password")).sendKeys("M12345678");
    driver.findElement(By.id("VerifyPassword")).click();
    driver.findElement(By.id("VerifyPassword")).sendKeys("M12345678");
    driver.findElement(By.id("Password")).click();
    driver.findElement(By.id("VerifyPassword")).click();
    driver.findElement(By.id("VerifyPassword")).sendKeys("M123456789");
    driver.findElement(By.id("FirstName")).click();
    driver.findElement(By.id("FirstName")).sendKeys("Alessia");
    driver.findElement(By.id("LastName")).sendKeys("Olivieri");
    driver.findElement(By.id("TelephoneNumber")).click();
    driver.findElement(By.id("TelephoneNumber")).click();
    driver.findElement(By.id("TelephoneNumber")).click();
    driver.findElement(By.id("TelephoneNumber")).sendKeys("3465891013");
    driver.findElement(By.cssSelector(".radio-inline:nth-child(4)")).click();
    driver.findElement(By.id("register")).click();
    {
      WebElement element = driver.findElement(By.id("register"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
  }
}
