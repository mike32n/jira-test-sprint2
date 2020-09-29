package com.codecool.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Login {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    }

    @Test(testName = "Successful Login")
    public static void successfulLogin() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("user6");
        driver.findElement(By.id("login-form-password")).sendKeys("CoolCanvas19.");
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);
        driver.get("https://jira.codecool.codecanvas.hu/secure/ViewProfile.jspa");
        Assert.assertEquals(driver.findElement(By.id("up-d-username")).getText(), "user6");
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();
    }

    @Test(testName = "Alternative Login")
    public static void alternativeLogin() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        driver.findElement(By.id("login-form-username")).sendKeys("user6");
        driver.findElement(By.id("login-form-password")).sendKeys("CoolCanvas19.");
        driver.findElement(By.id("login-form-submit")).click();
        Thread.sleep(1000);
        driver.get("https://jira.codecool.codecanvas.hu/secure/ViewProfile.jspa");
        Assert.assertEquals(driver.findElement(By.id("up-d-username")).getText(), "user6");
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();
    }

    @Test(testName = "No Password Login")
    public static void noPasswordLogin() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("user6");
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='usernameerror']/p")).getText(), "Sorry, your username and password are incorrect - please try again.");
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("user6");
        driver.findElement(By.id("login-form-password")).sendKeys("CoolCanvas19.");
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();
    }

    @AfterSuite
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
