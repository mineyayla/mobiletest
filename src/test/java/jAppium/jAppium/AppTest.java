package jAppium.jAppium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.PressesKey;

public class AppTest {

	public AppiumDriver<MobileElement> driver;
	public WebDriverWait wait;
	public DesiredCapabilities cap;

	public void Android_LaunchApp() throws MalformedURLException {
		cap = new DesiredCapabilities();
		cap.setCapability("chromedriverExecutable",
				"C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\node_modules\\appium-chromedriver\\chromedriver\\win2\\chromedriver.exe");
		cap.setCapability("deviceName", "Galaxy Nexut API 24");
		cap.setCapability("udid", "emulator-5554");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "7.0");
		cap.setCapability("appPackage", "com.logo.lbs.journey");
		cap.setCapability("appActivity", "com.logo.lbs.journey.MainActivity");
		cap.setCapability("noReset", "false");
		// cap.setCapability("autoWebview", "true");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		Set<String> contexts = driver.getContextHandles();

		for (String string : contexts) {
			System.out.println(string);
		}
		driver.context("WEBVIEW_com.logo.lbs.journey");

		System.out.println("111" + driver.getContext());

	}

	public void Login() throws InterruptedException {
		this.welcomePage();
	}

	public void welcomePage() throws InterruptedException {
		Thread.sleep(20000);
		System.out.println("### Welcome page'e geldi");
		WebElement loginPageDirectionButton = driver.findElementById("login");
		loginPageDirectionButton.click();
		this.loginPage();
	}

	public void loginPage() throws InterruptedException {
		System.out.println("### Login page'e geldi");
		
		driver.findElementByCssSelector("#mail > input").sendKeys("mine.yayla@logo.com.tr");
		WebElement submit = driver.findElementByCssSelector("#password > input");
		submit.sendKeys("123");
		driver.hideKeyboard();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.context("NATIVE_APP");
		driver.findElementById("login").click();
		
		driver.context("WEBVIEW_com.logo.lbs.journey");
		
		/*WebDriverWait wait = new WebDriverWait(driver, 50);
		if(wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("tab-t0-3"))) != null);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);*/
		Thread.sleep(10000);
		//new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(driver.findElementById("tab-t0-3")));
		driver.findElementById("tab-t0-3").click();
		
		String name =driver.findElementByClassName("employee-name").getText();
		Assert.assertEquals(name, "MİNE YAYLA");
		
		String role=driver.findElementByClassName("employee-title").getText();
		Assert.assertEquals(role, "Test Uzmanı");
		
		driver.findElementById("tab-t0-2").click();
		driver.findElementByCssSelector("#tabpanel-t0-2 > page-discover > ion-header > ion-navbar > div.toolbar-content.toolbar-content-md > search > ion-searchbar > div > input").sendKeys("LOGO");
		driver.findElementsByClassName("product-name").get(1).click();
		String productName =driver.findElementByCssSelector("#tabpanel-t0-2 > page-detail > ion-content > div.scroll-content > section.item-info > aside > span.title").getText();
		Assert.assertEquals(productName, "Logo CRM");
		
		driver.findElementById("tab-t0-3").click();
		Thread.sleep(5000);
		driver.findElementByCssSelector("#tabpanel-t0-3 > page-employee-profile > ion-content > div.scroll-content > div > div.sub-content > div.sub-content-in > button:nth-child(3)").click();
		driver.findElementByCssSelector("#tabpanel-t0-3 > page-employee-about > ion-content > div.scroll-content > div.root-container > div.interest-container > div.interest-content > button > span").click();
		driver.findElementsByClassName("category-name").get(1).click();
		driver.findElementsByClassName("interest-name").get(0).click();
 		driver.findElementByCssSelector("#tabpanel-t0-3 > page-manage-interest > ion-footer > ion-toolbar > div.toolbar-content.toolbar-content-md > ion-buttons > button").click();
 		Thread.sleep(10000);
 		String interestName = driver.findElementsByClassName("interest").get(1).getText();
		Assert.assertEquals(interestName, "Internet Of Things");
		
		
	}

	public void CloseApp() {
		driver.quit();
	}
}
