package com.tryCloud.stepDefinitions;

import com.tryCloud.pages.LoginPage;
import com.tryCloud.utilities.BrowserUtils;
import com.tryCloud.utilities.ConfigurationReader;
import com.tryCloud.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginStepDefinitions {

    LoginPage loginPage = new LoginPage();
    Actions actions = new Actions(Driver.getDriver());

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("user types username {string}")
    public void user_types_username(String string) {
        BrowserUtils.sleep(1);
        loginPage.usernameInputBox.sendKeys(ConfigurationReader.getProperty("username"));
    }

    @When("user types password {string}")
    public void user_types_password(String string) {
        BrowserUtils.sleep(1);
        loginPage.passwordInputBox.sendKeys(ConfigurationReader.getProperty("password"));
    }


    @When("user enters invalid username {string}")
    public void userEntersInvalidUsername(String username) {
        BrowserUtils.sleep(1);
        loginPage.usernameInputBox.sendKeys(username);
    }

    @And("user enters invalid password {string}")
    public void userEntersInvalidPassword(String password) {
        BrowserUtils.sleep(1);
        loginPage.passwordInputBox.sendKeys(password);
    }

    @When("user enters missing credentials username {string}")
    public void userEntersMissingCredentialsUsername(String username) {
        BrowserUtils.sleep(1);
        loginPage.usernameInputBox.sendKeys(username);
    }

    @And("user enters missing credentials password {string}")
    public void userEntersMissingCredentialsPassword(String password) {
        BrowserUtils.sleep(1);
        loginPage.passwordInputBox.sendKeys(password);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        loginPage.loginButton.click();
    }

    @When("user hits enter key")
    public void user_hits_enter_key() {
        BrowserUtils.sleep(1);
        actions.click(loginPage.loginButton).sendKeys(Keys.ENTER).perform();
    }

    @Then("user can login")
    public void userCanLogin() {
        wait.until(ExpectedConditions.visibilityOf(loginPage.userImage));
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("dashboard"));
    }

    @Then("user cannot login")
    public void userCannotLogin() {
        BrowserUtils.sleep(1);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("login"));
    }

    @Then("user sees validation message {string}")
    public void user_sees_validation_message(String string) {
        BrowserUtils.sleep(1);
        if (loginPage.usernameInputBox==null){
            String validationMessage = loginPage.usernameInputBox.getAttribute("validationMessage");
            Assert.assertEquals(validationMessage, string);
        }else if (loginPage.passwordInputBox==null){
            String validationMessage = loginPage.passwordInputBox.getAttribute("validationMessage");
            Assert.assertEquals(validationMessage, string);
        }

    }

    @Then("user sees error message {string}")
    public void user_sees_error_message(String string) {
        wait.until(ExpectedConditions.visibilityOf(loginPage.errorMessage));
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
    }


    @When("user sees password {string} hidden")
    public void user_sees_password_hidden(String string) {
        String expectedValueInTypeAttribute = "password";
        String actualValueInTypeAttribute = loginPage.passwordInputBox.getAttribute("type");
        Assert.assertEquals(expectedValueInTypeAttribute, actualValueInTypeAttribute);
    }

    @When("user toggles visibility")
    public void user_toggles_visibility() {
        loginPage.togglePasswordButton.click();
    }

    @Then("user sees password {string} visible")
    public void user_sees_password_visible(String string) {
        String expectedValueInTypeAttribute = "text";
        String actualValueInTypeAttribute = loginPage.passwordInputBox.getAttribute("type");
        Assert.assertEquals(expectedValueInTypeAttribute, actualValueInTypeAttribute);
    }

    @When("user sees forgot password link")
    public void user_sees_forgot_password_link() {
        Assert.assertTrue(loginPage.forgotPasswordLink.isDisplayed());
    }

    @When("user clicks forgot password link")
    public void user_clicks_forgot_password_link() {
        loginPage.forgotPasswordLink.click();
    }


    @And("user types username {string} in reset password link")
    public void userTypesUsernameInResetPasswordLink(String string) {
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.resetInputBox));
        actions.click(loginPage.resetInputBox).sendKeys(string).perform();
    }

    @Then("user clicks reset password")
    public void user_clicks_reset_password() {
        loginPage.resetPasswordLink.click();
    }

    @When("user sees the placeholder for username")
    public void user_sees_the_placeholder_for_username() {
        String expectedPlaceholderForUsername = "Username or email";
        String actualPlaceholderForUsername = loginPage.usernameInputBox.getAttribute("placeholder");
        Assert.assertEquals(expectedPlaceholderForUsername, actualPlaceholderForUsername);
    }

    @When("user sees the placeholder for password")
    public void user_sees_the_placeholder_for_password() {
        String expectedPlaceholderForPassword = "Password";
        String actualPlaceholderForPassword = loginPage.passwordInputBox.getAttribute("placeholder");
        Assert.assertEquals(expectedPlaceholderForPassword, actualPlaceholderForPassword);
    }

    @When("user refreshes the page")
    public void user_refreshes_the_page() {
        Driver.getDriver().navigate().refresh();
    }

    @Then("user is still logged in")
    public void user_is_still_logged_in() {
        wait.until(ExpectedConditions.visibilityOf(loginPage.userImage));
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("dashboard"));
    }


}
