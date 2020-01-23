package project.SystemTestingLogin;

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

public class LoginSuccessTutorSystemTest {
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
  public void loginSuccessTutorSystem() {
    driver.get("http://localhost:8080/TutoratoSmart/");
    driver.manage().window().setSize(new Dimension(867, 783));
    driver.findElement(By.linkText("Accedi")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).sendKeys("m.pisciotta@studenti.unicampania.it");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("M12345678");
    driver.findElement(By.id("login")).click();
  }
}
