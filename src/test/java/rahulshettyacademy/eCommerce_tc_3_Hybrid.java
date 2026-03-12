package rahulshettyacademy;

import java.awt.RenderingHints.Key;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_tc_3_Hybrid extends BasesTest {
	
	boolean isFound = false;
	float totalAmount = 0;

	@Test
	public void fillForm() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vqlong");
		driver.hideKeyboard();

		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();

		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Argentina\"))")).click();

		driver.findElement(By.xpath("//android.widget.RadioButton[@text=\"Male\"]")).click();

		driver.findElement(
				By.xpath("//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]"))
				.click();
		Thread.sleep(5000L);

		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Jordan 6 Rings\"))"));

		driver.findElement(By.xpath(
				"//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"Jordan 6 Rings\"]/following-sibling::android.widget.LinearLayout[2]/android.widget.TextView[@text='ADD TO CART']"))
				.click();

		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Nike SFB Jungle\"))"));
		driver.findElement(By.xpath(
				"//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productName\" and @text=\"Nike SFB Jungle\"]\n"
						+ "/parent::android.widget.LinearLayout[1]\n"
						+ "//android.widget.TextView[@text='ADD TO CART']"))
				.click();

		Thread.sleep(2000L);
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(2000L);
		WebElement pageTitle = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
		wait.until(ExpectedConditions.attributeContains(pageTitle, "text", "Cart"));

		List<WebElement> elesName = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));

		for (WebElement ele : elesName) {
			if (ele.getText().equalsIgnoreCase("Jordan 6 Rings")) {
				isFound = true;
				break;
			}
		}
		Assert.assertTrue(isFound);
		List<WebElement> elesPrice = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		for (WebElement ele : elesPrice) {
			totalAmount += Float.parseFloat(ele.getText().replace("$", ""));
		}
		Float actualAmount = Float.parseFloat(driver
				.findElement(By.xpath(
						"//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/totalAmountLbl\"]"))
				.getText().replace("$", ""));
		System.out.println(totalAmount + " " + actualAmount);
		Assert.assertEquals(totalAmount, actualAmount);
		
		driver.findElement(By.xpath("//android.widget.CheckBox")).click();
		driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.androidsample.generalstore:id/btnProceed\"]")).click();
		
		Thread.sleep(6000L);
		
		Set<String> contexrs = driver.getContextHandles();
		
		for (String context:contexrs) {
			System.out.println(context);
		}
		
		driver.context("WEBVIEW_com.androidsample.generalstore");    // Chrome Driver
		driver.findElement(By.name("q")).sendKeys("facebook");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		/* driver.pressKey(new KeyEvent(AndroidKey.ENTER)); */
		Thread.sleep(6000L);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
	}
}
