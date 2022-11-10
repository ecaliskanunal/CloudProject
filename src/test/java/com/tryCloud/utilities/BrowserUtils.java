package com.tryCloud.utilities;

import org.openqa.selenium.WebElement;

import java.util.List;

public class BrowserUtils {
    public static void deselectAllCheckBoxes(List<WebElement> list){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelected()) {
                list.get(i).click();
            }
        }
    }

    public static void selectCheckBox(WebElement element){
        if (!element.isSelected()) {
            element.click();
        }
    }


    public static void selectCheckBox(WebElement element, boolean check) {
        if (check) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            if (element.isSelected()) {
                element.click();
            }
        }
    }
}
