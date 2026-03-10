package rahulshettyacademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class SwipeDemo extends BasesTest {
	@Test
	public void swipeDemoTest() {
		scrollDemo("Views").click();
		scrollDemo("Gallery").click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		WebElement firstImage = driver.findElement(By.xpath("//android.widget.ImageView[1]"));
		Assert.assertEquals(firstImage.getAttribute("focusable"), "true");
		swipeDemo(firstImage);
		Assert.assertEquals(firstImage.getAttribute("focusable"), "false");

	}
}
