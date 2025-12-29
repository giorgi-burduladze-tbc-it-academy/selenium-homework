package com.giorgiburduladze.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.giorgiburduladze.base.BaseTest;
import com.giorgiburduladze.pages.PracticeFormPage;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Regression")
@Feature("Forms")
public class FormTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void fillAndAssertFormTest() {
        getDriver().get("https://demoqa.com/automation-practice-form");
        PracticeFormPage formPage = new PracticeFormPage(getDriver());

        formPage.fillPersonalDetails("Giorgi", "Burduladze", "giorgi@test.com", "1234567890");
        formPage.submitForm();

        Assert.assertEquals(formPage.getModalValue("Student Name"), "Giorgi Burduladze");
        Assert.assertEquals(formPage.getModalValue("Mobile"), "1234567890");
    }
}