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

public class ModifyAppointmentErrorSystemTest {
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
  public void modifyAppointmentErrorSystem() {
	  
	driver.get("http://localhost:8080/TutoratoSmart/");
	driver.manage().window().setSize(new Dimension(867, 783));
	driver.findElement(By.linkText("Accedi")).click();
	driver.findElement(By.id("email")).click();
	driver.findElement(By.id("email")).sendKeys("m.pisciotta@studenti.unicampania.it");
	driver.findElement(By.id("password")).click();
	driver.findElement(By.id("password")).sendKeys("M12345678");
	driver.findElement(By.id("login")).click();
	
    driver.get("http://localhost:8080/TutoratoSmart/home.jsp");
    driver.manage().window().setSize(new Dimension(1234, 784));
    driver.findElement(By.linkText("Storico Appuntamenti")).click();
    
    driver.get("http://localhost:8080/TutoratoSmart/ShowAppointment?flag=1");
    
    driver.findElement(By.cssSelector("tr:nth-child(3) a")).click();
    
    driver.get("http://localhost:8080/TutoratoSmart/ShowAppointment?id=1&flag=2");
    
    driver.findElement(By.id("modifyAppointment")).click();
    
    driver.get("http://localhost:8080/TutoratoSmart/tutor/appointmentModify.jsp");
    
    driver.findElement(By.id("appointmentComment")).click();
    driver.findElement(By.id("appointmentComment")).sendKeys("Ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
    driver.findElement(By.id("modifyAppointment")).click();
  }
}