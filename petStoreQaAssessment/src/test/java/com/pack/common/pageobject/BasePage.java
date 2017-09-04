package com.pack.common.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
	protected WebDriver driver;
	//private By signInButton = By.linkText("Sign in");

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickImagesLink() {
		driver.navigate().refresh();
		int intcont = driver.findElements(By.xpath("//tbody[@class='pet-list']//tr")).size();
		for(int i = 0; i < intcont; i++)
		{
			System.out.println("TEXT: "+driver.findElement(By.xpath("//tbody[@class='pet-list']/tr[final]/td//span")).getText());
		}
	}

	public String getPageTitle(){
		String title = driver.getTitle();
		return title;
	}

	public boolean verifyBasePageTitle() {
		String expectedPageTitle="Petstore - Assignment";
		return getPageTitle().contains(expectedPageTitle);
	}
}