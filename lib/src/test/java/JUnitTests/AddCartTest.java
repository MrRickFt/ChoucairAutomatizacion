package JUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import exitoauth.DriverConfiguration;
import exitoauth.ExitoHomePage;

public class AddCartTest {

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
    public void testAddRandomProductsToCart() throws InterruptedException {
    	exitoHomePage.selectCategory();
    	exitoHomePage.selectSubCategory();
        String resultMessage = exitoHomePage.addRandomProductsToCart(5);
        assertEquals("Productos seleccionados correctamente", resultMessage);
    }
}
