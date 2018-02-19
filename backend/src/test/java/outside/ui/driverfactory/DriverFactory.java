package outside.ui.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.net.URL;

public class DriverFactory {

    public WebDriver create(){
        String filePath = new File("").getAbsolutePath();
        String pathToChromeDriver = "";

        if(filePath.contains("backend")){
            pathToChromeDriver = filePath.concat("/src/test/java/outside/ui/driverfactory/chromedriver");
        } else {
            pathToChromeDriver = filePath.concat("/backend/src/test/java/outside/ui/driverfactory/chromedriver");
        }

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        return new ChromeDriver();
    }
}
