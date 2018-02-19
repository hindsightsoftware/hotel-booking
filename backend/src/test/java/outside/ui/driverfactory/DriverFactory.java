package outside.ui.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    public WebDriver create(){
        String pathToChromeDriver = System.getProperty("user.dir") + "/backend/src/test/java/outside/ui/driverfactory/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        return new ChromeDriver();
    }
}
