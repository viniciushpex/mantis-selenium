package support;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

//Classe para geração de Screenshots
public class Screenshot {
    public static void take(WebDriver driver, String file){
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(file));
        }catch (Exception e){
            System.out.println("Problema ao copiar arquivo para pasta: " + e.getMessage());
        }
    }

}
