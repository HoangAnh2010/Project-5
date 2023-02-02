package execution;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import keywords.*;
import report.ExtentTestManager;

import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import utils.ExcelUtils;
import utils.Log;
import utils.CapturesUtils;
import static report.ExtentManager.getExtentReports;

public class TestScripts {
	public static ActionKeywords actionKeywords;
	public static String testSuiteID;
	public static String description;
	public static String sActionKeyword;
	public static String locatorType;
	public static String locatorValue;
	public static String testData;

	ArrayList<String> arrTCIDSignIn = new ArrayList<String>();
	ArrayList<String> arrEmailSignIn = new ArrayList<String>();
	ArrayList<String> arrPasswordSignIn = new ArrayList<String>();
	ArrayList<String> arrResultSignIn = new ArrayList<String>();

	ArrayList<String> arrTCIDSignUp = new ArrayList<String>();
	ArrayList<String> arrNameSignUp = new ArrayList<String>();
	ArrayList<String> arrEmailSignUp = new ArrayList<String>();
	ArrayList<String> arrPasswordSignUp = new ArrayList<String>();
	ArrayList<String> arrPasswordCfSignUp = new ArrayList<String>();
	ArrayList<String> arrResultSignUp = new ArrayList<String>();

	public static String sPath = System.getProperty("user.dir") + "\\dataEngine\\data.xlsx";
	String stestCaseID;
	int casePass = 0;
	int caseFail = 0;
	int caseSkip = 0;

	//@Ignore
	@Test (priority = 3) // Sign in
	public void TestSuite_SignIn() throws Exception {

		ExcelUtils.setExcelFile(sPath, "SignInPage");
		Sheet sheet = ExcelUtils.getSheet("SignInPage");
		int rowCount = sheet.getLastRowNum();
		int row = 1;
		String tmp;

		// Lấy dữ liệu trong sheet "test" và thêm vào từng mảng tương ứng
		ExcelUtils.setExcelFile(sPath, "DataOfSignIn");
		Sheet data = ExcelUtils.getSheet("DataOfSignIn");
		int rowCountTest = data.getLastRowNum();
		while (true) {
			if (row > rowCountTest)// tmp.trim().equals("")
				break;

			tmp = ExcelUtils.getCellData("DataOfSignIn", row, 1) + "";
			arrTCIDSignIn.add(tmp);

			tmp = ExcelUtils.getCellData("DataOfSignIn", row, 3) + "";
			arrEmailSignIn.add(tmp);

			tmp = ExcelUtils.getCellData("DataOfSignIn", row, 4) + "";
			arrPasswordSignIn.add(tmp);

			tmp = ExcelUtils.getCellData("DataOfSignIn", row, 5) + "";
			arrResultSignIn.add(tmp);

			row = row + 1;
		}
		CapturesUtils.startRecord("SignIn");
		// Bỏ hàng tiêu đề
		for (int i = 0; i < arrEmailSignIn.size(); i++) {
			if (i <= 5) {

				for (int iRow = 1; iRow <= rowCount - 21; iRow++) {
					reuseSignIn(iRow);
					execute_Actions(testData, null, arrEmailSignIn.get(i), arrPasswordSignIn.get(i), null,
							arrResultSignIn.get(i), arrTCIDSignIn.get(i));
				}
			}
			if ((i >= 6 && i <= 9) || (i >= 11 && i <= 16)) {

				for (int iRow = 8; iRow <= rowCount - 14; iRow++) {
					reuseSignIn(iRow);
					execute_Actions(testData, null, arrEmailSignIn.get(i), arrPasswordSignIn.get(i), null,
							arrResultSignIn.get(i), arrTCIDSignIn.get(i));
				}
			}
			if (i == 10) {

				for (int iRow = 15; iRow <= rowCount - 7; iRow++) {
					reuseSignIn(iRow);
					execute_Actions(testData, null, arrEmailSignIn.get(i), arrPasswordSignIn.get(i), null,
							arrResultSignIn.get(i), arrTCIDSignIn.get(i));
				}
			}
			if (i >= 17) {

				for (int iRow = 22; iRow <= rowCount; iRow++) {
					reuseSignIn(iRow);
					execute_Actions(testData, null, arrEmailSignIn.get(i), arrPasswordSignIn.get(i), null,
							arrResultSignIn.get(i), arrTCIDSignIn.get(i));
				}
			}

		}
		reportInConsole();
		CapturesUtils.stopRecord();
	}

