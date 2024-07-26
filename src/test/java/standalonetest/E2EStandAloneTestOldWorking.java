package standalonetest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class E2EStandAloneTestOldWorking {

	public static void main(String[] args) throws InterruptedException {
	    
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
        
		//Login Page
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("aptest@automation.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Aptest@123");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		
		//LandingPage/dashboardPage
		
//		String productName = "ADIDAS ORIGINAL";
		String productName = "ZARA COAT 3";
		
		List<WebElement> prodList = driver.findElements(By.cssSelector("div.mb-3"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
		
		//Applying Java Stream concepts
		WebElement product = prodList.stream().filter(prod-> prod.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		//Limiting the web locator scope within the product instead of WebDriver
		
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
				
	    //Waiting for visibility of tostcontainer element post clicking on AddToCart
		
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div #toast-container")));
	    
	   
	  //Waiting for invisibility of progress element post visibility of toast container 
	    
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	    
	    
	    //Clicking on Cart option at the top header of the page
	    //    driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	
	    driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	    
	    //Finding list of products added into the cart
	   List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	   
	   boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	   Assert.assertTrue(match);
	   
	   //Clicking on Checkout button
	   driver.findElement(By.cssSelector(".totalRow button")).click();
	   
	   //Selecting country as India from the drop-down menu.
	   Actions action = new Actions(driver);
	   action.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,350)", "");
		
		WebElement element = driver.findElement(By.cssSelector(".action__submit"));
		
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//
//		jse.executeScript("arguments[0].scrollIntoView()", ele); 
		
        // As The element is not visible to click. and received element click intercepted hence used action class
		action.moveToElement(element).click().perform();
//	   driver.findElement(By.cssSelector(".action__submit")).click();
	   Thread.sleep(5000);
		String confirmMsg = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
	   Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	   
	   driver.quit();
	}

}
