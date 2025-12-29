package com.giorgiburduladze.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.giorgiburduladze.base.BaseTest;
import com.giorgiburduladze.pages.AlertPage;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Regression")
@Feature("Alerts")
public class AlertTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    public void alertWithTextboxTest() {
        getDriver().get("https://demo.automationtesting.in/Alerts.html");
        AlertPage alertPage = new AlertPage(getDriver());

        String name = "Giorgi Burduladze";
        alertPage.openPromptTab();
        alertPage.enterNameInPrompt(name);

        Assert.assertTrue(alertPage.getResultText().contains(name));
    }
}