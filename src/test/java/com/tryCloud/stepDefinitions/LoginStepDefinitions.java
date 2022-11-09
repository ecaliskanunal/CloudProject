package com.tryCloud.stepDefinitions;

import com.tryCloud.pages.LoginPage;
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
        Driver.getDriver().get(ConfigurationReader.getProperty("URL"));
    }

    @When("user types username {string}")
    public void user_types_username(String string) {
        loginPage.usernameInputBox.sendKeys(ConfigurationReader.getProperty("username"));
    }

    @When("user types password {string}")
    public void user_types_password(String string) {
        loginPage.passwordInputBox.sendKeys(ConfigurationReader.getProperty("password"));
    }


    @When("user enters invalid username {string}")
    public void userEntersInvalidUsername(String username) {
        loginPage.usernameInputBox.sendKeys(username);
    }

    @And("user enters invalid password {string}")
    public void userEntersInvalidPassword(String password) {
        loginPage.passwordInputBox.sendKeys(password);
    }

    @When("user enters missing credentials username {string}")
    public void userEntersMissingCredentialsUsername(String username) {
        loginPage.usernameInputBox.sendKeys(username);
    }

    @And("user enters missing credentials password {string}")
    public void userEntersMissingCredentialsPassword(String password) {
        loginPage.passwordInputBox.sendKeys(password);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        loginPage.loginButton.click();
    }

    @When("user hits enter key")
    public void user_hits_enter_key() {
        actions.click(loginPage.loginButton).sendKeys(Keys.ENTER).perform();
    }

    @Then("user can login")
    public void userCanLogin() {
        wait.until(ExpectedConditions.visibilityOf(loginPage.userImage));
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("dashboard"));
    }

    @Then("user cannot login")
    public void userCannotLogin() {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("login"));
    }

    @Then("user sees validation message {string}")
    public void user_sees_validation_message(String string) {
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
        //Assert.assertEquals(string, loginPage.errorMessage.getText());
    }

//    @Then("user sees reminder message {string}")
//    public void user_sees_reminder_message(String expectedReminderMessage) {
//        //wait.until(ExpectedConditions.visibilityOf(loginPage.reminderMessage));
//        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Please fill out this field.')]")));
//
//        JavascriptExecutor jse = ((JavascriptExecutor) Driver.getDriver());
//        jse.executeScript("arguments[0].scrollIntoView();", loginPage.reminderMessage);
//        Assert.assertEquals(expectedReminderMessage, loginPage.reminderMessage.getText());
//    }

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
        // wait.until(ExpectedConditions.visibilityOf(loginPage.resetInputBox));
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
