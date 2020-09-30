package com.codecool.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class EditIssue {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    }

    @Test(testName = "Edit Issue")
    public static void editIssue() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("user7");
        driver.findElement(By.id("login-form-password")).sendKeys("CoolCanvas19.");
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);
        driver.get("https://jira.codecool.codecanvas.hu/browse/MTP-1654");
        Assert.assertEquals(driver.findElement(By.linkText("Edit")).getText(), "Edit");
        driver.findElement(By.xpath("//a[@id='edit-issue']")).click();
    }
/*
    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }*/
}
