package project.SystemTestingRegistration;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class RegistrationNameFormatErrorSystemTest {
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
  public void registrationNameFormatErrorSystem() {
    driver.get("http://localhost:8080/TutoratoSmart/");
    driver.manage().window().setSize(new Dimension(867, 783));
    driver.findElement(By.cssSelector("div:nth-child(2) > .btn")).click();
    driver.findElement(By.id("Email")).click();
    driver.findElement(By.id("Email")).sendKeys("a.olivieri@studenti.unicampania.it");
    driver.findElement(By.id("RegistrationNumber")).click();
    driver.findElement(By.id("RegistrationNumber")).sendKeys("A512101212");
    driver.findElement(By.id("Password")).click();
    driver.findElement(By.id("Password")).sendKeys("M12345678");
    driver.findElement(By.id("RegistrationNumber")).click();
    driver.findElement(By.id("Email")).click();
    driver.findElement(By.id("VerifyPassword")).click();
    driver.findElement(By.id("Email")).click();
    driver.findElement(By.id("Email")).click();
    driver.findElement(By.id("Email")).sendKeys("a.olivierii@studenti.unicampania.it");
    driver.findElement(By.id("VerifyPassword")).click();
    driver.findElement(By.id("VerifyPassword")).sendKeys("M12345678");
    driver.findElement(By.id("FirstName")).click();
    driver.findElement(By.id("FirstName")).sendKeys("alessia1");
    driver.findElement(By.id("LastName")).click();
    driver.findElement(By.id("LastName")).sendKeys("Olivieri");
    driver.findElement(By.id("TelephoneNumber")).click();
    driver.findElement(By.id("TelephoneNumber")).click();
    driver.findElement(By.id("TelephoneNumber")).sendKeys("3465891013");
    driver.findElement(By.id("F")).click();
    driver.findElement(By.cssSelector(".row:nth-child(5)")).click();
    driver.findElement(By.cssSelector(".content")).click();
    driver.findElement(By.id("register")).click();
    js.executeScript("window.scrollTo(0,355.20001220703125)");
  }
}
