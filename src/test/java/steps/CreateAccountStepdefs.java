package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class CreateAccountStepdefs {

    private WebDriver driver;
    private WebDriverWait wait;

    private WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Given("I am using {string} browser")
    public void iAmUsingBrowser(String browser) {

        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        }
        if (browser.equals("edge")) {
            driver = new EdgeDriver();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I am currently on the website {string}")
    public void iAmCurrentlyOnTheWebsite(String url) {
        driver.get(url);
    }

    @When("I enter all required information and click {string}")
    public void iEnterAllRequiredInformationAndClick(String arg0) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String dateOfBirth=".custom-date";
        String firstName="input[id=\"member_firstname\"][name=\"Forename\"]\n";
        String lastName="input[id=\"member_lastname\"][name=\"Surname\"]";
        String email ="input[id=\"member_emailaddress\"][name=\"EmailAddress\"]";
        String confirmEmail="input[id=\"member_confirmemailaddress\"][name=\"ConfirmEmailAddress\"]";
        String password="#signupunlicenced_password";
        String retypePassword="#signupunlicenced_confirmpassword";
        String confirmTermsAndConditions="label[for='sign_up_25']";
        String iAmAgedOver18="label[for='sign_up_26']";
        String confirmCodeOfEthics="label[for='fanmembersignup_agreetocodeofethicsandconduct']";
        String confirmAndJoin ="#signup_form > div.form-actions.noborder > input";

        driver.findElement(By.cssSelector(dateOfBirth))
                .sendKeys("04/10/1986", Keys.ENTER);
        driver.findElement(By.cssSelector(firstName))
                .sendKeys("Alex", Keys.ENTER);
        driver.findElement(By.cssSelector(lastName))
                .sendKeys("Palm", Keys.ENTER);

        String randomEmail = "test" + System.currentTimeMillis() + "@inbox.lv";
        driver.findElement(By.cssSelector(email))
                .sendKeys(randomEmail);
        driver.findElement(By.cssSelector(confirmEmail))
                .sendKeys(randomEmail);
        driver.findElement(By.cssSelector(password))
                .sendKeys("Abcd123");
        driver.findElement(By.cssSelector(retypePassword))
                .sendKeys("Abcd123");

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();

        Thread.sleep(500);
        driver.findElement(By.cssSelector(confirmTermsAndConditions)).click();

        Thread.sleep(500);
        driver.findElement(By.cssSelector(iAmAgedOver18)).click();

        Thread.sleep(500);
        driver.findElement(By.cssSelector(confirmCodeOfEthics)).click();

        Thread.sleep(500);
        driver.findElement(By.cssSelector(confirmAndJoin)).click();

        Thread.sleep(500);

        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(confirmTermsAndConditions))).click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(iAmAgedOver18))).click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(confirmCodeOfEthics))).click();
    }

    @Then("I see {string}")
    public void iSee(String confirmation) {

        WebElement thankYouMessage = waitForElementVisible(
                By.xpath("//h2[contains(text(), 'THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND')]")
        );

        // Проверка, что элемент найден и текст соответствует ожиданию
        //Assert.assertTrue("Сообщение не отображается", thankYouMessage.isDisplayed());
        Assert.assertEquals(confirmation, thankYouMessage.getText().trim());
        System.out.println("Account is Created");

    }

    @When("I enter all required information except {string} and click {string}")
    public void iEnterAllRequiredInformationExceptAndClick(String arg0, String arg1) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String dateOfBirth=".custom-date";
        String firstName="input[id=\"member_firstname\"][name=\"Forename\"]\n";
        String lastName="input[id=\"member_lastname\"][name=\"Surname\"]";
        String email ="input[id=\"member_emailaddress\"][name=\"EmailAddress\"]";
        String confirmEmail="input[id=\"member_confirmemailaddress\"][name=\"ConfirmEmailAddress\"]";
        String password="#signupunlicenced_password";
        String retypePassword="#signupunlicenced_confirmpassword";
        String confirmTermsAndConditions="label[for='sign_up_25']";
        String iAmAgedOver18="label[for='sign_up_26']";
        String confirmCodeOfEthics="label[for='fanmembersignup_agreetocodeofethicsandconduct']";
        String confirmAndJoin ="#signup_form > div.form-actions.noborder > input";

        driver.findElement(By.cssSelector(dateOfBirth))
                .sendKeys("04/10/1986", Keys.ENTER);
        driver.findElement(By.cssSelector(firstName))
                .sendKeys("Alex", Keys.ENTER);

        String randomEmail = "test" + System.currentTimeMillis() + "@inbox.lv";
        driver.findElement(By.cssSelector(email))
                .sendKeys(randomEmail);
        driver.findElement(By.cssSelector(confirmEmail))
                .sendKeys(randomEmail);
        driver.findElement(By.cssSelector(password))
                .sendKeys("Abcd123");
        driver.findElement(By.cssSelector(retypePassword))
                .sendKeys("Abcd123");

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();

        Thread.sleep(500);
        driver.findElement(By.cssSelector(confirmTermsAndConditions)).click();

        Thread.sleep(500);
        driver.findElement(By.cssSelector(iAmAgedOver18)).click();

        Thread.sleep(500);
        driver.findElement(By.cssSelector(confirmCodeOfEthics)).click();

        Thread.sleep(500);
        driver.findElement(By.cssSelector(confirmAndJoin)).click();

        Thread.sleep(500);

    }

    @Then("I see error {string}")
    public void iSeeError(String message) {
        WebElement errorMessage = waitForElementVisible(
                By.cssSelector("span[for=\"member_lastname\"]\n"));

        Assert.assertEquals(message,errorMessage.getText().trim());
        System.out.println("Last name is missing");

    }

    @When("I enter {} and {}:")
    public void iEnterAnd(String Password, String ConfirmPassword) {

        String newPassword="#signupunlicenced_password";
        String retypePassword="#signupunlicenced_confirmpassword";

        driver.findElement(By.cssSelector(newPassword))
                .sendKeys(Password,Keys.ENTER);
        driver.findElement(By.cssSelector(retypePassword))
                .sendKeys(ConfirmPassword,Keys.ENTER);

    }

    @Then("I should see {}")
    public void iShouldSee(String message) {


        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(), '" + message + "')]"));

        String actualMessage = errorMessage.getText();

        assertEquals(message, actualMessage);
    }
    }




