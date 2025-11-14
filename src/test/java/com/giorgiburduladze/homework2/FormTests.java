package com.giorgiburduladze.homework2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class FormTests extends BaseTest {

    @Test
    public void fillAndAssertFormTest() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        getDriver().get("https://demoqa.com/automation-practice-form");

        Map<String, String> testData = new HashMap<>();
        testData.put("Student Name", "Giorgi Burduladze");
        testData.put("Student Email", "giorgi@example.com");
        testData.put("Gender", "Male");
        testData.put("Mobile", "1234567890");
        testData.put("Date of Birth", "15 January,1990");
        testData.put("Subjects", "Maths");
        testData.put("Hobbies", "Music");
        testData.put("Address", "123 Tbilisi, Georgia");
        testData.put("State and City", "NCR Delhi");

        getDriver().findElement(By.id("firstName")).sendKeys("Giorgi");
        getDriver().findElement(By.id("lastName")).sendKeys("Burduladze");
        getDriver().findElement(By.id("userEmail")).sendKeys(testData.get("Student Email"));

        getDriver().findElement(By.cssSelector("label[for='gender-radio-1']")).click();
        getDriver().findElement(By.id("userNumber")).sendKeys(testData.get("Mobile"));

        getDriver().findElement(By.id("dateOfBirthInput")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("react-datepicker__year-select"))).sendKeys("1990");
        getDriver().findElement(By.className("react-datepicker__month-select")).sendKeys("January");
        getDriver().findElement(By.className("react-datepicker__day--015")).click();

        WebElement subjectsInput = getDriver().findElement(By.id("subjectsInput"));
        subjectsInput.sendKeys(testData.get("Subjects"));
        subjectsInput.sendKeys(Keys.ENTER);

        getDriver().findElement(By.cssSelector("label[for='hobbies-checkbox-3']")).click();

        getDriver().findElement(By.id("currentAddress")).sendKeys(testData.get("Address"));

        js.executeScript("window.scrollBy(0, 500);");

        getDriver().findElement(By.id("state")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='NCR']"))).click();

        getDriver().findElement(By.id("city")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Delhi']"))).click();

        WebElement submitButton = getDriver().findElement(By.id("submit"));
        js.executeScript("arguments[0].click();", submitButton);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));

        Assert.assertEquals(getModalText("Student Name"), testData.get("Student Name"));
        Assert.assertEquals(getModalText("Student Email"), testData.get("Student Email"));
        Assert.assertEquals(getModalText("Gender"), testData.get("Gender"));
        Assert.assertEquals(getModalText("Mobile"), testData.get("Mobile"));
        Assert.assertEquals(getModalText("Date of Birth"), testData.get("Date of Birth"));
        Assert.assertEquals(getModalText("Subjects"), testData.get("Subjects"));
        Assert.assertEquals(getModalText("Hobbies"), testData.get("Hobbies"));
        Assert.assertEquals(getModalText("Address"), testData.get("Address"));
        Assert.assertEquals(getModalText("State and City"), testData.get("State and City"));

        getDriver().findElement(By.id("closeLargeModal")).click();
    }

    private String getModalText(String label) {
        String xpath = "//td[text()='" + label + "']/following-sibling::td";
        return getDriver().findElement(By.xpath(xpath)).getText();
    }
}