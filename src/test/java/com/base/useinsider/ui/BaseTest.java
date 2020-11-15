package com.base.useinsider.ui;

import com.base.useinsider.core.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.util.Arrays;

import static com.base.useinsider.utils.ConfigProperties.getTestProperty;
import static java.lang.String.valueOf;

@Slf4j
public abstract class BaseTest {

    protected WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    public void setup() {
        driver = WebDriverManager.initDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        log.info("Quitting browser");
    }

    @AfterMethod
    @SneakyThrows
    public void takeScreenShotOnFailure(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            log.info(valueOf(testResult.getStatus()));
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile,
                    new File(getTestProperty("PATH_TO_SCREENSHOT_FOR_FAILED_TESTS") + testResult.getName() + "-"
                            + Arrays.toString(testResult.getParameters()) + ".jpg"));
        }
    }

}