	public void reuseSignIn(int iRow) {
		testSuiteID = ExcelUtils.getCellData("SignInPage", iRow, 1);
		sActionKeyword = ExcelUtils.getCellData("SignInPage", iRow, 5);
		locatorType = ExcelUtils.getCellData("SignInPage", iRow, 6);
		locatorValue = ExcelUtils.getCellData("SignInPage", iRow, 7);
		testData = ExcelUtils.getCellData("SignInPage", iRow, 8);
	}

	//@Ignore
	@Test (priority = 2) // Sign up
	public void TestSuite_SignUp() throws Exception {

		ExcelUtils.setExcelFile(sPath, "SignUpPage");
		Sheet sheet = ExcelUtils.getSheet("SignUpPage");
		int rowCount = sheet.getLastRowNum();
		int row = 1;
		String tmp;

		// Lấy dữ liệu trong sheet "test" và thêm vào từng mảng tương ứng
		ExcelUtils.setExcelFile(sPath, "DataOfSignUp");
		Sheet data = ExcelUtils.getSheet("DataOfSignUp");
		int rowCountTest = data.getLastRowNum();
		while (true) {
			if (row > rowCountTest)// tmp.trim().equals("")
				break;

			tmp = ExcelUtils.getCellData("DataOfSignUp", row, 1) + "";
			arrTCIDSignUp.add(tmp);

			tmp = ExcelUtils.getCellData("DataOfSignUp", row, 3) + "";
			arrNameSignUp.add(tmp);

			tmp = ExcelUtils.getCellData("DataOfSignUp", row, 4) + "";
			arrEmailSignUp.add(tmp);

			tmp = ExcelUtils.getCellData("DataOfSignUp", row, 5) + "";
			arrPasswordSignUp.add(tmp);

			tmp = ExcelUtils.getCellData("DataOfSignUp", row, 6) + "";
			arrPasswordCfSignUp.add(tmp);

			tmp = ExcelUtils.getCellData("DataOfSignUp", row, 7) + "";
			arrResultSignUp.add(tmp);

			row = row + 1;
		}
		CapturesUtils.startRecord("SignUp");
		// Bỏ hàng tiêu đề
		for (int i = 0; i < arrNameSignUp.size(); i++) {
			if (i < 1) {
				// TS_01
				for (int iRow = 1; iRow <= rowCount - 45; iRow++) {
					reuseSignUp(iRow);
					execute_Actions(testData, arrNameSignUp.get(i), arrEmailSignUp.get(i), arrPasswordSignUp.get(i),
							arrPasswordCfSignUp.get(i), arrResultSignUp.get(i), arrTCIDSignUp.get(i));
				}
			}
			if (i >= 2 && i <= 7) {
				// TS_02
				for (int iRow = 10; iRow <= rowCount - 36; iRow++) {
					reuseSignUp(iRow);
					execute_Actions(testData, arrNameSignUp.get(i), arrEmailSignUp.get(i), arrPasswordSignUp.get(i),
							arrPasswordCfSignUp.get(i), arrResultSignUp.get(i), arrTCIDSignUp.get(i));
				}
			}
			if (i >= 8 && i < 10) {
				// TS_03
				for (int iRow = 19; iRow <= rowCount - 27; iRow++) {
					reuseSignUp(iRow);
					execute_Actions(testData, arrNameSignUp.get(i), arrEmailSignUp.get(i), arrPasswordSignUp.get(i),
							arrPasswordCfSignUp.get(i), arrResultSignUp.get(i), arrTCIDSignUp.get(i));
				}
			}
			if (i == 10) {
				// TS_04
				for (int iRow = 28; iRow <= rowCount - 18; iRow++) {
					reuseSignUp(iRow);
					execute_Actions(testData, arrNameSignUp.get(i), arrEmailSignUp.get(i), arrPasswordSignUp.get(i),
							arrPasswordCfSignUp.get(i), arrResultSignUp.get(i), arrTCIDSignUp.get(i));
				}
			}
			if (i == 17) {
				// TS_05
				for (int iRow = 37; iRow <= rowCount - 9; iRow++) {
					reuseSignUp(iRow);
					execute_Actions(testData, arrNameSignUp.get(i), arrEmailSignUp.get(i), arrPasswordSignUp.get(i),
							arrPasswordCfSignUp.get(i), arrResultSignUp.get(i), arrTCIDSignUp.get(i));
				}
			}
			if ((i >= 11 && i <= 16) || (i >= 18)) {
				// TS_06
				for (int iRow = 46; iRow <= rowCount; iRow++) {
					reuseSignUp(iRow);
					execute_Actions(testData, arrNameSignUp.get(i), arrEmailSignUp.get(i), arrPasswordSignUp.get(i),
							arrPasswordCfSignUp.get(i), arrResultSignUp.get(i), arrTCIDSignUp.get(i));
				}
			}			
		}		
		reportInConsole();
		CapturesUtils.stopRecord();
	}

