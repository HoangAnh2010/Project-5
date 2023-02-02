package report;

import com.aventstack.extentreports.ExtentReports;
import config.Driver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

	static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    //Phiên bản ExtentReports được tạo bằng cách gọi phương thức createExtentReports () từ ExtentManager
	static ExtentReports extent = ExtentManager.getExtentReports();

    //trả về cá thể ExtentTest trong scopeTestMap bằng cách sử dụng id luồng hiện tại
    public static ExtentTest getTest() {
    	//extentTestMap lưu giữ thông tin của id luồng và các cá thể ExtentTest
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    //một phiên bản của ExtentTest đã được tạo và đưa vào extentTestMap với id luồng hiện tại
    public static synchronized ExtentTest saveToReport(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
    public static void logMessage(Status status, String message) {
        getTest().log(status, message);
    }
    
    public static void logMessage(String message) {
        getTest().log(Status.INFO, message);
    }

    public static void addScreenShot(String message) {
        String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.INFO, message,
                getTest().addScreenCaptureFromBase64String(base64Image).getModel().getMedia().get(0));
    }

    public static void addScreenShot(Status status, String message) {

        String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BASE64);
        getTest().log(status, message,
                getTest().addScreenCaptureFromBase64String(base64Image).getModel().getMedia().get(0));
    }
    
}
