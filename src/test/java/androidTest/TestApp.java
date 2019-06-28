package androidTest;

import java.net.MalformedURLException;

import org.testng.annotations.AfterTest;

import jAppium.jAppium.AppTest;

public class TestApp {

	@org.junit.Test
	public void Test() throws MalformedURLException, InterruptedException {
		AppTest appTest = new AppTest();
		appTest.Android_LaunchApp();
		appTest.Login();

	}

	@AfterTest
	public void TearDown() {
		AppTest appTest = new AppTest();
		appTest.CloseApp(); 
	}
}
