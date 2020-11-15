package com.base.useinsider.ui;

import com.base.useinsider.pages.CareerPage;
import com.base.useinsider.pages.MainPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class InsiderUiTests extends BaseTest {

    @Test(description = "Check Main page Title", priority = 1)
    @Severity(SeverityLevel.NORMAL)
    @Story("UI Tests for the useinsider.com project")
    public void checkMainPageTitle() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPageAndCheckTitleIsCorrect();
    }

    @Test(description = "Check tabs on the Career Page", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Story("UI Tests for the useinsider.com project")
    public void checkTabsOnCareerPage() {
        CareerPage careerPage = new CareerPage(driver);
        careerPage
                .openCareerPageAndCheckTitleIsCorrect()
                .clickOnCultureTabAndMakeAssertions()
                .clickOnLocationsTabAndMakeAssertions()
                .clickOnTeamsTabAndMakeAssertions()
                .clickOnCareerOpportunitiesAndMakeAssertions()
                .clickOnLifeAtInsiderAndMakeAssertions();
    }

    @Test(description = "Check Filter on the Opportunities tab and Job description", priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Story("UI Tests for the useinsider.com project")
    public void checkFilterOnCareerOpportunitiesTab() {
        CareerPage careerPage = new CareerPage(driver);
        careerPage
                .openCareerPageAndCheckTitleIsCorrect()
                .clickOnCareerOpportunitiesAndMakeAssertions()
                .selectFilterByCountryAndDepartmentAndMakeAssertions("Istanbul, Turkey", "Quality Assurance")
                .openPositionAndMakeChecks()
                .clickApplyButtonOnPositionPage()
                .checkThatUploadButtonIsPresentOnApplicationFormPage();
    }

}
