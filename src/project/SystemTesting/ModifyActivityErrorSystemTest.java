package project.SystemTesting;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;


public class ModifyActivityErrorSystemTest {
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
  public void modifyActivityErrorSystem() {
	driver.get("http://localhost:8080/TutoratoSmart/");
	driver.manage().window().setSize(new Dimension(867, 783));
	driver.findElement(By.linkText("Accedi")).click();
	driver.findElement(By.id("email")).click();
	driver.findElement(By.id("email")).sendKeys("m.pisciotta@studenti.unicampania.it");
	driver.findElement(By.id("password")).click();
	driver.findElement(By.id("password")).sendKeys("M12345678");
	driver.findElement(By.id("login")).click();

    driver.get("http://localhost:8080/TutoratoSmart/home.jsp");
    driver.manage().window().setSize(new Dimension(1200, 1024));
    driver.findElement(By.linkText("Registro Tutorato")).click();
    
    driver.get("http://localhost:8080/TutoratoSmart/ShowRegister?flag=1");
    
    driver.findElement(By.linkText("Mostra")).click();
    
    driver.get("http://localhost:8080/TutoratoSmart/ShowActivity?id=1&flag=1");
    
    driver.findElement(By.id("content text-center")).click();
    driver.findElement(By.id("modifyActivity")).click();
    
    driver.get("http://localhost:8080/TutoratoSmart/tutor/activityModify.jsp");
    
    driver.findElement(By.id("categoryM")).click();
    {
      WebElement dropdown = driver.findElement(By.id("categoryM"));
      dropdown.findElement(By.xpath("//option[. = 'Evento']")).click();
    }
    driver.findElement(By.cssSelector("option:nth-child(6)")).click();
    driver.findElement(By.id("dateM")).click();
    driver.findElement(By.id("dateM")).sendKeys("2020-01-01");
    driver.findElement(By.id("startTimeM")).click();
    driver.findElement(By.id("startTimeM")).sendKeys("00:30");
    driver.findElement(By.id("startTimeM")).sendKeys("09:30");
    driver.findElement(By.id("startTimeM")).sendKeys("06:00");
    driver.findElement(By.id("finishTimeM")).click();
    driver.findElement(By.id("finishTimeM")).sendKeys("01:00");
    driver.findElement(By.id("finishTimeM")).sendKeys("11:00");
    driver.findElement(By.id("description")).click();
    driver.findElement(By.id("description")).sendKeys("Giornata open-day");
    driver.findElement(By.id("modifyActivity")).click();
  }
}