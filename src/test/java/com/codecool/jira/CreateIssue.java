package com.codecool.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateIssue {
    private static final WebDriver driver = new ChromeDriver();
    private static final WebDriverWait wait = new WebDriverWait(driver, 20);
    private static String username;

    static {
        try {
            username = ReadLoginProperties.getUsername();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String password;

    static {
        try {
            password = ReadLoginProperties.getPassword();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CreateIssue() {
    }

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    }

    @Test(testName = "Empty Project Without Summary")
    public static void emptyProjectWithoutSummary() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-password")));
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
        driver.findElement(By.id("create_link")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field")));
        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("EMPTY");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("summary")));
        driver.findElement(By.id("summary")).click();
        driver.findElement(By.id("create-issue-submit")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".error")));
        String errorMessage = driver.findElement((By.cssSelector(".error"))).getText();
        Assert.assertEquals(errorMessage, "You must specify a summary of the issue.");

        driver.findElement(By.linkText("Cancel")).click();
        driver.switchTo().alert().accept();

        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }

    @Test(testName = "With Required Fields Filled")
    public static void withRequiredFieldsFilled() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-password")));
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
        driver.findElement(By.id("create_link")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field")));
        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("MTP");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        driver.findElement(By.id("issuetype-field")).click();
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("issuetype-field")).sendKeys("Task");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("summary")));
        driver.findElement(By.id("summary")).click();
        driver.findElement(By.id("summary")).sendKeys("Testing \"Create Issue\" with all required fields are filled");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("create-issue-submit")));
        driver.findElement(By.id("create-issue-submit")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='aui-flag-container']/div/div/a")));
        driver.findElement(By.xpath("//div[@id='aui-flag-container']/div/div/a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary-val")));
        String issueSummary = driver.findElement((By.id("summary-val"))).getText();
        Assert.assertEquals(issueSummary, "Testing \"Create Issue\" with all required fields are filled");

        driver.findElement(By.xpath("//a[@id='opsbar-operations_more']/span")).click();
        driver.findElement(By.xpath("//aui-item-link[@id='delete-issue']/a/span")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("delete-issue-submit")));
        driver.findElement(By.id("delete-issue-submit")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")));
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }

    @Test(testName = "Story For Toucan")
    public static void storyIssueForToucanProject() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-password")));
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
        driver.findElement(By.id("create_link")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field")));
        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("TOUCAN");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        driver.findElement(By.id("issuetype-field")).click();
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("issuetype-field")).sendKeys("Story");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getPageSource().contains("TOUCAN projekt (TOUCAN)"));
        Assert.assertTrue(driver.getPageSource().contains("Story"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cancel")));
        driver.findElement(By.linkText("Cancel")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")));
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }

    @Test(testName = "Task For Toucan")
    public static void taskIssueForToucanProject() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-password")));
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
        driver.findElement(By.id("create_link")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field")));
        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("TOUCAN");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        driver.findElement(By.id("issuetype-field")).click();
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("issuetype-field")).sendKeys("Task");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getPageSource().contains("TOUCAN projekt (TOUCAN)"));
        Assert.assertTrue(driver.getPageSource().contains("Task"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cancel")));
        driver.findElement(By.linkText("Cancel")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")));
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }

    @Test(testName = "Bug For Toucan")
    public static void bugIssueForToucanProject() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-password")));
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
        driver.findElement(By.id("create_link")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field")));
        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("TOUCAN");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        driver.findElement(By.id("issuetype-field")).click();
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("issuetype-field")).sendKeys("Bug");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getPageSource().contains("TOUCAN projekt (TOUCAN)"));
        Assert.assertTrue(driver.getPageSource().contains("Bug"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cancel")));
        driver.findElement(By.linkText("Cancel")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")));
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }

    @Test(testName = "Story For Jeti")
    public static void storyIssueForJetiProject() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-password")));
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
        driver.findElement(By.id("create_link")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field")));
        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("JETI");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        driver.findElement(By.id("issuetype-field")).click();
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("issuetype-field")).sendKeys("Story");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getPageSource().contains("JETI Project (JETI)"));
        Assert.assertTrue(driver.getPageSource().contains("Story"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cancel")));
        driver.findElement(By.linkText("Cancel")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")));
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }

    @Test(testName = "Task For Jeti")
    public static void taskIssueForJetiProject() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-password")));
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
        driver.findElement(By.id("create_link")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field")));
        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("JETI");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        driver.findElement(By.id("issuetype-field")).click();
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("issuetype-field")).sendKeys("Task");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getPageSource().contains("JETI project (JETI)"));
        Assert.assertTrue(driver.getPageSource().contains("Task"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cancel")));
        driver.findElement(By.linkText("Cancel")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")));
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }

    @Test(testName = "Bug For Jeti")
    public static void bugIssueForJetiProject() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-password")));
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
        driver.findElement(By.id("create_link")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field")));
        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("JETI");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        driver.findElement(By.id("issuetype-field")).click();
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("issuetype-field")).sendKeys("Bug");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getPageSource().contains("JETI Project (JETI)"));
        Assert.assertTrue(driver.getPageSource().contains("Bug"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cancel")));
        driver.findElement(By.linkText("Cancel")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")));
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }

    @Test(testName = "Story For Coala")
    public static void storyIssueForCoalaProject() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-password")));
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
        driver.findElement(By.id("create_link")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field")));
        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("COALA");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        driver.findElement(By.id("issuetype-field")).click();
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("issuetype-field")).sendKeys("Story");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getPageSource().contains("COALA Project (COALA)"));
        Assert.assertTrue(driver.getPageSource().contains("Story"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cancel")));
        driver.findElement(By.linkText("Cancel")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")));
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }

    @Test(testName = "Task For Coala")
    public static void taskIssueForCoalaProject() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-password")));
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
        driver.findElement(By.id("create_link")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field")));
        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("COALA");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        driver.findElement(By.id("issuetype-field")).click();
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("issuetype-field")).sendKeys("Task");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getPageSource().contains("COALA Project (COALA)"));
        Assert.assertTrue(driver.getPageSource().contains("Task"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cancel")));
        driver.findElement(By.linkText("Cancel")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")));
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }

    @Test(testName = "Bug For Coala")
    public static void bugIssueForCoalaProject() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-form-password")));
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("create_link")));
        driver.findElement(By.id("create_link")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("project-field")));
        driver.findElement(By.id("project-field")).click();
        driver.findElement(By.id("project-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("project-field")).sendKeys("COALA");
        driver.findElement(By.id("project-field")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        driver.findElement(By.id("issuetype-field")).click();
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("issuetype-field")).sendKeys("Bug");
        driver.findElement(By.id("issuetype-field")).sendKeys(Keys.ENTER);

        Assert.assertTrue(driver.getPageSource().contains("COALA Project (COALA)"));
        Assert.assertTrue(driver.getPageSource().contains("Bug"));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cancel")));
        driver.findElement(By.linkText("Cancel")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")));
        driver.findElement(By.xpath("//a[@id='header-details-user-fullname']/span/span/img")).click();
        driver.findElement(By.id("log_out")).click();

    }


    @AfterSuite
    public static void cleanup() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
