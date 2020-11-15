package com.base.useinsider.core;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static com.base.useinsider.pages.MainPage.baseUrl;
import static com.base.useinsider.utils.ConfigProperties.getTestProperty;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static java.lang.Long.parseLong;

@Slf4j
public class WebDriverManager {

    public static WebDriver driver = null;

    public static WebDriver initDriver() {
        String browserName = getTestProperty("BROWSER");
        if (browserName.equalsIgnoreCase("chrome")) {
            chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(parseLong(getTestProperty("IMPLICIT_WAIT")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(baseUrl());
        return driver;
    }

}
