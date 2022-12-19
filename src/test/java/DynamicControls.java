import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class DynamicControls {
    WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        if (driver !=null) {
            driver.quit();
        }
    }


    @Test
    public void testDynamicControl(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait( driver, 20);
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type=checkbox]"));
        WebElement removeButton = driver.findElement(By.cssSelector("[onclick='swapCheckbox()']"));
        removeButton.click();
        wait.until(ExpectedConditions.textToBe(By.id("message"), "It's gone!"));
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        int numberOfElements = driver.findElements(By.cssSelector("[type=checkbox]")).size();
        assertEquals(numberOfElements, 0, "Элемент присутствует на странице");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement Input = driver.findElement(By.xpath("//input[@type=\"text\"]"));
        WebElement enableButton = driver.findElement(By.cssSelector("[onclick='swapInput()']"));
        Assert.assertFalse(Input.isEnabled(),"Строка не активна");
        enableButton.click();
        wait.until(ExpectedConditions.textToBe(By.id("massage"),"It's enabled!"));
        Assert.assertTrue(Input.isEnabled(), "Строка активна");







    }


}
