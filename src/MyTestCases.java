import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
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
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "android1");
		File myApplication = new File("src/applications/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, myApplication.getAbsolutePath());
	}

	@Test
	public void myFirstTest() throws MalformedURLException {
		driver = new AndroidDriver(new URL(appiumURL), caps);
	}
}
