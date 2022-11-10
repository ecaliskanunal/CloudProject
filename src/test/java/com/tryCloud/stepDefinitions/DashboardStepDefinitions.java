package com.tryCloud.stepDefinitions;

import com.tryCloud.pages.DashboardPage;
import com.tryCloud.pages.LoginPage;
import com.tryCloud.utilities.BrowserUtils;
import com.tryCloud.utilities.ConfigurationReader;
import com.tryCloud.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DashboardStepDefinitions {
    Actions actions = new Actions(Driver.getDriver());
    DashboardPage dashboardPage = new DashboardPage();

    LoginPage loginPage = new LoginPage();
    JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);


    @Given("user is logged in")
    public void user_is_logged_in() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.logIn();
    }

    @When("user hovers over a module {string}")
    public void user_hovers_over_a_module(String string) {

        String actualModuleLabel = "";
        for (WebElement module : dashboardPage.modules) {
            actualModuleLabel = module.getAttribute("aria-label");
            if (string.equals(actualModuleLabel)) {
                actions.moveToElement(module).perform();
                break;
            }
        }

    }

    @Then("user should be able to see the selected module {string}")
    public void user_should_be_able_to_see_the_selected_module(String string) {
        String actualModuleLabel = "";
        for (WebElement module : dashboardPage.modules) {
            actualModuleLabel = module.getText();
            if (actualModuleLabel.equals(string)) {
                wait.until(ExpectedConditions.elementToBeClickable(module));
                Assert.assertTrue(module.isDisplayed());
                break;
            }
        }
    }

    @When("user clicks on the module {string}")
    public void user_clicks_on_the_module(String string) {

        String actualModuleLabel = "";
        for (WebElement module : dashboardPage.modules) {
            actualModuleLabel = module.getText();
            if (actualModuleLabel.equals(string)) {
                wait.until(ExpectedConditions.elementToBeClickable(module));
                actions.click(module).perform();
                break;
            }
        }
    }


    @Then("user should be able to see the module page {string}")
    public void user_should_be_able_to_see_the_module_page(String string) {
        String actualModuleLabel = "";
        for (WebElement module : dashboardPage.modules) {
            actualModuleLabel = module.getText();
            if (actualModuleLabel.equalsIgnoreCase(string)) {
                wait.until(ExpectedConditions.titleContains(string));
                Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(string));
                break;
            }
        }
    }


    @When("user hovers over username image")
    public void user_hovers_over_username_image() {
        actions.moveToElement(dashboardPage.userImage).perform();
    }

    @And("user clicks username image")
    public void user_clicks_username_image() {
        actions.click(dashboardPage.userImage).perform();
    }

    @Then("user should be able to see username {string}")
    public void userShouldBeAbleToSee(String string) {
        Assert.assertTrue(dashboardPage.usernameOnUserImage.getText().equals(ConfigurationReader.getProperty(string)));
    }

    @When("user hovers over customize button")
    public void user_hovers_over_customize_button() {
        actions.moveToElement(dashboardPage.customizeButton).perform();
    }

    @When("user clicks on customize button")
    public void user_clicks_on_customize_button() {
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        jse.executeScript("arguments[0].scrollIntoView(true);", dashboardPage.customizeButton);
        actions.click(dashboardPage.customizeButton).perform();
    }

    @Then("user should be able to customize options")
    public void user_should_be_able_to_customize_options() {
        Assert.assertTrue(dashboardPage.customizePage.isDisplayed());
    }

    @Then("user should be able to see all status widgets following")
    public void user_should_be_able_to_see_all_status_widgets_following(List<String> widgetsListFromFeature) {
        List<String> actualWidgets = new ArrayList<>();
        for (int i = 0; i < dashboardPage.widgets.size(); i++) {
            actualWidgets.add(dashboardPage.widgets.get(i).getText());
        }
        Assert.assertTrue(widgetsListFromFeature.containsAll(actualWidgets));
    }

    @When("user clicks on all widgets")
    public void user_clicks_on_all_widgets() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div//li/input/following-sibling::label")));
        for (int i = 0; i < dashboardPage.widgets.size(); i++) {
            BrowserUtils.selectCheckBox(dashboardPage.widgets.get(i), true);
        }
    }

    @Then("user should be able to see all widgets selected")
    public void user_should_be_able_to_see_all_widgets_selected() {
        for (int i = 0; i < dashboardPage.widgetCheckboxes.size(); i++) {
            if (dashboardPage.widgetCheckboxes.get(i).isSelected()) {
                Assert.assertTrue(dashboardPage.widgetCheckboxes.get(i).isSelected());
                if (!(dashboardPage.widgetCheckboxes.get(i).isSelected())) {
                    Assert.assertFalse(dashboardPage.widgetCheckboxes.get(i).isSelected());
                    break;
                }
            }

        }
    }

    @When("user clicks on all widgets to deselect")
    public void user_clicks_on_all_widgets_to_deselect() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div//li/input/following-sibling::label")));
        for (int i = 0; i < dashboardPage.widgets.size(); i++) {
            BrowserUtils.selectCheckBox(dashboardPage.widgets.get(i), true);
        }
    }

    @Then("user should be able to see all widgets deselected")
    public void user_should_be_able_to_see_all_widgets_deselected() {
        for (int i = 0; i < dashboardPage.widgetCheckboxes.size(); i++) {
            if (dashboardPage.widgetCheckboxes.get(i).isSelected()) {
                Assert.assertTrue(dashboardPage.widgetCheckboxes.get(i).isSelected());
                if (!(dashboardPage.widgetCheckboxes.get(i).isSelected())) {
                    Assert.assertFalse(dashboardPage.widgetCheckboxes.get(i).isSelected());
                    break;
                }
            }
        }
    }

    @When("user scrolls down on customize page")
    public void user_scrolls_down_on_customize_page() {
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        jse.executeScript("arguments[0].scrollIntoView(true);", dashboardPage.lastImage);
    }

    @Then("user should be able to see background images")
    public void user_should_be_able_to_see_background_images() {
        for (int i = 0; i < dashboardPage.images.size(); i++) {
            if (dashboardPage.images.get(i).isDisplayed()) {
                Assert.assertTrue(dashboardPage.images.get(i).isDisplayed());
                if (!(dashboardPage.images.get(i).isDisplayed())) {
                    Assert.assertFalse(dashboardPage.images.get(i).isDisplayed());
                    break;
                }
            }
        }
    }

    @Then("user should be able to select any background image")
    public void user_should_be_able_to_select_any_background_image(List<Integer> numbersFromFeature) {
        for (int i = 1; i <= dashboardPage.images.size(); i++) {
            for (Integer integer : numbersFromFeature) {
                if (integer == i) {
                    dashboardPage.images.get(i).click();
                    wait.until(ExpectedConditions.attributeContains(dashboardPage.images.get(i), "class", "background active"));
                    String classAttValueOfSelectedImage = dashboardPage.images.get(i).getAttribute("class");
                    String expectedAttValueOfSelectedImage = "background active";
                    Assert.assertEquals(classAttValueOfSelectedImage, expectedAttValueOfSelectedImage);
                    if (!classAttValueOfSelectedImage.equals(expectedAttValueOfSelectedImage)) {
                        Assert.assertFalse(classAttValueOfSelectedImage.equals(expectedAttValueOfSelectedImage));
                    }
                }
            }
        }
    }

    @When("user clicks on set status button on dashboard")
    public void user_clicks_on_set_status_button_on_dashboard() {
        dashboardPage.statusButtonOnDashboard.click();
    }

    @When("user clicks on a status {string}")
    public void user_clicks_on_a_status(String string) {
        for (WebElement status : dashboardPage.statusOptions) {
            if (status.getText().contains(string)) {
                status.click();
            }
        }
    }

    @When("user clicks on set status message button")
    public void user_clicks_on_set_status_message_button() {
        actions.click(dashboardPage.setStatusButton).perform();
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("user should be able to see the selected status message on dashboard {string}")
    public void user_should_be_able_to_see_the_selected_status_message_on_dashboard(String string) {
        dashboardPage.clearStatusButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(dashboardPage.statusButtonOnDashboard, string));
        Assert.assertTrue(dashboardPage.statusButtonOnDashboard.getText().contains(string));
    }

    @When("user chooses a status message {string}")
    public void user_chooses_a_status_message(String string) {
        for (WebElement status : dashboardPage.statusMessageList) {
            if (status.getText().equals(string)) {
                status.click();
            }
        }
    }

    @Then("Then user should be able to see the selected message on dashboard {string}")
    public void then_user_should_be_able_to_see_the_selected_message_on_dashboard(String string) {
        wait.until(ExpectedConditions.textToBePresentInElement(dashboardPage.statusButtonOnDashboard, string));
        Assert.assertTrue(dashboardPage.statusButtonOnDashboard.getText().contains(string));
    }

    @When("user clicks on clear status button")
    public void user_clicks_on_clear_status_button() {
        dashboardPage.clearStatusButton.click();
    }

    @Then("user should be able to see status message cleared from dashboard")
    public void user_should_be_able_to_see_status_message_cleared_from_dashboard(List<String> messagesFromFeature) {
        jse.executeScript("arguments[0].scrollIntoView(true);", dashboardPage.greetingMessageOnTop);
        jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        Driver.getDriver().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        for (String message : messagesFromFeature) {
            Assert.assertFalse(dashboardPage.statusButtonOnDashboard.getText().contains(message));
            if (dashboardPage.statusButtonOnDashboard.getText().contains(message)) {
                Assert.assertTrue(dashboardPage.statusButtonOnDashboard.getText().contains(message));
                break;
            }
        }
    }
}