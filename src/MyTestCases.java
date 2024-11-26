import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyTestCases {

	// mobile
	DesiredCapabilities caps = new DesiredCapabilities();
	String appiumURL = "http://127.0.0.1:4723/wd/hub";
	AndroidDriver driver;

	@BeforeTest
	public void mySetUp() {
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "android");
		File myApplication = new File("src/applications/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, myApplication.getAbsolutePath());
	}

	@Test(enabled = false)
	public void addTwoNumbers() throws MalformedURLException {
		driver = new AndroidDriver(new URL(appiumURL), caps);
		driver.findElement(By.id("com.google.android.calculator:id/digit_9")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
		String actual = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		String expected = "14";
		System.out.println(actual + "this is the actual value from the app");
		System.out.println(expected + "this is the expected value");
		Assert.assertEquals(actual, expected);
	}

	@Test(enabled = false)
	public void addTwoRandomNumbers() throws MalformedURLException {
		driver = new AndroidDriver(new URL(appiumURL), caps);
		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < allButtons.size(); i++) {
			if (allButtons.get(i).getAttribute("resource-id").contains("digit")) {
				allButtons.get(i).click();
			}
		}
		String actualResult = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
		String expectedResult = "7894561230";
		Assert.assertEquals(actualResult, expectedResult);
	}

	@Test
	public void clickOnEvenNumbers() throws MalformedURLException {
		driver = new AndroidDriver(new URL(appiumURL), caps);
		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < allButtons.size(); i++) {
			// easy way
//			if (allButtons.get(i).getAttribute("resource-id").contains("2")
//					|| allButtons.get(i).getAttribute("resource-id").contains("4")
//					|| allButtons.get(i).getAttribute("resource-id").contains("6")
//					|| allButtons.get(i).getAttribute("resource-id").contains("8")
//					|| allButtons.get(i).getAttribute("resource-id").contains("0")) {
//				allButtons.get(i).click();
//			}

			// hard way
			if (allButtons.get(i).getAttribute("resource-id").contains("digit")) {
				String theNumber = allButtons.get(i).getAttribute("resource-id")
						.replace("com.google.android.calculator:id/digit_", "");
				int theNumberAsInt = Integer.parseInt(theNumber);
				if (theNumberAsInt % 2 == 0) {
					System.out.println(theNumberAsInt);
					allButtons.get(i).click();
				}
			}
		}
	}
}
