package com.base.useinsider.pages;

import com.base.useinsider.utils.Url;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.base.useinsider.utils.ConfigProperties.getTestProperty;
import static java.lang.Long.parseLong;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class MainPage extends BasePage {

    private static final By LOGO = By.xpath("//*[@id='logo']");

    WebDriverWait wait = new WebDriverWait(driver, parseLong(getTestProperty("EXPLICIT_WAIT")));

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @SneakyThrows
    @Step
    public MainPage openMainPageAndCheckTitleIsCorrect() {
        wait.until(ExpectedConditions.elementToBeClickable(LOGO));
        assertThat(find(LOGO).isDisplayed()).isTrue();
        assertThat(driver.getCurrentUrl()
                .replaceAll("https://", "")
                .replaceAll("/", ""))
                .as("Url is incorrect")
                .isEqualTo(getTestProperty("BASE_URL_UI"));
        log.info("Main Page is opened:");
        return this;
    }

    public static String baseUrl() {
        return new Url.UrlBuilder(getTestProperty("BASE_URL_UI"))
                .withHttps(true)
                .withResource("")
                .build()
                .getUrl();
    }

}
