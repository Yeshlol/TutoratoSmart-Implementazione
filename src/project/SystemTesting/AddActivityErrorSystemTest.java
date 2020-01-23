package project.SystemTesting;
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


public class AddActivityErrorSystemTest {
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
  public void addActivityErrorSystem() {
	driver.get("http://localhost:8080/TutoratoSmart/");
	driver.manage().window().setSize(new Dimension(867, 783));
	driver.findElement(By.linkText("Accedi")).click();
	driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).sendKeys("m.pisciotta@studenti.unicampania.it");
	driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("M12345678");
	driver.findElement(By.id("login")).click();  
	  
	  
    driver.get("http://localhost:8080/TutoratoSmart/tutor/activity.jsp");
    driver.manage().window().setSize(new Dimension(1200, 1024));
    driver.findElement(By.id("category")).click();
    {
      WebElement dropdown = driver.findElement(By.id("category"));
      dropdown.findElement(By.xpath("//option[. = 'Evento']")).click();
    }
    driver.findElement(By.id("category")).click();
    driver.findElement(By.id("date")).click();
    driver.findElement(By.id("date")).click();
    driver.findElement(By.id("date")).click();
    driver.findElement(By.id("date")).click();
    driver.findElement(By.id("date")).click();
    driver.findElement(By.id("date")).click();
    driver.findElement(By.id("date")).sendKeys("2020-01-01");
    driver.findElement(By.id("startTime")).click();
    driver.findElement(By.id("startTime")).sendKeys("00:30");
    driver.findElement(By.id("startTime")).sendKeys("09:30");
    driver.findElement(By.id("startTime")).sendKeys("09:00");
    driver.findElement(By.id("finishTime")).click();
    driver.findElement(By.id("finishTime")).sendKeys("02:00");
    driver.findElement(By.id("finishTime")).sendKeys("23:00");
    driver.findElement(By.id("description")).click();
    driver.findElement(By.id("description")).sendKeys("Giornata open-day");
    driver.findElement(By.id("addActivity")).click();
    {
      WebElement element = driver.findElement(By.id("addActivity"));
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
