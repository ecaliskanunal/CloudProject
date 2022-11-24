package com.tryCloud.stepDefinitions;

import com.tryCloud.pages.Dashboard.DashboardPage;
import com.tryCloud.pages.LoginPage;
import com.tryCloud.utilities.BrowserUtils;
import com.tryCloud.utilities.ConfigurationReader;
import com.tryCloud.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
        loginPage.logIn();
    }

    @When("user hovers over a {string} button on top left")
    public void user_hovers_over_a_module_button_on_top_left(String string) {
        actions.moveToElement(dashboardPage.getLocatorForOneModule(string)).perform();

        // If you want to use the List<WebElement> modules from DashboardPage, the loop below will do fine.
        // String actualModuleLabel;
        // for (WebElement module : dashboardPage.modules) {
        //    actualModuleLabel = module.getAttribute("aria-label");
        //    if (string.equals(actualModuleLabel)) {
        //        actions.moveToElement(module).perform();
        //        break;
        //    }
        // }
    }

    @Then("user should be able to see the {string} name highlighted")
    public void user_should_be_able_to_see_the_module_name_highlighted(String string) {
        String actualModuleLabel;
        for (WebElement module : dashboardPage.modules) {
            actualModuleLabel = module.getText();
            if (actualModuleLabel.equals(string)) {
                wait.until(ExpectedConditions.elementToBeClickable(module));
                Assert.assertTrue(module.isDisplayed());
                break;
            }
        }
    }

    @When("user hovers over username image on top right menu")
    public void user_hovers_over_username_image_on_top_right_menu() {
        actions.moveToElement(dashboardPage.userImage).perform();
    }

    @And("user clicks on username image")
    public void user_clicks_on_username_image() {
        actions.click(dashboardPage.userImage).perform();
    }

    @Then("user should be able to see his full username displayed on top of the menu")
    public void user_should_be_able_to_see_his_full_username_displayed_on_top_of_the_menu() {
        Assert.assertEquals(dashboardPage.usernameOnUserImage.getText(), ConfigurationReader.getProperty("username"));
    }

    @When("user clicks on the {string}")
    public void user_clicks_on_the_module(String string) {
        dashboardPage.clickOnListElement(dashboardPage.modules, string);

        // If you don't want to use this custom method, the loop below will do fine
        // String actualModuleLabel = "";
        // for (WebElement module : dashboardPage.modules) {
        //    actualModuleLabel = module.getText();
        //    if (actualModuleLabel.equals(string)) {
        //        wait.until(ExpectedConditions.elementToBeClickable(module));
        //        actions.click(module).perform();
        //        break;
        //    }
        // }
    }

    @Then("user should be able to see the {string} page")
    public void user_should_be_able_to_see_the_module_page(String string) {
        String actualModuleLabel;
        for (WebElement module : dashboardPage.modules) {
            actualModuleLabel = module.getText();
            if (actualModuleLabel.equalsIgnoreCase(string)) {
                wait.until(ExpectedConditions.titleContains(string));
                Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(string));
                break;
            }
        }
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

    @Then("user should be able to see the customize page")
    public void user_should_be_able_to_see_the_customize_page() {
        Assert.assertTrue(dashboardPage.customizePage.isDisplayed());
    }

    @Then("user should be able to see all status widgets on customize page")
    public void user_should_be_able_to_see_all_status_widgets_on_customize_page(List<String> widgetsListFromFeature) {
        List<String> actualWidgets = new ArrayList<>();
        for (int i = 0; i < dashboardPage.widgets.size(); i++) {
            actualWidgets.add(dashboardPage.widgets.get(i).getText());
        }
        Assert.assertTrue(widgetsListFromFeature.containsAll(actualWidgets));
    }


    @When("user clicks on all widgets one by one to select")
    public void user_clicks_on_all_widgets_one_by_one_to_select() {
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div//li/input/following-sibling::label")));
        for (int i = 0; i < dashboardPage.widgets.size(); i++) {
            if (!dashboardPage.widgetCheckboxes.get(i).isSelected()) {
                actions.click(dashboardPage.widgets.get(i)).perform();
            }
        }
    }

    @Then("user should be able to see all widgets selected")
    public void user_should_be_able_to_see_all_widgets_selected() {
        for (int i = 0; i < dashboardPage.widgetCheckboxes.size(); i++) {
            Assert.assertTrue("WARNING: Widget not selected", dashboardPage.widgetCheckboxes.get(i).isSelected());
        }
    }

    @When("user clicks on all widgets one by one to deselect")
    public void user_clicks_on_all_widgets_one_by_one_to_deselect() {
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div//li/input/following-sibling::label")));
        ArrayList<WebElement> widgetArray = new ArrayList<>(dashboardPage.widgets);
        for (int i = dashboardPage.widgets.size()-1; i>=0; i--) {
            if (dashboardPage.widgetCheckboxes.get(i).isSelected()) {
                actions.click(widgetArray.get(i)).perform();
                BrowserUtils.sleep(1);
            }
        }
    }


    @Then("user should be able to see all widgets deselected")
    public void user_should_be_able_to_see_all_widgets_deselected() {
        for (WebElement widget : dashboardPage.widgetCheckboxes) {
            Assert.assertFalse("WARNING: Widget not deselected", widget.isSelected());
        }
    }

    @When("user scrolls down on customize page")
    public void user_scrolls_down_on_customize_page() {
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        jse.executeScript("arguments[0].scrollIntoView(true);", dashboardPage.lastBackgroundImage);
    }

    @Then("user should be able to see background images")
    public void user_should_be_able_to_see_background_images() {
        for (int i = 0; i < dashboardPage.images.size(); i++) {
            Assert.assertTrue("WARNING: Image is not displayed", dashboardPage.images.get(i).isDisplayed());
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
                }
            }
        }
    }

    @When("user clicks on set status button on dashboard")
    public void user_clicks_on_set_status_button_on_dashboard() {
        user_clicks_on_customize_button();
        if(!(dashboardPage.widgetCheckboxes.get(0).isSelected())){
            actions.click(dashboardPage.getLocatorForOneWidget("status")).perform();
        }
        //wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.statusButtonOnDashboard));
        BrowserUtils.sleep(3);
        actions.click(dashboardPage.greetingMessageOnTop).perform();
        //wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.statusButtonOnDashboard));
        BrowserUtils.sleep(3);
        actions.click(dashboardPage.statusButtonOnDashboard).perform();
    }

    @When("user clicks on a {string}")
    public void user_clicks_on_a_status(String string) {
        //wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.getLocatorForOneStatus(string)));
        BrowserUtils.sleep(3);
        actions.click(dashboardPage.getLocatorForOneStatus(string)).perform();

        // If you want to use the List<WebElement> statusOptions from DashboardPage, the loop below will do fine.
        // for (WebElement status : dashboardPage.statusOptions) {
        //    if (status.getText().contains(string)) {
        //        status.click();
        //    }
        // }
    }

    @When("user clicks on set status message button on the new opening page")
    public void user_clicks_on_set_status_message_button_on_the_new_opening_page() {
        actions.click(dashboardPage.setStatusButton).perform();
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("user should be able to see the selected {string} message on dashboard")
    public void user_should_be_able_to_see_the_selected_status_message_on_dashboard(String string) {
        dashboardPage.clearStatusButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(dashboardPage.statusButtonOnDashboard, string));
        Assert.assertTrue(dashboardPage.statusButtonOnDashboard.getText().contains(string));
    }

    @When("user chooses a {string}")
    public void user_chooses_a_status_message(String string) {
        for (WebElement status : dashboardPage.statusMessageList) {
            if (status.getText().equals(string)) {
                status.click();
            }
        }
    }

    @Then("Then user should be able to see the selected {string} on dashboard")
    public void then_user_should_be_able_to_see_the_selected_message_on_dashboard(String string) {
        wait.until(ExpectedConditions.textToBePresentInElement(dashboardPage.statusButtonOnDashboard, string));
        Assert.assertTrue(dashboardPage.statusButtonOnDashboard.getText().contains(string));
    }

    @When("user clicks on clear status button on the new opening page")
    public void user_clicks_on_clear_status_button_on_the_new_opening_page() {
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

    @When("user clicks on weather widget on dashboard")
    public void user_clicks_on_weather_widget_on_dashboard() {

        user_clicks_on_customize_button();
        if(!(dashboardPage.widgetCheckboxes.get(1).isSelected())){
            actions.click(dashboardPage.getLocatorForOneWidget("weather")).perform();
        }
        actions.click(dashboardPage.greetingMessageOnTop).perform();
        //wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.weatherWidgetOnDashboard));
        BrowserUtils.sleep(3);
        actions.click(dashboardPage.weatherWidgetOnDashboard).perform();
    }

    @When("user clicks on address box")
    public void user_clicks_on_address_box() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.locationInputBox));
        //BrowserUtils.sleep(3);
        actions.moveToElement(dashboardPage.locationInputBox).click().perform();
    }

    @When("user types a {string} in address box")
    public void user_types_a_in_address_box(String string) {
        actions.sendKeys(string + Keys.ENTER).perform();
    }

    @Then("user should be able to see the weather for the {string} typed on dashboard")
    public void user_should_be_able_to_see_the_weather_for_the_place_typed_on_dashboard(String string) {
        actions.moveToElement(dashboardPage.weatherInfo).perform();
        wait.until(ExpectedConditions.textToBePresentInElement(dashboardPage.weatherInfo, string));
        Assert.assertTrue(dashboardPage.weatherInfo.getText().contains(string));
    }
}