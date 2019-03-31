package testcases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class TestWebBrowser {

    AndroidDriver driver;

    public static String firstDeviceName = "44354f5747553098";
    public static String secondDeviceName = "emulator-5554";


    @BeforeClass
    @Parameters({"port","deviceID"})
    public void startTest(String portNo,String device_id) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        File app = new File(".\\apk\\selendroid-test-app-0.17.0.apk");

        if (device_id.equalsIgnoreCase(firstDeviceName)) {
            System.out.println(portNo+"---"+System.currentTimeMillis());
            System.out.println(device_id+"---"+System.currentTimeMillis());
            //capabilities.setCapability(CapabilityType.BROWSER_NAME,"Chrome");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,firstDeviceName);
            capabilities.setCapability("udid",firstDeviceName);
            capabilities.setCapability("platformVersion","9.0");
            capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());

        } else if (device_id.equalsIgnoreCase(secondDeviceName)){

            System.out.println(portNo+"---"+System.currentTimeMillis());
            System.out.println(device_id+"---"+System.currentTimeMillis());
            //capabilities.setCapability(CapabilityType.BROWSER_NAME,"Chrome");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,secondDeviceName);
            capabilities.setCapability("udid",secondDeviceName);
            capabilities.setCapability("platformVersion","7.1.1");
            capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
        }

        driver = new AndroidDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void testcase(){
//        driver.get("http://google.com");
//        System.out.println("URL Opened on : - " + driver.getSessionId());
//        driver.findElement(By.name("q")).sendKeys("Hello Selenium!!!");
        WebElement popup = driver.findElementById("android:id/content");

        driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).sendKeys("Hello appium");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
