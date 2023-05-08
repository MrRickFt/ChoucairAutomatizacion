package exitoauth;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ExitoMain{

	 public static void main(String[] args) throws InterruptedException {
		 DriverConfiguration driverManager = new DriverConfiguration();
		 WebDriver driver = driverManager.getDriver();


	        // Create a new instance of the ExitoHomePage class
		 	
	        ExitoHomePage exitoHomePage = new ExitoHomePage(driver);
	        exitoHomePage.navigateToHomePage();
	        exitoHomePage.selectCategory();
	        exitoHomePage.selectSubCategory();
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
