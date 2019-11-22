package framework.utils;

import framework.driver.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import static framework.utils.Waiters.timeOut;

public class FileUtils {
    private static WebDriver driver = Browser.getDriver();
    private static String expectedFileName = "steam_latest.deb";
    private static String path = System.getProperty("user.dir") + File.separator + "Output" + File.separator;
    private static Path downloadsDirectory = Paths.get(path);


    public static void waitForFileDownload() {
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);

        File fileToCheck = downloadsDirectory
                .resolve(expectedFileName)
                .toFile();

        wait.until((driver) -> fileToCheck.exists());

    }

    public static void clearOutput() {
        File fileToCheck = downloadsDirectory
                .resolve(expectedFileName)
                .toFile();
        fileToCheck.delete();
    }

    public static Path getDownloadsDirectory(){
        return downloadsDirectory;
    }
}
