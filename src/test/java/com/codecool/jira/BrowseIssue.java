package com.codecool.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BrowseIssue {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    }

    @Test(testName = "Browse Issue")
    public static void browseIssue() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("user7");
        driver.findElement(By.id("login-form-password")).sendKeys("CoolCanvas19.");
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);
        driver.get("https://jira.codecool.codecanvas.hu/browse/MTP-1");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='key-val']")).getText(), "MTP-1");
    }

    @Test(testName = "Browse Issue (COALA)")
    public static void browseIssueCoala() throws InterruptedException{
        driver.get("https://jira.codecool.codecanvas.hu/browse/COALA-1");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='key-val']")).getText(),"COALA-1");
        driver.get("https://jira.codecool.codecanvas.hu/browse/COALA-2");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='key-val']")).getText(),"COALA-2");
        driver.get("https://jira.codecool.codecanvas.hu/browse/COALA-3");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='key-val']")).getText(),"COALA-3");
    }

    @Test(testName = "Browse Issue (JETI)")
    public static void browseIssueJeti() throws InterruptedException{
        driver.get("https://jira.codecool.codecanvas.hu/browse/JETI-1");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='key-val']")).getText(),"JETI-1");
        driver.get("https://jira.codecool.codecanvas.hu/browse/JETI-2");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='key-val']")).getText(),"JETI-2");
        driver.get("https://jira.codecool.codecanvas.hu/browse/JETI-3");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='key-val']")).getText(),"JETI-3");
    }

    @Test(testName = "Browse Issue (TOUCAN)")
    public static void browseIssueToucan() throws InterruptedException{
        driver.get("https://jira.codecool.codecanvas.hu/browse/TOUCAN-1");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='key-val']")).getText(),"TOUCAN-1");
        driver.get("https://jira.codecool.codecanvas.hu/browse/TOUCAN-2");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='key-val']")).getText(),"TOUCAN-2");
        driver.get("https://jira.codecool.codecanvas.hu/browse/TOUCAN-3");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='key-val']")).getText(),"TOUCAN-3");
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
