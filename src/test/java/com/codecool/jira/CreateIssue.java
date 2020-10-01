package com.codecool.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateIssue {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    }

    @Test(testName = "Empty Project Without Summary")
    public static void emptyProjectWithoutSummary() throws InterruptedException, IOException {
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys(ReadLoginProperties.getUsername());
        driver.findElement(By.id("login-form-password")).sendKeys(ReadLoginProperties.getPassword());
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("create_link")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("EMPTY");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.id("summary")).click();
        driver.findElement(By.id("create-issue-submit")).click();
        Thread.sleep(2000);

        String errorMessage = driver.findElement((By.cssSelector(".error"))).getText();
        Assert.assertEquals(errorMessage, "You must specify a summary of the issue.");

        driver.findElement(By.xpath("//div[@id='create-issue-dialog']/div[2]/div/div/form/div[2]/div/a")).click();
        driver.switchTo().alert().accept();

        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }

    @Test(testName = "With Required Fields Filled")
    public static void withRequiredFieldsFilled() throws InterruptedException, IOException {
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys(ReadLoginProperties.getUsername());
        driver.findElement(By.id("login-form-password")).sendKeys(ReadLoginProperties.getPassword());
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("create_link")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("MTP");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.id("issuetype-field")).click();
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("issuetype-field")).sendKeys("Task");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.id("summary")).click();
        driver.findElement(By.id("summary")).sendKeys("Testing \"Create Issue\" with all required fields are filled");
        Thread.sleep(1000);
        driver.findElement(By.id("create-issue-submit")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='aui-flag-container']/div/div/a")).click();
        Thread.sleep(1000);

        String issueSummary = driver.findElement((By.id("summary-val"))).getText();
        Assert.assertEquals(issueSummary, "Testing \"Create Issue\" with all required fields are filled");

        driver.findElement(By.xpath("//a[@id='opsbar-operations_more']/span")).click();
        driver.findElement(By.xpath("//aui-item-link[@id='delete-issue']/a/span")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("delete-issue-submit")).click();

        Thread.sleep(2000);


        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }


    @AfterSuite
    public static void cleanup() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
