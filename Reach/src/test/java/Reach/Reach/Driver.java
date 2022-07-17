package Reach.Reach;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
	static WebDriver driver;
	static String Chrome_driver_path = System.getProperty("user.dir") + "\\chromedriver_win32\\chromedriver.exe";

	public static WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", Chrome_driver_path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.get("https://www.medicines.org.uk/emc/browse-companies");
		// Accept Cookies
		driver.findElement(By.id("onetrust-accept-btn-handler")).click();
		
		return driver;
	}

}
