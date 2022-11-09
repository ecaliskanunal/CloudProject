package com.tryCloud.pages;

import com.tryCloud.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {
    public LogoutPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //@FindBy (xpath="//*[@id='expand']/div[1]/img")

    @FindBy (xpath="//*[@id='expand']/div[1]/img")
    public WebElement userImage;


    @FindBy (xpath="//*[@id='expanddiv']/ul/li[4]/a")
    public WebElement logoutOption;

}
