package com.giorgiburduladze.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class PracticeFormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;

    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By emailInput = By.id("userEmail");
    private final By genderMale = By.cssSelector("label[for='gender-radio-1']");
    private final By mobile = By.id("userNumber");
    private final By submitButton = By.id("submit");

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    @Step("Filling personal details: {0} {1}")
    public void fillPersonalDetails(String fName, String lName, String email, String phone) {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(genderMale).click();
        driver.findElement(mobile).sendKeys(phone);
    }

    @Step("Submit form")
    public void submitForm() {
        WebElement btn = driver.findElement(submitButton);
        js.executeScript("arguments[0].click();", btn);
    }

    @Step("Checking result for label: {0}")
    public String getModalValue(String label) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
        String xpath = "//td[text()='" + label + "']/following-sibling::td";
        return driver.findElement(By.xpath(xpath)).getText();
    }
}