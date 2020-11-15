package com.base.useinsider.pages;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class ApplicationFormPage extends BasePage {

    private static final By SUBMIT_FORM_TEXT = By.xpath("//*[@id='application-form']/div[1]/h4");

    public ApplicationFormPage(WebDriver driver) {
        super(driver);
    }

    @SneakyThrows
    @Step
    public void checkThatUploadButtonIsPresentOnApplicationFormPage() {
        sleep(2000);
        assertThat(find(SUBMIT_FORM_TEXT).getText())
                .as("Text is incorrect!")
                .isEqualTo("SUBMIT YOUR APPLICATION");
        log.info("Text on the Application Form are correct!");
    }

}
