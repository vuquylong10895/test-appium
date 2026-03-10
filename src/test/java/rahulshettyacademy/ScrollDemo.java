package rahulshettyacademy;

import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import io.appium.java_client.AppiumBy;

public class ScrollDemo extends BasesTest {
	@Test
	public void scrollDemoTest() throws MalformedURLException, URISyntaxException, InterruptedException {
		
		scrollDemo("Views");
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		scrollDemo("Search View");
		//scrollDemo2();
		Thread.sleep(2000);
	}
}
