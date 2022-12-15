import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Frames {
    WebDriver driver;

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void testIframe() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get("http://the-internet.herokuapp.com/iframe");
        WebElement Iframe = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(Iframe);
        WebElement textInFrame = driver.findElement(By.id("tinymce"));
        textInFrame.click();
        Assert.assertEquals(textInFrame.getText(), "Your content goes here.");
        driver.switchTo().defaultContent();


    }
}