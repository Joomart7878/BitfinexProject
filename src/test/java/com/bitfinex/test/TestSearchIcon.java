package com.bitfinex.test;

import com.bitfinex.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSearchIcon {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome");

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void searchEngine() throws InterruptedException {

        //Main page executed
        driver.get("https://www.bitfinex.com/");

        Thread.sleep(3000);
        //search button clicks
        WebElement search = driver.findElement(By.xpath("(//a[@class='landing-tickers__search'])[1]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", search);

        // Send text into search button
        WebElement typeSearch = driver.findElement(By.xpath("(//input[@class='bp3-input'])[1]"));
        executor.executeScript("arguments[0].click();", typeSearch);
        executor.executeScript("arguments[0].value='Unus Sed Leo';",typeSearch);

        //Clicks on the result
        driver.findElement(By.xpath("(//table[@class='bp3-html-table bp3-html-table-condensed bp3-interactive'])[1]")).click();

        //Gets information about URL
        String actualTitle = driver.getCurrentUrl();

        //Assertion with TestNG
        Assert.assertEquals(actualTitle, "https://trading.bitfinex.com/t/LEO:USD?demo=true");
    }
}
