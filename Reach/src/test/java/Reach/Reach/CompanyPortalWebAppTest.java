package Reach.Reach;

import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CompanyPortalWebAppTest {

	WebDriver driver;
	Support_Functions SF;

	@Before
	public void setUp() {
		driver = Driver.getDriver();
		SF = new Support_Functions(driver);

	}

	@Test
	public void FetchCompanyDetails() throws IOException {

		SF.Verify_Jsonfile_exist();
		// Get number of pages
		List<WebElement> No_of_Pages_With_Data = driver
				.findElements(By.xpath("//ul[@class='browse']/li//descendant::a"));

		// Open each page and get required details
		for (int i = 0 ;i< No_of_Pages_With_Data.size(); i++) {

			try {
				No_of_Pages_With_Data.get(i).click();

			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				No_of_Pages_With_Data = driver.findElements(By.xpath("//ul[@class='browse']/li//descendant::a"));
				No_of_Pages_With_Data.get(i).click();
			}

			List<WebElement> No_of_links_ieright = driver
					.findElements(By.xpath("//div[@class='col-md-6 ingredients ieright']/ul//descendant::a"));

			int No_of_links = driver.findElements(By.xpath("//div[@class='col-md-6 ingredients ieleft']/ul/li/a"))
					.size() + No_of_links_ieright.size();

			if (No_of_links == 1) {
				SF.FirstCompany();
			} else if (No_of_links == 2 || No_of_links == 3) {
				SF.FirstCompany();
				SF.LastCompany(No_of_links_ieright.size());
			} else if (No_of_links == 4) {
				SF.FirstCompany();
				SF.LastCompany(No_of_links_ieright.size());
			} else {
				SF.FirstCompany();
				SF.ThirdCompany(No_of_links);
				SF.LastCompany(No_of_links_ieright.size());
			}

		}
		SF.JsonfileCreation();

	}

	@After
	public void terminateBrowser() {
		
		try {
			Thread.sleep(5000);
			// driver.close();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
}
