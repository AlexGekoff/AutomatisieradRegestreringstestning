package steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class CreateAccountStepdefs {

    WebDriver driver;
    WebDriverWait wait;

    private WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
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

    @When("I enter all required information and click confirm and join")
    public void iEnterAllRequiredInformationAndClickConfirmAndJoin() {

        String dateOfBirth = ".custom-date";
        String firstName = "input[id=\"member_firstname\"][name=\"Forename\"]\n";
        String lastName = "input[id=\"member_lastname\"][name=\"Surname\"]";
        String email = "input[id=\"member_emailaddress\"][name=\"EmailAddress\"]";
        String confirmEmail = "input[id=\"member_confirmemailaddress\"][name=\"ConfirmEmailAddress\"]";
        String password = "#signupunlicenced_password";
        String retypePassword = "#signupunlicenced_confirmpassword";
        String confirmTermsAndConditions = "label[for='sign_up_25']";
        String iAmAgedOver18 = "label[for='sign_up_26']";
        String confirmCodeOfEthics = "label[for='fanmembersignup_agreetocodeofethicsandconduct']";
        String confirmAndJoin = "#signup_form > div.form-actions.noborder > input";

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


        waitForElementVisible(
                By.cssSelector(confirmTermsAndConditions)).click();
        waitForElementVisible(
                By.cssSelector(iAmAgedOver18)).click();
        waitForElementVisible(
                By.cssSelector(confirmCodeOfEthics)).click();
        waitForElementVisible(
                By.cssSelector(confirmAndJoin)).click();

    }

    @Then("I see {string}")
    public void iSee(String confirmation) {

        WebElement thankYouMessage = waitForElementVisible(
                By.xpath("//h2[contains(text(), 'THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND')]")
        );

        assertEquals(confirmation, thankYouMessage.getText().trim());

    }

    @When("Last Name is Missing")
    public void lastNameIsMissing() {

        String dateOfBirth = ".custom-date";
        String firstName = "input[id=\"member_firstname\"][name=\"Forename\"]\n";
        String email = "input[id=\"member_emailaddress\"][name=\"EmailAddress\"]";
        String confirmEmail = "input[id=\"member_confirmemailaddress\"][name=\"ConfirmEmailAddress\"]";
        String password = "#signupunlicenced_password";
        String retypePassword = "#signupunlicenced_confirmpassword";
        String confirmTermsAndConditions = "label[for='sign_up_25']";
        String iAmAgedOver18 = "label[for='sign_up_26']";
        String confirmCodeOfEthics = "label[for='fanmembersignup_agreetocodeofethicsandconduct']";
        String confirmAndJoin = "#signup_form > div.form-actions.noborder > input";

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


        waitForElementVisible(
                By.cssSelector(confirmTermsAndConditions)).click();
        waitForElementVisible(
                By.cssSelector(iAmAgedOver18)).click();
        waitForElementVisible(
                By.cssSelector(confirmCodeOfEthics)).click();
        waitForElementVisible(
                By.cssSelector(confirmAndJoin)).click();

    }

    @Then("I see error {string}")
    public void iSeeError(String message) {
        WebElement errorMessage = waitForElementVisible(
                By.cssSelector("span[for=\"member_lastname\"]\n"));

        assertEquals(message, errorMessage.getText().trim());

    }

    @When("I enter {} and {}:")
    public void iEnterAnd(String Password, String ConfirmPassword) {

        String newPassword = "#signupunlicenced_password";
        String retypePassword = "#signupunlicenced_confirmpassword";

        driver.findElement(By.cssSelector(newPassword))
                .sendKeys(Password, Keys.ENTER);
        driver.findElement(By.cssSelector(retypePassword))
                .sendKeys(ConfirmPassword, Keys.ENTER);

    }

    @Then("I should see {}")
    public void iShouldSee(String message) {

        WebElement errorMessage = driver.findElement(By.xpath("//span[contains(text(), '" + message + "')]"));
        String actualMessage = errorMessage.getText();
        assertEquals(message, actualMessage);
    }

    @When("Confirm registration without Terms and Conditions accept")
    public void confirmRegistrationWithoutTermsAndConditionsAccept() {

        String dateOfBirth = ".custom-date";
        String firstName = "input[id=\"member_firstname\"][name=\"Forename\"]\n";
        String lastName = "input[id=\"member_lastname\"][name=\"Surname\"]";
        String email = "input[id=\"member_emailaddress\"][name=\"EmailAddress\"]";
        String confirmEmail = "input[id=\"member_confirmemailaddress\"][name=\"ConfirmEmailAddress\"]";
        String password = "#signupunlicenced_password";
        String retypePassword = "#signupunlicenced_confirmpassword";
        String iAmAgedOver18 = "label[for='sign_up_26']";
        String confirmCodeOfEthics = "label[for='fanmembersignup_agreetocodeofethicsandconduct']";
        String confirmAndJoin = "#signup_form > div.form-actions.noborder > input";

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


        waitForElementVisible(By.cssSelector(iAmAgedOver18)).click();
        waitForElementVisible(By.cssSelector(confirmCodeOfEthics)).click();
        waitForElementVisible(By.cssSelector(confirmAndJoin)).click();

    }

    @Then("I see error message")
    public void iSeeErrorMessage() {

        WebElement error = waitForElementVisible(
                By.cssSelector("span[for=\"TermsAccept\"]"));

        String expectedErrorMessage = "You must confirm that you have read and accepted our Terms and Conditions";
        assertEquals(expectedErrorMessage, error.getText().trim());

    }
}




