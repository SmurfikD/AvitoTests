import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class EcoImpactCounterTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @BeforeEach
    public void navigateToPage() {
        driver.get("https://www.avito.ru/avito-care/eco-impact");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void takeScreenshot(String fileName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
           FileUtils.copyFile(srcFile, new File("output/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWaterSavingsCounter() {
        WebElement waterCounter = driver.findElement(By.cssSelector("selector_for_water_counter"));
        waterCounter.isDisplayed();
        takeScreenshot("TC01.png");
    }

    @Test
    public void testCO2SavingsCounter() {
        WebElement co2Counter = driver.findElement(By.cssSelector("selector_for_co2_counter"));
        co2Counter.isDisplayed();
        takeScreenshot("TC02.png");
    }

    @Test
    public void testEnergySavingsCounter() {
        WebElement energyCounter = driver.findElement(By.cssSelector("selector_for_energy_counter"));
        energyCounter.isDisplayed();
        takeScreenshot("TC03.png");
    }
}
