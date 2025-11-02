import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

public class CommandsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testDynamicControlsAndDragDrop() {
        
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        By inputField = By.cssSelector("#input-example input[type='text']");
        By toggleButton = By.cssSelector("#input-example button");
        By message = By.id("message");

        driver.findElement(toggleButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(message));
        wait.until(ExpectedConditions.textToBe(toggleButton, "Disable"));
        
        WebElement input = driver.findElement(inputField);
        Assertions.assertTrue(input.isEnabled(), "Input field is not enabled after click.");
        
        String messageText = driver.findElement(message).getText();
        Assertions.assertEquals("It's enabled!", messageText, "Message text is incorrect or not found.");

        System.out.println("Input field enabled and text visible");

        String buttonText = driver.findElement(toggleButton).getText();
        Assertions.assertEquals("Disable", buttonText, "Button text did not change to 'Disable'.");
        
        System.out.println("Button text changed successfully");

        input.sendKeys("Bootcamp");
        Assertions.assertEquals("Bootcamp", input.getAttribute("value"), "Text 'Bootcamp' was not entered.");

        input.clear();
        Assertions.assertEquals("", input.getAttribute("value"), "Input field was not cleared.");
        
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");

        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));

        int yCoordinateA = columnA.getLocation().getY();
        int yCoordinateB = columnB.getLocation().getY();

        Assertions.assertEquals(yCoordinateA, yCoordinateB, "Columns A and B are not aligned on the Y-axis.");

        System.out.println("Columns A and B aligned successfully");
    }
}

