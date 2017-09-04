package com.pack.common.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.pack.base.*;
import com.pack.common.pageobject.BasePage;
import com.pack.common.pageobject.CreatePetPage;

public class BasePageTest extends TestBaseSetup{
	
	private WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver=getDriver();
	}
	
	@Parameters({ "petname3", "petStatus3"})
	@Test
	public void createNullPet(String petname3, String petStatus3) {
		
		CreatePetPage createPet = new CreatePetPage(driver);
		int intcont = createPet.returnCount(driver);
		createPet.enterPet(driver, petname3);
		createPet.enterStatus(driver, petStatus3);
		createPet.clickCreate(driver);
		try
		{
		Assert.assertTrue(createPet.verifyNullPet(driver, intcont), "Null Pet was not created");
		}
		catch(Exception e)
		{
			System.out.println("Exception due to Null Pet creation!");
		}
	}
	
	@Parameters({ "petname1", "petStatus1"})
	@Test
	public void createValidPet(String petname1, String petStatus1) {
		
		CreatePetPage createPet = new CreatePetPage(driver);
		int intcont = createPet.returnCount(driver);
		createPet.enterPet(driver, petname1);
		createPet.enterStatus(driver, petStatus1);
		createPet.clickCreate(driver);
		Assert.assertTrue(createPet.verifyNewPet(driver, intcont, "Create Button"), "New Pet was not created");
	}
	
	@Parameters({ "petname2", "petStatus2"})
	@Test
	public void createPetEnter(String petname2, String petStatus2) {
		
		CreatePetPage createPet = new CreatePetPage(driver);
		int intcont = createPet.returnCount(driver);
		createPet.enterPet(driver, petname2);
		createPet.enterStatus(driver, petStatus2);
		createPet.clickEnter(driver);
		Assert.assertTrue(createPet.verifyNewPet(driver, intcont, "Enter"), "New Pet was created using Enter");
	}

	@Test
	public void checkFocus()
	{
		CreatePetPage create = new CreatePetPage(driver);
		System.out.println("**********************************************");
		System.out.println("Validation: US03 - Be able to add a new pet");
		System.out.println("**********************************************");
		System.out.println("Check Focus");
		driver.navigate().refresh();
		create.checkFocus(driver);
	}
	@Test
	public void verifyHomePage() {
		System.out.println("Home page test...");
		BasePage basePage = new BasePage(driver);
		Assert.assertTrue(basePage.verifyBasePageTitle(), "Home page title doesn't match");
	}
}
