package com.codecool.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.InputSource;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class GlassComponents {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    }

    @Test(testName = "Glass Components Management")
    public static void componentsManagement() throws InterruptedException, IOException {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys(ReadLoginProperties.getUsername());
        driver.findElement(By.id("login-form-password")).sendKeys(ReadLoginProperties.getPassword());
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);
        driver.get("https://jira.codecool.codecanvas.hu/projects/PP5/summary");
        driver.findElement(By.xpath("//li[6]/a/span")).click();
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("glass test");
        driver.findElement(By.xpath("//input[@name='description']")).sendKeys("this is a test");
        driver.findElement(By.id("assigneeType-field")).click();
        driver.findElement(By.id("assigneeType-field")).sendKeys("Project default (Project lead)");
        driver.findElement(By.cssSelector(".aui-page-header-fixed")).click();
        driver.findElement(By.xpath("//form[@id='components-add__component']//button")).click();
        driver.get("https://jira.codecool.codecanvas.hu/projects/PP5?selectedItem=com.codecanvas.glass:glass");
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(.,'glass test')]")).getText(),"glass test");
        driver.findElement(By.xpath("//li[6]/a/span")).click();
        driver.findElement(By.xpath("//input[@id='component-filter-text']")).sendKeys("glass test");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".item-state-ready:nth-child(1) .aui-button")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Delete")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='submit']")).click();
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
