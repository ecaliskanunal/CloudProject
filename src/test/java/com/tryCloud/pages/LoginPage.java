package com.tryCloud.pages;

import com.tryCloud.utilities.ConfigurationReader;
import com.tryCloud.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//*[@id='user']")
    public WebElement usernameInputBox;

    @FindBy (xpath = "//*[@id='password']")
    public WebElement passwordInputBox;

    @FindBy (xpath = "//*[@id='submit-form']")
    public WebElement loginButton;

    @FindBy (css = "#lost-password")
    public WebElement forgotPasswordLink;

    @FindBy (xpath = "//*[@id='reset-password-submit']")
    //@FindBy (css = "#reset-password-submit")
    public WebElement resetPasswordLink;

    @FindBy (xpath = "//*[@id='expand']//img")
    public WebElement userImage;

    @FindBy (xpath = "//*[@id='user']")
    public WebElement resetInputBox;

    @FindBy (xpath = "//div//p[3]")
    public WebElement errorMessage;

    @FindBy (xpath = "//*[@id=\"body-login\"]/div[1]//div/form/fieldset/p")
    public WebElement validationBody;


    @FindBy (xpath = "//form//p[2]/a")
    public WebElement togglePasswordButton;

    public  void logIn(){
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        usernameInputBox.sendKeys(ConfigurationReader.getProperty("username"));
        passwordInputBox.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
    }




}
