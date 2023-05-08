package exitoauth;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverConfiguration {

	public WebDriver getDriver() {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
                put("profile.default_content_setting_values.notifications", 2);
            }});
            
            return new ChromeDriver(options);
        } catch (Exception e) {
        	
            return null;
        }
    }
	
}
