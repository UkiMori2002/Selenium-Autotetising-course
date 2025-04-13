package module_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.time.Duration;

public class BookingSearchTest {
    public static void main(String[] args) {
        WebDriverManager.firefoxdriver().setup();

        File profileDir = new File("C:\\Users\\mitru\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\mhaucy8l.selenium-profile");
        FirefoxProfile profile = new FirefoxProfile(profileDir);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        WebDriver driver = new FirefoxDriver(options);

        try {
            driver.get("https://www.booking.com/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Ввод города
            WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@id=':rj:']")));
            cityInput.click();
            String country = "Таиланд";
            cityInput.sendKeys("Таиланд");

            // Ожидание появления нужной нам страны
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//div[@data-testid='autocomplete-result']//div[contains(text(),'" + country + "')]"),
                    country));

            // Клик по найденной строке
            WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@data-testid='autocomplete-result']//div[contains(text(),'" + country + "')]")));
            suggestion.click();

            // Выбрать дату заезда (например, 20 мая 2025)
            WebElement checkInDate = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@data-date='2025-05-20']")));
            checkInDate.click();

            // Выбрать дату отъезда (например, 23 мая 2025)
            WebElement checkOutDate = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@data-date='2025-05-23']")));
            checkOutDate.click();

            // Нажать кнопку поиска
            WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='e22b782521 d12ff5f5bf']")));
            searchBtn.click();

            // Вывести название первого отеля
            WebElement firstHotel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//div[@data-testid='title'])[1]")));
            System.out.println("Найден отель: " + firstHotel.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
