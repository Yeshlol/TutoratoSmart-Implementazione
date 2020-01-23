package project.SystemTesting;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class AddAppointmentSuccessSystemTest {
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
  public void addAppointmentSuccessSystem() {
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
    driver.findElement(By.linkText("Gestione Sportello")).click();
    driver.findElement(By.linkText("Nuove Prenotazioni")).click();
    
    driver.get("http://localhost:8080/TutoratoSmart/ShowRequest?flag=4");
    
    driver.findElement(By.linkText("Mostra")).click();
    
   driver.get("http://localhost:8080/TutoratoSmart/ShowRequest?id=4&flag=3"); //MODIFICARE
    driver.findElement(By.id("acceptRequest")).click();
    
   // driver.get("http://localhost:8080/TutoratoSmart/ShowRequest?id=5&flag=3");
    driver.findElement(By.cssSelector(".row:nth-child(1) .btn-success")).click();
    
    driver.get("http://localhost:8080/TutoratoSmart/tutor/requestInfo.jsp");
    
    driver.findElement(By.id("confirmAppointment")).click();
    
    driver.get("http://localhost:8080/TutoratoSmart/tutor/appointment.jsp");
    driver.findElement(By.id("appointmentComment")).click();
    driver.findElement(By.id("appointmentComment")).sendKeys("Appuntamento effettuato con lo studente");
    driver.findElement(By.id("addAppointment")).click();
  }
}
