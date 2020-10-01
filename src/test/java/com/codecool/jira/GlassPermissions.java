package com.codecool.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class GlassPermissions {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    }

    @Test(testName = "Permission Verification")
    public static void permissionVerification() throws InterruptedException, IOException {
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
        driver.findElement(By.xpath("//section[@id='content']/div/div/div[2]/a")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("view_project_permissions")).click();
        Thread.sleep(1500);

        String browse = driver.findElement(By.xpath("//div[@id='project-config-panel-permissions']/jira-permissions-table/div/table/tbody/tr[2]/td[2]/dl/dd")).getText();
        String create = driver.findElement(By.xpath("//div[@id='project-config-panel-permissions']/jira-permissions-table/div[2]/table/tbody/tr[4]/td[2]/dl/dd")).getText();
        String edit = driver.findElement(By.xpath("//div[@id='project-config-panel-permissions']/jira-permissions-table/div[2]/table/tbody/tr[6]/td[2]/dl/dd")).getText();

        driver.findElement(By.xpath("//section[@id='content']/div/div/div/nav/div/div[2]/ul/li[7]/a")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[@id='glass-permissions-nav']/a")).click();
        Thread.sleep(1500);

        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='glass-permissions-panel']/div/table/thead/tr/th[3]/div/span/b")).getText().contains(browse));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='glass-permissions-panel']/div/table/thead/tr/th[3]/div/span/b")).getText().contains(create));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='glass-permissions-panel']/div/table/thead/tr/th[3]/div/span/b")).getText().contains(edit));

        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();
    }

    @AfterSuite
    public static void cleanup() {
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
