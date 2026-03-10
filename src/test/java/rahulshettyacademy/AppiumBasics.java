package rahulshettyacademy;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import io.appium.java_client.AppiumBy;

public class AppiumBasics extends BasesTest {
	@Test
	public void appiumTest() throws MalformedURLException, URISyntaxException {
		driver.executeScript("mobile: startActivity",
				ImmutableMap.of("intent", "io.appium.android.apis/.preference.PreferenceDependencies"));
		/*
		 * driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		 * driver.findElement(By.
		 * xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]"
		 * )).click();
		 */
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"WiFi settings\")")).click();
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, "WiFi settings");
		driver.findElement(By.id("android:id/edit")).sendKeys("VQLONG");
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
	}
}
