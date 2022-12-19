import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;

public class FileUpload { WebDriver driver;

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
        driver.get("http://the-internet.herokuapp.com/upload");
        WebElement selectFile = driver.findElement(By.id("file-upload"));
        selectFile.sendKeys(System.getProperty("user.dir")+
                "/src/test/java/resources/Картинка.png");
        File file = new File("src/test/java/resources/Картинка.png");
       selectFile.sendKeys(file.getAbsolutePath());
       WebElement upLoadFileButton = driver.findElement(By.id("file-submit"));
       upLoadFileButton.click();
       WebElement fileUploaded = driver.findElement(By.xpath("//div[@class=\"panel text-center\"]"));
      // Assert.assertTrue(fileUploaded.isDisplayed(),"Файл загружен");
        Assert.assertEquals(fileUploaded.getText(), "Картинка.png");


    }



}
