package com.tryCloud.pages.Dashboard;

import com.tryCloud.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage {
    public DashboardPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//li[@tabindex='-1']/a")
    public List<WebElement> modules;

    public WebElement getLocatorForOneModule(String moduleName) {
        int i = 0;
        switch (moduleName.toLowerCase()) {
            case "dashboard":
                i = 1;
                break;
            case "files":
                i = 2;
                break;
            case "photos":
                i = 3;
                break;
            case "activity":
                i = 4;
                break;
            case "talk":
                i = 5;
                break;
            case "mail":
                i = 6;
                break;
            case "contacts":
                i = 7;
                break;
            case "circles":
                i = 8;
                break;
            case "calender":
                i = 9;
                break;
            case "deck":
                i = 10;
                break;
        }
        String xpath = "//*[@id='appmenu']/li[" + i + "]/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    @FindBy(xpath = "//*[@id='expand']//img")
    public WebElement userImage;

    @FindBy(xpath = "(//*[@id='expanddiv']//span)[1]")
    public WebElement usernameOnUserImage;

    @FindBy(xpath = "//*[@id='status-status']//button")
    public WebElement statusButtonOnDashboard;

    @FindBy(xpath = "//*[@id='app-dashboard']/h2")
    public WebElement greetingMessageOnTop;

    // @FindBy(css = "div[class='user-status-online-select']")
    // public List<WebElement> statusOptions;

    public WebElement getLocatorForOneStatus(String statusName) {
        String statusNameToAdd = "";
        switch (statusName.toLowerCase()) {
            case "online":
                statusNameToAdd = "online";
                break;
            case "away":
                statusNameToAdd = "away";
                break;
            case "do not disturb":
                statusNameToAdd = "dnd";
                break;
            case "invisible":
                statusNameToAdd = "invisible";
                break;
        }
        String xpath = "//input[@id='user-status-online-status-" + statusNameToAdd + "']//parent::div";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    @FindBy(xpath = "//span[@class='predefined-status__message']")
    public List<WebElement> statusMessageList;

    @FindBy(xpath = "//div[@class='status-buttons']/button[1]")
    public WebElement clearStatusButton;

    @FindBy(xpath = "//div[@class='status-buttons']/button[2]")
    public WebElement setStatusButton;

    @FindBy(xpath = "//*[@id='app-dashboard']/div[2]/a")
    public WebElement customizeButton;

    @FindBy(xpath = "//div[@class='modal-container']")
    public WebElement customizePage;

    @FindBy(xpath = "//div//li/input")
    public List<WebElement> widgetCheckboxes;

    @FindBy(xpath = "//div//li/input/following-sibling::label")
    public List<WebElement> widgets;

    @FindBy(xpath = "//*[@id='status-weather']")
    public WebElement weatherWidgetOnDashboard;

    public WebElement getLocatorForOneWidget(String widgetName) {
        String widgetNameToAdd = "";
        switch (widgetName.toLowerCase()) {
            case "status":
                widgetNameToAdd = "status-checkbox-status";
                break;
            case "weather":
                widgetNameToAdd = "status-checkbox-weather";
                break;
            case "upcoming events":
                widgetNameToAdd = "panel-checkbox-calendar";
                break;
            case "upcoming cards":
                widgetNameToAdd = "panel-checkbox-deck";
                break;
            case "important mail":
                widgetNameToAdd = "panel-checkbox-mail";
                break;
            case "unread mail":
                widgetNameToAdd = "panel-checkbox-mail-unread";
                break;
            case "recommended files":
                widgetNameToAdd = "panel-checkbox-recommendations";
                break;
            case "talk mentions":
                widgetNameToAdd = "panel-checkbox-spreed";
                break;
            case "recent statuses":
                widgetNameToAdd = "panel-checkbox-user_status";
                break;
        }
        String xpath = "//label[@for='" + widgetNameToAdd + "']";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    @FindBy(xpath = "//button[contains (@style, 'background-image')]")
    public List<WebElement> images;

    @FindBy(xpath = "//button[18]")
    public WebElement lastBackgroundImage;

    @FindBy(xpath = "//input[@class='action-input__input focusable']")
    public WebElement locationInputBox;

    @FindBy(xpath = "//p[@class='action-link__longtext']")
    public WebElement weatherInfo;

    public void clickOnListElement(List<WebElement> list, String expectedElementText) {
        for (WebElement element : list) {
            if (element.getText().contains(expectedElementText)) {
                element.click();
                break;
            }
        }
    }
}
