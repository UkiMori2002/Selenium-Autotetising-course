package module_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.time.Duration;

public class BookingAccountCheck {
    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();

        // Путь к профилю Firefox
        File profileDir = new File("C:\\Users\\mitru\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\mhaucy8l.selenium-profile");
        FirefoxProfile profile = new FirefoxProfile(profileDir);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        WebDriver driver = new FirefoxDriver(options);

        try {
            driver.get("https://www.booking.com");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Поиск элемента по XPath
            WebElement accountText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//div[contains(@class, 'a3332d346a')])[1]")
            ));

            System.out.println("Найден текст: " + accountText.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
