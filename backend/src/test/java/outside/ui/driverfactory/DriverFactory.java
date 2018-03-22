package outside.ui.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.net.URL;

public class DriverFactory {

    public WebDriver create(){
        return new ChromeDriver();
    }
}