	public void reuseSignUp(int iRow) {
		testSuiteID = ExcelUtils.getCellData("SignUpPage", iRow, 1);
		sActionKeyword = ExcelUtils.getCellData("SignUpPage", iRow, 5);
		locatorType = ExcelUtils.getCellData("SignUpPage", iRow, 6);
		locatorValue = ExcelUtils.getCellData("SignUpPage", iRow, 7);
		testData = ExcelUtils.getCellData("SignUpPage", iRow, 8);
	}

	//@Ignore
	@Test (priority = 4) // Create CV
	public void TestSuite_CreateCV() throws Exception {

		ExcelUtils.setExcelFile(sPath, "CreateCV");
		Sheet sheet = ExcelUtils.getSheet("CreateCV");
		int rowCount = sheet.getLastRowNum();
//		int row = 1;
//		String tmp;
//
//		// Lấy dữ liệu trong sheet "test" và thêm vào từng mảng tương ứng
//		ExcelUtils.setExcelFile(sPath, "DataOfCreateCV");
//		Sheet data = ExcelUtils.getSheet("DataOfCreateCV");
//		int rowCountTest = data.getLastRowNum();
//		while (true) {
//			if (row > rowCountTest)// tmp.trim().equals("")
//				break;
//
//			tmp = ExcelUtils.getCellData("DataOfCreateCV", row, 1) + "";
//			arrTCIDSignIn.add(tmp);
//			
//			tmp = ExcelUtils.getCellData("DataOfCreateCV", row, 3) + "";
//			arrEmailSignIn.add(tmp);
//
//			tmp = ExcelUtils.getCellData("DataOfCreateCV", row, 4) + "";
//			arrPasswordSignIn.add(tmp);
//
//			tmp = ExcelUtils.getCellData("DataOfCreateCV", row, 5) + "";
//			arrResultSignIn.add(tmp);
//			
//			row = row + 1;
//		}
		CapturesUtils.startRecord("CreateCV");
		// Bỏ hàng tiêu đề
		for (int iRow = 1; iRow <= rowCount; iRow++) {
			reuseCreateCV(iRow);
			execute_Actions(testData, null, null, null, null, null, null);
		}
		reportInConsole();
		CapturesUtils.stopRecord();
	}

	public void reuseCreateCV(int iRow) {
		testSuiteID = ExcelUtils.getCellData("CreateCV", iRow, 1);
		sActionKeyword = ExcelUtils.getCellData("CreateCV", iRow, 5);
		locatorType = ExcelUtils.getCellData("CreateCV", iRow, 6);
		locatorValue = ExcelUtils.getCellData("CreateCV", iRow, 7);
		testData = ExcelUtils.getCellData("CreateCV", iRow, 8);
	}
	
	//@Ignore
	@Test (priority = 1) // Search
	public void TestSuite_SearchAndViewJobDetails() throws Exception {

		ExcelUtils.setExcelFile(sPath, "Search");
		Sheet sheet = ExcelUtils.getSheet("Search");
		int rowCount = sheet.getLastRowNum();

		CapturesUtils.startRecord("Search");
		// Bỏ hàng tiêu đề
		for (int iRow = 1; iRow <= rowCount; iRow++) {
			reuseSearch(iRow);
			execute_Actions(testData, null, null, null, null, null, null);
		}
		reportInConsole();
		CapturesUtils.stopRecord();
	}

	public void reuseSearch(int iRow) {
		testSuiteID = ExcelUtils.getCellData("Search", iRow, 1);
		sActionKeyword = ExcelUtils.getCellData("Search", iRow, 5);
		locatorType = ExcelUtils.getCellData("Search", iRow, 6);
		locatorValue = ExcelUtils.getCellData("Search", iRow, 7);
		testData = ExcelUtils.getCellData("Search", iRow, 8);
	}

