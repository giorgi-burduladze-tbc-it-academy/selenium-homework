package com.giorgiburduladze.homework2;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTests extends BaseTest {

    @Test
    public void alertWithTextboxTest() {
        getDriver().get("https://demo.automationtesting.in/Alerts.html");

        getDriver().findElement(By.xpath("//a[contains(text(), 'Alert with Textbox')]")).click();

        getDriver().findElement(By.cssSelector("button.btn.btn-info")).click();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        String fullName = "Giorgi Burduladze";
        alert.sendKeys(fullName);

        alert.accept();

        WebElement resultElement = getDriver().findElement(By.id("demo1"));
        String expectedText = "Hello " + fullName + " How are you today";
        String actualText = resultElement.getText();

        Assert.assertEquals(actualText, expectedText, "The result text is incorrect.");
    }
}