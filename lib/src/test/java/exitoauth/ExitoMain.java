package exitoauth;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ExitoMain{

	 public static void main(String[] args) throws InterruptedException {
	        // Aquí se puede llamar a los métodos de las otras clases
		 	WebDriverManager.chromedriver().setup();
		 	ChromeOptions options = new ChromeOptions();
		 	options.addArguments("--disable-notifications");
		 	WebDriver driver = new ChromeDriver(options);

	        // Create a new instance of the ExitoHomePage class
		 	
	        ExitoHomePage exitoHomePage = new ExitoHomePage(driver);
	        exitoHomePage.navigateToHomePage();
	        exitoHomePage.selectMarketCategory();
	        exitoHomePage.selectLaptopSubCategory();
	        Thread.sleep(8000);
	        List<WebElement> productos = exitoHomePage.getVisibleProducts();
	        System.out.println("Productos visibles: " + productos.size());
	        Thread.sleep(3000);
	        try {
	            exitoHomePage.addRandomProductsToCart(5);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        Thread.sleep(3000);
	        System.out.println("");
	        System.out.println("#########################");
	        System.out.println("SECCIÓN DE CKECKOUT");
	        System.out.println("#########################");
	        exitoHomePage.verifyProductsToCart();
	        
	       
	        driver.quit();
	 }

}
