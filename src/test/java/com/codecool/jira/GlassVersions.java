package com.codecool.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class GlassVersions {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    }

    @Test(testName = "New Project Version In Glass (Empty Optional Fields)")
    public static void newProjectVersionInGlassEmptyOptionalFields() throws InterruptedException, IOException {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        Thread.sleep(500);
        driver.findElement(By.id("login-form-username")).sendKeys(ReadLoginProperties.getUsername());
        driver.findElement(By.id("login-form-password")).sendKeys(ReadLoginProperties.getPassword());
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);

        driver.get("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        Thread.sleep(1500);

        driver.findElement(By.xpath("//section[@id='content']/div/div/div/nav/div/div[2]/ul/li[3]/a")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@id='releases-add__version']/div/input")).sendKeys("Test PP1");
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@id='releases-add__version']/div[5]/button")).click();
        Thread.sleep(500);
        driver.findElement(By.linkText("Test PP1")).click();
        Thread.sleep(1000);
        driver.get("https://jira.codecool.codecanvas.hu/projects/PP1?selectedItem=com.codecanvas.glass:glass");
        Thread.sleep(1500);
        driver.findElement(By.id("aui-uid-2")).click();
        Thread.sleep(1500);
        Assert.assertTrue(driver.getPageSource().contains(driver.findElement(By.linkText("Test PP1")).getText()));

        driver.findElement(By.xpath("//section[@id='content']/div/div/div/nav/div/div[2]/ul/li[3]/a")).click();
        Thread.sleep(500);
        driver.findElement(By.linkText("Operations")).click();
        Thread.sleep(500);
        driver.findElement(By.linkText("Delete")).click();
        Thread.sleep(500);
        driver.findElement(By.id("submit")).click();
        Thread.sleep(500);

        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();
    }

    @AfterSuite
    public static void cleanup() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
