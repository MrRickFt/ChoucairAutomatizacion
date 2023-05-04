package exitoauth;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions actions;
	protected JavascriptExecutor jsExecutor;

	
	public BasePage(WebDriver driver) {
	    this.driver = driver;
	    wait = new WebDriverWait(driver, 20);
	    actions = new Actions(driver);
	    jsExecutor = (JavascriptExecutor) driver;
	    PageFactory.initElements(driver, this);
	    
	}
}
