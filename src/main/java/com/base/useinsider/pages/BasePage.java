package com.base.useinsider.pages;

import com.base.useinsider.core.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;

import java.util.List;

@Slf4j
public class BasePage extends WebDriverManager {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement find(By locator) {
        WebElement element = driver.findElement(locator);
        return element;
    }

    protected List<WebElement> findAll(By locator) {
        List<WebElement> allWebElements = driver.findElements(locator);
        log.info("Found next count of WebElements: " + allWebElements.size());
        return allWebElements;
    }

    protected void click(By locator) {
        try {
            find(locator).click();
        } catch (WebDriverException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", find(locator));
        } finally {
            log.info("Click using next locator: {}", locator);
        }
    }

    protected String getCurrentPageTitle() {
        return driver.getTitle();
    }

}