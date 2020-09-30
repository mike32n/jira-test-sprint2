package com.codecool.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BrowseProjects {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    }

    @Test(testName = "Browse Project")
    public static void browseProject() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("user7");
        driver.findElement(By.id("login-form-password")).sendKeys("CoolCanvas19.");
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);
        driver.get("https://jira.codecool.codecanvas.hu/projects/MTP/summary");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='summary-body']/div/div[2]/dl/dd[2]")).getText(), "MTP");
    }

    @Test(testName = "Browse Project (COALA)")
    public static void browseProjectCoala() throws InterruptedException {
        Thread.sleep(1000);
        driver.get("https://jira.codecool.codecanvas.hu/projects/COALA/summary");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='summary-body']/div/div[2]/dl/dd[2]")).getText(), "COALA");
    }

    @Test(testName = "Browse Project (JETI)")
    public static void browseProjectJeti() throws InterruptedException {
        Thread.sleep(1000);
        driver.get("https://jira.codecool.codecanvas.hu/projects/JETI/summary");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='summary-body']/div/div[2]/dl/dd[2]")).getText(), "JETI");
    }

    @Test(testName = "Browse Project (TOUCAN)")
    public static void browseProjectToucan() throws InterruptedException {
        Thread.sleep(1000);
        driver.get("https://jira.codecool.codecanvas.hu/projects/TOUCAN/summary");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='summary-body']/div/div[2]/dl/dd[2]")).getText(), "TOUCAN");
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