	public void execute_Actions(String testData, String sName, String sEmail, String sPass, String sPassCf,
			String sResult, String sTCID) throws Exception {

		try {
			switch (sActionKeyword) {
			case "openBrowser":
				// Log.info("--------------Execute Test Case--------------");
				ExtentTestManager.saveToReport("Test Case", "");
				try {
					ActionKeywords.openBrowser(testData);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (Exception e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "move":
				try {
					ActionKeywords.elementPerform(locatorValue);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (Exception e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "switchTo":
				try {
					ActionKeywords.switchTo(testData);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (Exception e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "clear":
				try {
					ActionKeywords.clear(locatorType, locatorValue);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (Exception e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "navigate":
				try {
					ActionKeywords.navigate(testData);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (Exception e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "setText":
				try {
					if (testData.equalsIgnoreCase("varEmail"))
						ActionKeywords.setText(locatorType, locatorValue, sEmail);
					else {
						if (testData.equalsIgnoreCase("varName")) {
							ActionKeywords.setText(locatorType, locatorValue, sName);
						} else {
							if (testData.equalsIgnoreCase("varPassword")) {
								ActionKeywords.setText(locatorType, locatorValue, sPass);
							} else {
								if (testData.equalsIgnoreCase("varPasswordCf")) {
									ActionKeywords.setText(locatorType, locatorValue, sPassCf);
								} else {
									ActionKeywords.setText(locatorType, locatorValue, testData);
								}
							}
						}
					}

					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (NoSuchElementException e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}

				break;
			case "uploadImage":
				try {
					ActionKeywords.uploadImage(locatorType, locatorValue, testData);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (Exception e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "clickButton":
				try {
					ActionKeywords.clickButton(locatorType, locatorValue);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (NoSuchElementException e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}

				break;
			case "doubleClick":
				try {
					ActionKeywords.doubleClick(locatorType, locatorValue);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (NoSuchElementException e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}

				break;
			case "clickElement":
				try {
					ActionKeywords.clickElement(locatorType, locatorValue);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (NoSuchElementException e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "verifyTextInSignIn":
				if (ActionKeywords.verifyTextInSignIn(sResult)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "alertInSignInPage":
				if (ActionKeywords.verifyAlertInSignIn(sResult)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "alertOfEmailinSignInPagehtml5":
				if (ActionKeywords.verifyAlertOfEmailinSignInPagehtml5(sResult)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "alertOfPasswordinSignInPagehtml5":
				if (ActionKeywords.verifyAlertOfPasswordinSignInPagehtml5(sResult)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "alertNameSignUp":
				if (ActionKeywords.verifyAlertNameSignUp(sResult)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "alertOfEmailinSignUpPagehtml5":
				if (ActionKeywords.verifyAlertOfEmailinSignUpPagehtml5(sResult)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "alertOfPasswordinSignUpPagehtml5":
				if (ActionKeywords.verifyAlertOfPasswordinSignUpPagehtml5(sResult)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "alertOfPasswordConfinSignUpPagehtml5":
				if (ActionKeywords.verifyAlertOfPasswordConfinSignUpPagehtml5(sResult)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "alertOfEmailinSignUpPage":
				if (ActionKeywords.verifyAlertOfEmailinSignUpPage(sResult)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "alertOfPasswordConfinSignUpPage":
				if (ActionKeywords.verifyAlertOfPasswordConfinSignUpPage(sResult)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "verifyText":
				if (ActionKeywords.verifyText(locatorType, locatorValue, testData)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "verifyTitle":
				if (ActionKeywords.verifyTitle(testData)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "verifyUrl":
				if (ActionKeywords.getUrl(testData)) {
					Log.info("Same result ---> Pass");
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} else {
					Log.error("Different result ---> Fail");
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "displayed":
				try {
					ActionKeywords.displayed(locatorType, locatorValue);
					casePass++;
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (Exception e) {
					caseFail++;
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "screenShot":
				try {
					ActionKeywords.screenShot(testSuiteID + "_" + sTCID);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (Exception e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "selectOptionByValue":
				try {
					ActionKeywords.selectOptionByValue(locatorType, locatorValue, testData);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (NoSuchElementException e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "selectOptionByText":
				try {
					ActionKeywords.selectOptionByText(locatorType, locatorValue, testData);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (NoSuchElementException e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "scrollDown":
				try {
					ActionKeywords.clickElementWithJs(locatorType, locatorValue);
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (NoSuchElementException e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			case "closeBrower":
				try {
					ActionKeywords.closeBrowser();
					ExtentTestManager.logMessage(Status.PASS, description);
				} catch (Exception e) {
					ExtentTestManager.logMessage(Status.FAIL, description);
				}
				break;
			default:
				Log.info("[>>ERROR<<]: |Keyword Not Found " + sActionKeyword);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		getExtentReports().flush();

	}

	public void reportInConsole() {

		java.util.Date date = new java.util.Date();
		System.out.println("==========================================================");
		System.out.println("-----------" + date + "--------------");
		System.out.println("Total number of Testcases run: " + (casePass + caseFail + caseSkip));
		System.out.println("Total number of passed Testcases: " + casePass);
		System.out.println("Total number of failed Testcases: " + caseFail);
		System.out.println("Total number of skiped Testcases: " + caseSkip);
		System.out.println("==========================================================");
	}
}
