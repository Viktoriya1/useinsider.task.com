package com.base.useinsider.pages;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.base.useinsider.utils.ConfigProperties.getTestProperty;
import static java.lang.Long.parseLong;
import static java.lang.Thread.sleep;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class CareerPage extends BasePage {

    private static final By CAREER_COLUMN = By.xpath("//*[@id='menu-item-21643']");
    private static final By CULTURE_COLUMN = By.xpath("//*[@href='#culture']");
    private static final By LOCATIONS_COLUMN = By.xpath("//*[@href='#locations']");
    private static final By TEAMS_COLUMN = By.xpath("//*[@href='#teams']");
    private static final By JOBS_COLUMN = By.xpath("//*[@href='#jobs']");
    private static final By LIFE_AT_INSIDER_COLUMN = By.xpath("//*[@href='#life-at-insider']");
    private static final By DROP_DOWN_FILTER_BY_COUNTRY = By.xpath("//*[@class='jobs-locations']");
    private static final By DROP_DOWN_FILTER_BY_TEAMS = By.xpath("//*[@class='jobs-teams']");
    private static final By POSITIONS = By.xpath("//*[@class='jobs-list']/a");
    private static final By TEXT_ON_POSITIONS = By.xpath("//*[@class='jobs-list']/a/span");
    private static final By LOCATIONS_TEXT = By.xpath("//*[@id='locations']/div[2]/div/div/div/div/div/p");
    private static final By TEAM_LINKS = By.xpath("//a[@href and @class='column-link']");
    private static final By JOBS_TEXT = By.xpath("//*[@id='jobs']/div[2]/div/div/div/div/div/p");
    private static final By LIFE_AT_INSIDER_BLOCKS = By.xpath("//*[@id='sbi_images']/div");
    private static final By POSITION_NAME = By.xpath("//*[@class='posting-headline']/h2");
    static final By APPLY_POSITION_BUTTON = By.xpath("//*[text()='Apply for this job']");

    private static final String CAREER_TITLE = "Career - Insider";
    private static final String POSITIONS_TITLE = "Insider. - Quality Assurance Analyst";

    WebDriverWait wait = new WebDriverWait(driver, parseLong(getTestProperty("EXPLICIT_WAIT")));

    public CareerPage(WebDriver driver) {
        super(driver);
    }

    @SneakyThrows
    @Step
    public CareerPage openCareerPageAndCheckTitleIsCorrect() {
        wait.until(ExpectedConditions.elementToBeClickable(CAREER_COLUMN)).click();
        click(CAREER_COLUMN);
        assertThat(getCurrentPageTitle()).as("Title is incorrect!").isEqualTo(CAREER_TITLE);
        log.info("Career Page is opened and Title is correct: {}", CAREER_TITLE);
        return this;
    }

    @Step
    public CareerPage clickOnCultureTabAndMakeAssertions() {
        wait.until(ExpectedConditions.elementToBeClickable(CULTURE_COLUMN)).click();
        List<WebElement> images = findAll(By.xpath("//*[@class='grid-container']/div/img"));
        assertThat(images.size()).as("Some pictures are missing").isEqualTo(10);
        log.info("All required pictures are present");
        return this;
    }

    @Step
    public CareerPage clickOnLocationsTabAndMakeAssertions() {
        String expectedText = "We are more than 500 Insiders around the world, spanning 20 offices and crossing 4 continents.";
        wait.until(ExpectedConditions.elementToBeClickable(LOCATIONS_COLUMN));
        click(LOCATIONS_COLUMN);
        wait.until(ExpectedConditions.visibilityOf(find(LOCATIONS_TEXT)));
        assertThat(find(LOCATIONS_TEXT).getText()).as("Text is wrong").isEqualTo(expectedText);
        log.info("Text under 'Our Locations' tab is displayed correctly");
        return this;
    }

    @Step
    public CareerPage clickOnTeamsTabAndMakeAssertions() {
        wait.until(ExpectedConditions.elementToBeClickable(TEAMS_COLUMN));
        click(TEAMS_COLUMN);
        List<WebElement> teamLinks = findAll(TEAM_LINKS);
        assertThat(teamLinks.size()).as("Some link is missing").isEqualTo(10);
        teamLinks.forEach(link -> assertThat(link.isDisplayed()).as("Some href is missing").isTrue());
        log.info("All Links on the TEAM tab are correct");
        return this;
    }

    @Step
    public CareerPage clickOnCareerOpportunitiesAndMakeAssertions() {
        String expectedText = "Become one of us if you want to create an impact in the lives of marketers and shape the future of " +
                "customer experience delivery by democratizing predictive, Machine Learning and AI technologies.";
        wait.until(ExpectedConditions.elementToBeClickable(JOBS_COLUMN));
        click(JOBS_COLUMN);
        wait.until(ExpectedConditions.visibilityOf(find(JOBS_TEXT)));
        assertThat(find(JOBS_TEXT).getText()).as("Text has some discrepancies").isEqualTo(expectedText);
        log.info("Text under 'Career Opportunities' tab is displayed correctly");
        return this;
    }

    @Step
    public CareerPage clickOnLifeAtInsiderAndMakeAssertions() {
        wait.until(ExpectedConditions.elementToBeClickable(LIFE_AT_INSIDER_COLUMN));
        click(LIFE_AT_INSIDER_COLUMN);
        List<WebElement> lifeAtInsiderBlocks = findAll(LIFE_AT_INSIDER_BLOCKS);
        assertThat(lifeAtInsiderBlocks.size()).as("Some href is missing").isEqualTo(8);
        lifeAtInsiderBlocks.forEach(link -> assertThat(link.isDisplayed()).as("Some href is missing").isTrue());
        log.info("All Links on the LIFE AT INSIDER tab are correct");
        return this;
    }

    @SneakyThrows
    @Step
    public CareerPage selectFilterByCountryAndDepartmentAndMakeAssertions(String region, String department) {
        Select regionDropDown = new Select(find(DROP_DOWN_FILTER_BY_COUNTRY));
        regionDropDown.selectByVisibleText(region);
        sleep(2000);
        Select departmentDropDown = new Select(find(DROP_DOWN_FILTER_BY_TEAMS));
        departmentDropDown.selectByVisibleText(department);
        sleep(3000);
        List<WebElement> positions = findAll(POSITIONS);
        assertThat(positions.size()).as("Count of positions is incorrect").isEqualTo(5);
        log.info("All positions after using filters works correctly");
        List<WebElement> textOnPositions = findAll(TEXT_ON_POSITIONS);
        List<WebElement> filteredRegion = textOnPositions.stream()
                .filter(tag -> tag.getText().equals(region)).collect(toList());
        List<WebElement> filteredDepartment = textOnPositions.stream()
                .filter(tag -> tag.getText().equals(department)).collect(toList());
        assertThat(filteredRegion.size()).as("Text in positions is incorrect").isEqualTo(filteredDepartment.size());
        return this;
    }

    @SneakyThrows
    @Step
    public PositionDescriptionPage openPositionAndMakeChecks() {
        List<WebElement> positions = findAll(POSITIONS);
        positions.stream().findFirst().get().click();
        wait.until(ExpectedConditions.visibilityOf(find(POSITION_NAME)));
        assertThat(getCurrentPageTitle()).as("Title is incorrect!").isEqualTo(POSITIONS_TITLE);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(find(POSITION_NAME).getText(), "Quality Assurance Analyst");
        softAssert.assertEquals(findAll(By.xpath("//*[@class='posting-categories']/div")).size(), 3);
        softAssert.assertTrue(find(By.xpath("//*[@class='posting-categories']/div[1]")).getText().contains("ISTANBUL, TURKEY /"));
        softAssert.assertTrue(find(By.xpath("//*[@class='posting-categories']/div[2]")).getText().contains("QUALITY ASSURANCE /"));
        softAssert.assertTrue(find(By.xpath("//*[text()='Requirements;']")).isDisplayed());
        softAssert.assertEquals(findAll(APPLY_POSITION_BUTTON).size(), 2);
        softAssert.assertAll();
        log.info("All checks on position page are correct");
        return new PositionDescriptionPage(driver);
    }

}
