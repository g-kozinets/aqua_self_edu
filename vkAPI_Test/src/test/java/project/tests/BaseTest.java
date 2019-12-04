package project.tests;

import aquality.selenium.browser.Browser;
import aquality.selenium.browser.BrowserManager;
import aquality.selenium.logger.Logger;
import framework.configuration.Configuration;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.internal.TestResult;

import static framework.configuration.Configuration.getCurrentEnvironment;

public class BaseTest {

    protected final Logger logger = Logger.getInstance();

    @BeforeMethod
    public void before() throws WebDriverException {
        logger.info("----------------------------------------");
        logger.info("============ PRECONDITIONS =============");
        getBrowser().goTo(getCurrentEnvironment().getStartUrl());
        getBrowser().setWindowSize(getCurrentEnvironment().getWidth(), getCurrentEnvironment().getHeight());
        logger.info("----------------------------------------");
    }

    @AfterMethod
    public void afterMethod(ITestContext testContext, ITestResult testResult) {
        makeScreenshot();
        logger.info("----------------------------------------");
        logger.info("=== TEST '%1$s' '%2$s' ===", testContext.getName(), getStatusName(testResult.getStatus()));
        logger.info("----------------------------------------");
        getBrowser().quit();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] makeScreenshot() {
        return getBrowser().getScreenshot();
    }

    private String getStatusName(int status){

        switch (status) {
            case TestResult.SUCCESS:
                return "SUCCESS";
            case TestResult.FAILURE:
                return "FAILURE";
            case TestResult.SKIP:
                return "SKIP";
            case TestResult.STARTED:
                return "STARTED";
            case TestResult.SUCCESS_PERCENTAGE_FAILURE:
                return "SUCCESS PERCENTAGE FAILURE";
            default:
                return "UNDEFINED STATUS ID " + status;
        }
    }

    private Browser getBrowser(){
        return BrowserManager.getBrowser();
    }
}

