package JUnitTests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import exitoauth.DriverConfiguration;
import exitoauth.ExitoHomePage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExitoMainTest {
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
    public void test01Tittle() {
    	String pageTitle = driver.getTitle();
        assertEquals("Compra Online en Colombia | exito.com", pageTitle);
        
    }
    
    @Test
    public void test02SelectCategory() throws InterruptedException {
    	String selectedCategory = exitoHomePage.selectCategory();
        assertEquals("Tecnología", selectedCategory);
        
    }
    
    @Test
    public void test03SubCategory() throws InterruptedException {
        String expectedTitle = "Tecnología: Computadores & Portátiles - exito.com";
        exitoHomePage.selectCategory();
        String selectedSubCategodry = exitoHomePage.selectSubCategory();
        assertEquals(expectedTitle, selectedSubCategodry);
        
        
        
    }

}
