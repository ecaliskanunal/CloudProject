package com.tryCloud.stepDefinitions;

import com.tryCloud.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;

public class Hooks {

    @After
    public void tearDownScenario(Scenario scenario) throws IOException { //use the Scenario from cucumber-java

        if (scenario.isFailed()) {
            //Driver.getDriver().manage().window().setSize(new Dimension(1200,1000));
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }
}
