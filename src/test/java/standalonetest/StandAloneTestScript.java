package standalonetest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTestScript {

	
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
	    WebElement loc_txt_username = driver.findElement(By.xpath("//input[@id='userEmail']"));
	    loc_txt_username.sendKeys("aptest@automation.com");
	    
	    WebElement loc_txt_password = driver.findElement(By.xpath("//input[@id='userPassword']"));
	    loc_txt_password.sendKeys("Aptest@123");
	    
	    WebElement loc_btn_login = driver.findElement(By.xpath("//input[@type='submit']"));
	    loc_btn_login.click();
	    
	    String productName = "ZARA COAT 3";
	    
	    List<WebElement> productsList = driver.findElements(By.cssSelector("div.mb-3"));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));

	    
	
	     WebElement prodName = productsList.stream().filter(product -> 
	     product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

         prodName.findElement(By.cssSelector(".card-body button:last-of-type")).click();

	     
	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	     wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	     
	     driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	    
	     
	     
//	    driver.quit();
	    
	    
		
		

	}

}
