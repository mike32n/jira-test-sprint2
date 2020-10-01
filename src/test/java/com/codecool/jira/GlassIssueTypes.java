package com.codecool.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class GlassIssueTypes {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        // ChromeDriver location set up in Utils class
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    }

    @Test(testName = "Glass Issue Type Scheme")
    public static void issueTypeScheme() throws InterruptedException, IOException {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys(ReadLoginProperties.getUsername());
        driver.findElement(By.id("login-form-password")).sendKeys(ReadLoginProperties.getPassword());
        driver.findElement(By.id("login")).click();
        Thread.sleep(1000);
        driver.get("https://jira.codecool.codecanvas.hu/plugins/servlet/project-config/PP5/issuetypes");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='project-config-panel-issuetypes']/div/div[3]/div")).getText(),"Scheme used by this project:PP5: Scrum Issue Type Scheme");
        Assert.assertEquals(driver.findElement(By.xpath("(//a[contains(text(),'Story')])[2]")).getText(),"Story");
        Assert.assertEquals(driver.findElement(By.xpath("(//a[contains(text(),'Bug')])[2]")).getText(),"Bug");
        Assert.assertEquals(driver.findElement(By.xpath("(//a[contains(text(),'Epic')])[2]")).getText(),"Epic");
        Assert.assertEquals(driver.findElement(By.xpath("(//a[contains(text(),'Task')])[2]")).getText(),"Task");
        Assert.assertEquals(driver.findElement(By.xpath("(//a[contains(text(),'Sub-task')])[2]")).getText(),"Sub-task");
        driver.get("https://jira.codecool.codecanvas.hu/projects/PP5?selectedItem=com.codecanvas.glass:glass");
        driver.findElement(By.xpath("//a[@id='aui-uid-3']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='glass-general-schemes-panel']/div/table/tbody/tr/td[2]")).getText(),"PP5: Scrum Issue Type Scheme");
        driver.findElement(By.xpath("//li[@id='glass-workflow-nav']/a/div")).click();
        Assert.assertEquals(driver.findElement(By.linkText("Task")).getText(),"Task");
        Assert.assertEquals(driver.findElement(By.linkText("Sub-task")).getText(),"Sub-task");
        Assert.assertEquals(driver.findElement(By.linkText("Story")).getText(),"Story");
        Assert.assertEquals(driver.findElement(By.linkText("Bug")).getText(),"Bug");
        Assert.assertEquals(driver.findElement(By.linkText("Epic")).getText(),"Epic");
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
