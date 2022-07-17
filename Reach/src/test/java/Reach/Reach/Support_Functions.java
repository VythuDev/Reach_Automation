package Reach.Reach;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Support_Functions {

	Map<String, Object> companyObj;
	Map<String, Object> contact_Info;
	WebDriver driver;
	FileWriter filewriter;
	String output_file_path = System.getProperty("user.dir") +"\\OutputJson"; // directory from where the program was launched
	
	JSONArray json_arr = new JSONArray();
	
	public Support_Functions(WebDriver driver)
	{
		this.driver = driver;
	}

	public void Verify_Jsonfile_exist() throws IOException {
		File file = new File(output_file_path + "\\data.json");
		if (file.exists()) {
			file.delete();
		}

	}

	public void FirstCompany() {
		companyObj = new LinkedHashMap<>();
		driver.findElement(By.xpath("//div[@class='col-md-6 ingredients ieleft']/ul/li/a")).click();
		String CompanyName = driver.findElement(By.xpath("//div[@class='col-md-12 title']/h1")).getText();
		companyObj.put("Company_Name", CompanyName);

		try {
			if (driver.findElements(By.xpath("//div[@class='companyLogoWrapper']/img")).size() != 0) {
				WebElement Logo = driver.findElement(By.xpath("//div[@class='companyLogoWrapper']/img"));
				String logoSRC = Logo.getAttribute("src");

				URL imageURL = new URL(logoSRC);
				BufferedImage saveimage = ImageIO.read(imageURL);
				CompanyName = CompanyName.replaceAll("/", ""); // CompanyName=CompanyName.replaceAll("[^a-zA-Z0-9]+","");
				ImageIO.write(saveimage, "png",
						new File("C:\\Users\\Devesh\\eclipse-workspace\\Reach\\Screenshots\\" + CompanyName + ".png"));
				companyObj.put("Logo_File_Name", CompanyName + ".png");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Get_Address();
		Get_Contact_Info();
		add_Company_Object_to_JsonArray();
		driver.navigate().back();
	}

	public void LastCompany( int No_of_Companies) {
		companyObj = new LinkedHashMap<>();
		String Path = "//div[@class='col-md-6 ingredients ieright']/ul/li[" + No_of_Companies + "]/a";
		driver.findElement(By.xpath(Path)).click();
		String CompanyName = driver.findElement(By.xpath("//div[@class='col-md-12 title']/h1")).getText();
		companyObj.put("Company_Name", CompanyName);

		try {
			if (driver.findElements(By.xpath("//div[@class='companyLogoWrapper']/img")).size() != 0) {
				WebElement Logo = driver.findElement(By.xpath("//div[@class='companyLogoWrapper']/img"));
				String logoSRC = Logo.getAttribute("src");

				URL imageURL = new URL(logoSRC);
				BufferedImage saveimage = ImageIO.read(imageURL);

				ImageIO.write(saveimage, "png",
						new File("C:\\Users\\Devesh\\eclipse-workspace\\Reach\\Screenshots\\" + CompanyName + ".png"));
				companyObj.put("Logo_File_Name", CompanyName + ".png");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Get_Address();
		Get_Contact_Info();
		add_Company_Object_to_JsonArray();
		driver.navigate().back();
	}

	public void ThirdCompany( int No_of_links) {
		companyObj = new LinkedHashMap<>();
		if (No_of_links == 4 || No_of_links == 3) {
			driver.findElement(By.xpath("//div[@class='col-md-6 ingredients ieright']/ul/li[01]/a")).click();
		} else if (No_of_links >= 5) {
			driver.findElement(By.xpath("//div[@class='col-md-6 ingredients ieleft']/ul/li[03]/a")).click();
		}

		String CompanyName = driver.findElement(By.xpath("//div[@class='col-md-12 title']/h1")).getText();
		companyObj.put("Company_Name", CompanyName);
		try {
			if (driver.findElements(By.xpath("//div[@class='companyLogoWrapper']/img")).size() != 0) {
				WebElement Logo = driver.findElement(By.xpath("//div[@class='companyLogoWrapper']/img"));
				String logoSRC = Logo.getAttribute("src");

				URL imageURL = new URL(logoSRC);
				BufferedImage saveimage = ImageIO.read(imageURL);

				ImageIO.write(saveimage, "png",
						new File("C:\\Users\\Devesh\\eclipse-workspace\\Reach\\Screenshots\\" + CompanyName + ".png"));
				companyObj.put("Logo_File_Name", CompanyName + ".png");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Get_Address();
		Get_Contact_Info();
		add_Company_Object_to_JsonArray();
		driver.navigate().back();
	}

	public void Get_Address() {
		String Address = driver.findElement(By.xpath("//div[@class='col-md-5']/div/div[02]/p")).getText();
		companyObj.put("Company_Address", Address);
	};

	public void Get_Contact_Info() {

		contact_Info = new LinkedHashMap<>();
		capture_Contact_details_from_Div(2, "col-md-5");// left div element check
		capture_Contact_details_from_Div(0, "col-md-4"); // right div element check

		companyObj.put("Contact_Details", contact_Info);
		
	}

	// Generic function
	public void capture_Contact_details_from_Div(int loop_start_value, String div_class_name) {
		List<WebElement> Contact_Details_Element = driver
				.findElements(By.xpath("//div[@class='" + div_class_name + "']/div/div"));

		for (int i = loop_start_value; i < Contact_Details_Element.size(); i = i + 2) {

			if (Contact_Details_Element.get(i).getText().equalsIgnoreCase("Fax")
					|| Contact_Details_Element.get(i).getText().contains("Medical Information Fax")) {
				String Fax = driver
						.findElement(By.xpath("//div[@class='" + div_class_name + "']/div/div[" + (i + 2) + "]/p"))
						.getText();
				contact_Info.put("Fax", Fax);

			} else if (Contact_Details_Element.get(i).getText().equalsIgnoreCase("Medical Information e-mail")) {
				String Email = driver
						.findElement(By.xpath("//div[@class='" + div_class_name + "']/div/div[" + (i + 2) + "]/p"))
						.getText();
				contact_Info.put("Medical Information e-mail", Email);

			} else if (Contact_Details_Element.get(i).getText().equalsIgnoreCase("Telephone")) {
				String Telephone = driver
						.findElement(By.xpath("//div[@class='" + div_class_name + "']/div/div[" + (i + 2) + "]/p"))
						.getText();
				contact_Info.put("Telephone", Telephone);

			} else if (Contact_Details_Element.get(i).getText().equalsIgnoreCase("Medical Information Direct Line")) {
				String DirectLine = driver
						.findElement(By.xpath("//div[@class='" + div_class_name + "']/div/div[" + (i + 2) + "]/p"))
						.getText();
				contact_Info.put("Medical Information Direct Line", DirectLine);
			}
		}
	}



	@SuppressWarnings("unchecked")
	public void add_Company_Object_to_JsonArray() {

		JSONObject json_obj = new JSONObject();
		json_obj.putAll(companyObj);
		json_arr.add(json_obj);

	}

	public void JsonfileCreation() {

		try {
			filewriter = new FileWriter(output_file_path + "\\data.json");
			filewriter.append(json_arr.toJSONString());
			filewriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(json_arr.toJSONString());
	}
}
