package com.base.useinsider.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static com.base.useinsider.pages.CareerPage.APPLY_POSITION_BUTTON;


@Slf4j
public class PositionDescriptionPage extends BasePage {

    public PositionDescriptionPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public ApplicationFormPage clickApplyButtonOnPositionPage() {
        find(APPLY_POSITION_BUTTON).click();
        log.info("Click on Apply position button is successful");
        return new ApplicationFormPage(driver);
    }


}
