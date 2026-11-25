package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.TakeScreenshots;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static utils.Base.driver;

public class ExtentReportManager implements ITestListener {
    private static ExtentSparkReporter sparkReporter;
    private static ExtentReports extent;
    private static ExtentTest test;

    public void onStart(ITestContext context) {
        Path reportPath = Paths.get(System.getProperty("user.dir"), "Reports", "NdosiAutomationReport.html");
        try {
            Files.createDirectories(reportPath.getParent());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create report directory: " + reportPath.getParent(), e);
        }

        sparkReporter = new ExtentSparkReporter(reportPath.toString());
        sparkReporter.config().setDocumentTitle("Ndosi automation");
        sparkReporter.config().setReportName("Regression Test");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Execution machine", System.getProperty("user.name"));
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Test Environment", "QA");
    }

    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getName());
        test.log(Status.PASS, "Test Case "+result.getMethod().getMethodName()+" has passed");
    }

    public void onTestFailure(ITestResult result){
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test Case "+result.getMethod().getMethodName()+" has failed");
        test.log(Status.FAIL, result.getThrowable());

        try {
            String screenshotName = result.getMethod().getMethodName() + ".png";
            TakeScreenshots.takesSnapShot(driver, result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(Paths.get("Screenshots", screenshotName).toString().replace("\\", "/"),result.getMethod().getMethodName());
        } catch (Exception e) {
            throw new RuntimeException("Failed to capture screenshot for test case: " + result.getMethod().getMethodName(), e);
        }
    }

    public void onTestSkipped(ITestResult result){
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test Case "+result.getMethod().getMethodName()+" has been skipped");
    }

    public void onFinish(ITestContext context){
        extent.flush();
    }


}
