package outside.api;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"outside/api"},
        features = {"src/test/resource/outside"},
        format = {"html:target/failsafe-reports"}
)
public class RunCukesIT {
}