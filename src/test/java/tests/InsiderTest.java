package tests;

import Pages.CareerPage;
import Pages.HomePage;
import Pages.JobListPage;
import Pages.QAPage;
import org.bases.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InsiderTest extends BaseTest {

    @Test
    public void testHomePageOpened() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageOpened(), "Insider ana sayfası açılmadı!");
        homePage.navigateToCareers();
    }

    @Test(dependsOnMethods = "testHomePageOpened")
    public void testCareerPageNavigation() {
        CareerPage careerPage = new CareerPage(driver);

        Assert.assertTrue(careerPage.isLocationsBlockDisplayed(), "Locations bloğu görünmedi!");
        Assert.assertTrue(careerPage.isTeamsBlockDisplayed(), "Teams bloğu görünmedi!");
        Assert.assertTrue(careerPage.isLifeBlockDisplayed(), "Life bloğu görünmedi!");

        careerPage.clickSeeAllTeams();
        careerPage.goToQAPage();

        QAPage qaPage = new QAPage(driver);
        Assert.assertTrue(qaPage.isQAPageOpened(), "QA sayfası açılmadı!");
        qaPage.clickSeeAllQAJobs();

        JobListPage jobListPage = new JobListPage(driver);
        Assert.assertTrue(jobListPage.isJobListPresent(), "Job listesi bulunamadı!");

        jobListPage.filterByLocationAndDepartment("Istanbul, Turkiye", "Quality Assurance");

        Assert.assertTrue(jobListPage.validateJobDetails("Istanbul, Turkiye", "Quality Assurance"),
                "Job detayları doğru değil!");

        Assert.assertTrue(jobListPage.clickViewRoleAndCheckRedirect(),
                "Lever Application sayfasına yönlendirilmedi!");
    }
}
