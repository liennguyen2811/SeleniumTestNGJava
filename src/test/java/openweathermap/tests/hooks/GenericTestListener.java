package openweathermap.tests.hooks;

import openweathermap.common.BrowserControl;
import openweathermap.common.Fixtures;
import openweathermap.common.TestConfig;
import openweathermap.helpers.BrowserstackHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class GenericTestListener extends TestListenerAdapter {
    private WebDriver driver;
    private String bsSessionId;

    @Override
    public void onTestStart(ITestResult tr) {
        driver = BrowserControl.getDriver();

        if (TestConfig.getHub() == TestConfig.Driver.BROWSERSTACK) {
            bsSessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        driver = BrowserControl.getDriver();

        if (TestConfig.getHub() == TestConfig.Driver.BROWSERSTACK) {
            BrowserstackHelper.failBsStatus(bsSessionId);
        }
        Fixtures.TearDown.close(driver);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        driver = BrowserControl.getDriver();

        if (TestConfig.getHub() == TestConfig.Driver.BROWSERSTACK) {
            BrowserstackHelper.failBsStatus(bsSessionId);
        }
        try {
            String testNameMethod = TestConfig.getTestNameForTestFailed();
            System.out.println(" Lien testNameMethod is " + testNameMethod);
            Fixtures.takeSnapShot(driver,"src/test/resources/failedimage/" + testNameMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Fixtures.TearDown.close(driver);

    }

}