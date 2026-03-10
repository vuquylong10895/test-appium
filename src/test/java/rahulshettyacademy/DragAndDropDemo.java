package rahulshettyacademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropDemo extends BasesTest {
	@Test
	public void DragAndDropDemoTest() {
		scrollDemo("Views").click();
		scrollDemo("Drag and Drop").click();
		WebElement ele = driver.findElement(By.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_1\"]"));
		dragAndDropDemo(ele, 642, 598);
		String dropText = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
		Assert.assertEquals(dropText, "Dropped!");
	}
}
