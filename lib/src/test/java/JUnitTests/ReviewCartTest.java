package JUnitTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import exitoauth.DriverConfiguration;
import exitoauth.ExitoHomePage;

public class ReviewCartTest {

	private DriverConfiguration driverManager = new DriverConfiguration();
    private WebDriver driver = driverManager.getDriver();
    private ExitoHomePage exitoHomePage = new ExitoHomePage(driver);
	
    @Before
    public void testNavigateToHomePage() throws InterruptedException {
        
		exitoHomePage.navigateToHomePage();
        
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void testVerifyProductsToCart() throws InterruptedException {
        // Ejecutar el método que se desea probar
    	exitoHomePage.selectCategory();
    	exitoHomePage.selectSubCategory();
    	exitoHomePage.addRandomProductsToCart(5);
    	Thread.sleep(3000);
        Double total = exitoHomePage.verifyProductsToCart();

        // Verificar que el total sea mayor a cero
        assertTrue(total > 0);

        
    }
}
