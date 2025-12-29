package com.giorgiburduladze.pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class AlertPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By alertTextboxTab = By.xpath("//a[contains(text(), 'Alert with Textbox')]");
    private final By promptButton = By.cssSelector("button.btn.btn-info");
    private final By resultText = By.id("demo1");

    public AlertPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Clicking on 'Alert with Textbox' tab")
    public void openPromptTab() {
        driver.findElement(alertTextboxTab).click();
    }

    @Step("Opening prompt and entering name: {0}")
    public void enterNameInPrompt(String name) {
        driver.findElement(promptButton).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(name);
        alert.accept();
    }

    public String getResultText() {
        return driver.findElement(resultText).getText();
    }
}