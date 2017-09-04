package com.pack.common.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreatePetPage {

	public CreatePetPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}

	public void enterPet(WebDriver driver, String name) 
	{
		By TF_name = By.xpath("//input[@placeholder='Pet Name']");
		WebElement petTxtBox = driver.findElement(TF_name);
		if(petTxtBox.isDisplayed())
			petTxtBox.sendKeys(name);
	}

	public void enterStatus(WebDriver driver, String status) 
	{
		By TF_status = By.xpath("//input[@placeholder='Pet Status']");
		WebElement statusTxtBox = driver.findElement(TF_status);
		if(statusTxtBox.isDisplayed())
			statusTxtBox.sendKeys(status);
	}

	public void clickCreate(WebDriver driver) {
		By BTN_create = By.id("btn-create");
		WebElement createInBtn = driver.findElement(BTN_create);
		if(createInBtn.isDisplayed())
			createInBtn.click();
	}

	public boolean verifyNewPet(WebDriver driver, int intcont, String creation) 
	{
		System.out.println("-----------------------------------------------------------------");
		boolean status = false;
		try{
			driver.get("https://qa-petstore.herokuapp.com/");
			int finalcount = returnCount(driver);
			System.out.println("CREATION of Pet using "+creation+" option: Initial Pet List Count: "+intcont+" Updated Pet List count: "+finalcount);
			if(finalcount>intcont)
			{
				String addedPet = returnValue(driver, finalcount);
				if(addedPet.trim().length()>0)
					status = true;
				System.out.println("Scenario Pass: For given 'Petname' & 'Status' New Enty is Created");
			}
			else
			{
				System.out.println("Scenario Fail: For given 'Petname' & 'Status' New Enty is NOT Created");
				status = false;
			}
			System.out.println("-----------------------------------------------------------------");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	public String returnValue(WebDriver driver, int finalcount) {
		By petNameList = By.xpath("//tbody[@class='pet-list']/tr["+finalcount+"]/td");
		petNameList = By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div/table/tbody/tr["+finalcount+"]/td[2]/input");
		By petStatusList = By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div/table/tbody/tr["+finalcount+"]/td[1]/input");
		String lastAddedPet = driver.findElement(petNameList).getAttribute("value");
		String statusLastAdded = driver.findElement(petStatusList).getAttribute("value");

		System.out.println("LAST ADDED: Name: "+statusLastAdded+" Status: "+lastAddedPet);
		return driver.findElement(petNameList).getAttribute("value");
	}

	public int returnCount(WebDriver driver)
	{
		try{
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		driver.navigate().refresh();
		driver.navigate().refresh();
		driver.navigate().refresh();
		driver.navigate().refresh();
		driver.navigate().refresh();
		driver.navigate().refresh();
		driver.navigate().refresh();
		driver.navigate().refresh();
		return driver.findElements(By.xpath("//tbody[@class='pet-list']//tr")).size();	
	}

	public boolean verifyNullPet(WebDriver driver, int intcont) {

		boolean addedPet = false;
		try{
			driver.get("https://qa-petstore.herokuapp.com/");
			int finalcount = returnCount(driver);
			if(finalcount>intcont)
				addedPet = checkListforNull(driver);
			else
				addedPet = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return addedPet;

	}

	private boolean checkListforNull(WebDriver driver) {
		System.out.println("");
		System.out.println("-----------------------------------------------------------------");
		boolean status = false;
		try{
			driver.get("https://qa-petstore.herokuapp.com/");
			int finalcount = returnCount(driver), i = 1;
			while(i<=finalcount && status == false)
			{
				By petNameList = By.xpath("//tbody[@class='pet-list']/tr["+finalcount+"]/td");
				petNameList = By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div/table/tbody/tr["+finalcount+"]/td[2]/input");
				if(driver.findElement(petNameList).getAttribute("value").trim().length()==0)
				{
					//System.out.println("Null Pet Added: "+driver.findElement(petNameList).getAttribute("value").trim().length());
					System.out.println("Scenario Fails: For given empty 'Petname' & 'Status' New Enty is Created");
					status = true;
					i++;
				}
				else
				{
					System.out.println("Scenario Pass: For given empty 'Petname' & 'Status' New Enty is NOT Created");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;


	}

	public void clickEnter(WebDriver driver) {
		driver.findElement(By.xpath("//input[@placeholder='Pet Status']")).sendKeys(Keys.ENTER);
	}

	public void checkFocus(WebDriver driver) {
		System.out.println("------------------------------------------------");
		WebElement activeElement = driver.switchTo().activeElement(); 
		String className =  activeElement.getAttribute("class"); 
		if(className.contains("form-control pet-name"))
		{
			System.out.println("------------------------------------------------");
			System.out.println("The default focus is on Pet Name");
		}
		else
			System.out.println("The default focus is not on Pet Name");
	}



}
