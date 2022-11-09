package com.tryCloud.stepDefinitions;

import com.tryCloud.pages.LoginPage;
import com.tryCloud.pages.LogoutPage;
import com.tryCloud.utilities.ConfigurationReader;
import com.tryCloud.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutStepDefinitions {

    LoginPage loginPage = new LoginPage();
    LogoutPage logoutPage = new LogoutPage();
    Actions actions = new Actions(Driver.getDriver());
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

    @Given("user is on dashboard")
    public void user_is_on_dashboard() {
        Driver.getDriver().get(ConfigurationReader.getProperty("URL"));
        loginPage.usernameInputBox.sendKeys(ConfigurationReader.getProperty("username"));
        loginPage.passwordInputBox.sendKeys(ConfigurationReader.getProperty("password"));
        loginPage.loginButton.click();

    }
    @When("user clicks on dropdown menu")
    public void user_clicks_on_dropdown_menu() {
        actions.click(logoutPage.userImage).perform();
    }

    @Then("user should be able to see logout button")
    public void user_should_be_able_to_see_logout_button() {
        Assert.assertTrue(logoutPage.logoutOption.isDisplayed());
    }

    @When("user clicks logout button")
    public void user_clicks_logout_button() {
        logoutPage.logoutOption.click();
    }
    @Then("user should be able to click on logout button")
    public void user_should_be_able_to_click_on_logout_button() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(logoutPage.logoutOption));
        Assert.assertTrue(element.isDisplayed());
    }


    @Then("user should be able to log out")
    public void user_should_be_able_to_log_out() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("login"));
    }

    @When("user navigates back")
    public void user_navigates_back() {
        Driver.getDriver().navigate().back();
    }
    @Then("user should be able to land on login page")
    public void user_should_be_able_to_land_on_login_page() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("login"));
    }

    @Then("user should not be able to see his username in username box")
    public void user_should_not_be_able_to_see_his_username_in_username_box() {
        Assert.assertTrue(loginPage.usernameInputBox.getText().isEmpty());
    }

}
