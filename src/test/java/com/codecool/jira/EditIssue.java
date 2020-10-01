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
        driver.findElement(By.id("login-form-username")).sendKeys("user6");
        driver.findElement(By.id("login-form-password")).sendKeys("CoolCanvas19.");
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);
        driver.get("https://jira.codecool.codecanvas.hu/browse/MTP-1654");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='edit-issue']")).getText(), "Edit");
        driver.findElement(By.xpath("//a[@id='edit-issue']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("summary")).clear();
        driver.findElement(By.id("summary")).sendKeys("This is a test for editing issues");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='edit-issue-submit']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='summary-val']")).getText(), "This is a test for editing issues");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@id='edit-issue']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("summary")).clear();
        driver.findElement(By.xpath("//input[@id='summary']")).sendKeys("Test issue");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='edit-issue-submit']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='summary-val']")).getText(), "Test issue");
    }


    @Test(testName = "Edit Issue (COALA)")
    public static void editIssueCoala() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/browse/COALA-586");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='edit-issue']")).getText(), "Edit");
        driver.findElement(By.xpath("//a[@id='edit-issue']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("summary")).clear();
        driver.findElement(By.xpath("//input[@id='summary']")).sendKeys("This is a test for editing issues");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='edit-issue-submit']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='summary-val']")).getText(), "This is a test for editing issues");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@id='edit-issue']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("summary")).clear();
        driver.findElement(By.xpath("//input[@id='summary']")).sendKeys("Test issue");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='edit-issue-submit']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='summary-val']")).getText(), "Test issue");
    }

    @Test(testName = "Edit Issue (JETI)")
    public static void editIssueJeti() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/browse/JETI-405");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='edit-issue']")).getText(), "Edit");
        driver.findElement(By.xpath("//a[@id='edit-issue']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("summary")).clear();
        driver.findElement(By.xpath("//input[@id='summary']")).sendKeys("This is a test for editing issues");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='edit-issue-submit']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='summary-val']")).getText(), "This is a test for editing issues");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@id='edit-issue']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("summary")).clear();
        driver.findElement(By.xpath("//input[@id='summary']")).sendKeys("Test issue");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='edit-issue-submit']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='summary-val']")).getText(), "Test issue");
    }

    @Test(testName = "Edit Issue (TOUCAN)")
    public static void editIssueToucan() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/browse/TOUCAN-580");
        Assert.assertEquals(driver.findElement(By.xpath("//a[@id='edit-issue']")).getText(), "Edit");
        driver.findElement(By.xpath("//a[@id='edit-issue']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("summary")).clear();
        driver.findElement(By.xpath("//input[@id='summary']")).sendKeys("This is a test for editing issues");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='edit-issue-submit']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='summary-val']")).getText(), "This is a test for editing issues");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@id='edit-issue']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("summary")).clear();
        driver.findElement(By.xpath("//input[@id='summary']")).sendKeys("Test issue");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='edit-issue-submit']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='summary-val']")).getText(), "Test issue");
    }


    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
