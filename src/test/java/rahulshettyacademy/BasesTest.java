package rahulshettyacademy;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class BasesTest {

	public AppiumDriverLocalService service;
	public AndroidDriver driver;

	@BeforeClass
	public void ConfigireAppium() throws MalformedURLException, URISyntaxException {

		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"/home/thundersoft/.nvm/versions/node/v20.19.6/lib/node_modules/appium/build/lib/main.js"))
				.usingDriverExecutable(new File("/home/thundersoft/.nvm/versions/node/v20.19.6/bin/node"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setUdid("0A171FDD40053Z");
		/*
		 * options.setApp(
		 * "//home//thundersoft//eclipse-workspace//Appium//src//test//java//resources//ApiDemos-debug.apk"
		 * );
		 */
		options.setApp("//home//thundersoft//eclipse-workspace//Appium//src//test//java//resources//General-Store.apk");


		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

	public void longclick(WebElement ele) {
		driver.executeScript("mobile:longclickGesture",
				ImmutableMap.of("elementID", ((RemoteWebElement) ele).getId(), "duration", 1000));
	}

	public WebElement scrollDemo(String str) {
		return driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + str
						+ "\"))"));
	}

	public void scrollDemo2() {
		boolean canScrollMore;
		do {
			canScrollMore = (boolean) driver.executeScript("mobile:scrollGesture", ImmutableMap.of("left", 240, "top",
					238, "width", 1916, "height", 571, "direction", "down", "percent", 0.75));
		} while (canScrollMore);
	}

	public void swipeDemo(WebElement ele) {
		driver.executeScript("mobile:swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "direction", "left", "percent", 0.01));
	}

	public void dragAndDropDemo(WebElement ele, int endX, int endY) {
		driver.executeScript("mobile:dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "endX", endX, "endY", endY));
	}
}
