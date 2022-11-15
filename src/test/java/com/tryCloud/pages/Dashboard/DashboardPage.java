package com.tryCloud.pages.Dashboard;

import com.tryCloud.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage {
    public DashboardPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@id='appmenu']/li[@tabindex='-1']/a")
    public List<WebElement> modules;

//    @FindBy(xpath = "//*[@id='appmenu']/li[1]")
//    public WebElement dashboardModule;
//
//    @FindBy(xpath = "//*[@id='appmenu']/li[2]")
//    public WebElement filesModule;
//
//    @FindBy(xpath = "//*[@id='appmenu']/li[3]")
//    public WebElement photosModule;
//
//    @FindBy(xpath = "//*[@id='appmenu']/li[4]")
//    public WebElement activityModule;
//
//    @FindBy(xpath = "//*[@id='appmenu']/li[5]")
//    public WebElement talkModule;
//
//    @FindBy(xpath = "//*[@id='appmenu']/li[6]")
//    public WebElement mailModule;
//
//    @FindBy(xpath = "//*[@id='appmenu']/li[7]")
//    public WebElement contactsModule;
//
//    @FindBy(xpath = "//*[@id='appmenu']/li[8]")
//    public WebElement circlesModule;

    @FindBy(xpath = "//*[@id='expand']/div[1]/img")
    public WebElement userImage;

    @FindBy(xpath = "(//*[@id=\"expanddiv\"]//span)[1]")
    public WebElement usernameOnUserImage;

//    @FindBy(xpath = "//*[@id='appmenu']/li[9]")
//    public WebElement calenderModule;
//
//    @FindBy(xpath = "//*[@id='appmenu']/li[10]")
//    public WebElement deckModule;

    @FindBy(xpath = "//*[@id=\"status-status\"]/li/div/button")
    public WebElement statusButtonOnDashboard;

    @FindBy(xpath = "//*[@id=\"app-dashboard\"]/h2")
    public WebElement greetingMessageOnTop;

    @FindBy(xpath = "//*[@id=\"body-user\"]/div[6]/div[2]/div/div/div[2]/div")
    public List<WebElement> statusOptions;

//    @FindBy(xpath = "//input[@id='user-status-online-status-online']")
//    public WebElement onlineStatus;
//
//    @FindBy(xpath = "//input[@id='user-status-online-status-away']")
//    public WebElement awayStatus;
//
//    @FindBy(xpath = "//input[@id='user-status-online-status-dnd']")
//    public WebElement doNotDisturbStatus;
//
//    @FindBy(xpath = "//input[@id='user-status-online-status-invisible']")
//    public WebElement invisibleStatus;
//
//    @FindBy(xpath = "//input[@placeholder=\"What's your status?\"]")
//    public WebElement statusMessageBox;

    @FindBy(xpath = "//*[@id=\"body-user\"]/div[6]/div[2]/div/div/div[5]/div/span[2]")
    public List<WebElement> statusMessageList;

    @FindBy(xpath = "//div[@class='status-buttons']/button[1]")
    public WebElement clearStatusButton;

    @FindBy(xpath = "//div[@class='status-buttons']/button[2]")
    public WebElement setStatusButton;

    @FindBy(xpath = "//*[@id=\"app-dashboard\"]/div[2]/a")
    public WebElement customizeButton;

    @FindBy(xpath = "//div[@class='modal-container']")
    public WebElement customizePage;

    @FindBy(xpath = "//div//li/input")
    public List<WebElement> widgetCheckboxes;

    @FindBy(xpath = "//div//li/input/following-sibling::label")
    public List<WebElement> widgets;

    @FindBy(xpath = "//*[@id='status-weather']")
    public WebElement weatherWidgetOnDashboard;

    @FindBy(xpath = "//button[contains (@style, \"background-image\")]")
    public List<WebElement> images;

    @FindBy(xpath = "//*[@id=\"body-user\"]/div[6]/div[2]/div/div/div/button[18]")
    public WebElement lastBackgroundImage;

    @FindBy (xpath = "//input[@class=\"action-input__input focusable\"]")
    public WebElement locationInputBox;

    @FindBy(xpath="//p[@class=\"action-link__longtext\"]")
    public WebElement weatherInfo;





}
