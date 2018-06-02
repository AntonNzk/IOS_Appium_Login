import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import static org.junit.Assert.assertTrue;


public class MobileWebTest {

    @Test
    public void testIncorrectFBLogin() throws Exception{
        URL serverURL = new URL("http://127.0.0.1:4723/wd/hub");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.3");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 6");

        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET,"true");


        capabilities.setCapability("ShowLog", "true");
        capabilities.setCapability("autoAcceptAlerts", "true");


        System.out.println("Step 1 Create new driver");
        AppiumDriver driver = new IOSDriver(serverURL,capabilities);
        WebDriverWait wait = new WebDriverWait(driver,30);
        System.out.println("Step 2. Open website");
        driver.get("https://facebook.com");

        System.out.println("Step 3. Enter Email");
        driver.findElement(By.name("email")).sendKeys("anton@test.com");

        System.out.println("Step 4. Enter password");
        driver.findElement(By.name("pass")).sendKeys("password");

        System.out.println("Step 5. Click login button");
        driver.findElement(By.name("login")).click();

        System.out.println("Step 6. Check Error message");
        assertTrue(driver.findElement(By.cssSelector("div[data-sigil='m_login_notice']")).getText().contains("The password you entered is incorrect"));
        System.out.println("Step 7. Close driver");
        driver.close();
    }
}
