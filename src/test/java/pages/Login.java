package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
//    public void clickSignInButton(){
//        driver.findElement(By.id("SubmitLogin")).click();
//    }
    public void logout(){
        driver.findElement(By.xpath("//a[@class='logout']"));
    }
}
