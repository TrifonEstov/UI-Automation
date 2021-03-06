package phptravels;

import Utils.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static Utils.Browser.driver;


public class StepsDefinitions {

    @Before
    public static void setupDriver() {
        Browser.openBrowser();
    }
    @After
    public static void closeDriver(){
      Browser.closeBrowser();
    }

    public String newlyCreatedAccountEmail;

    @Given("the user is on PHP Travels homepage with accepted cookies")
    public void theUserIsOnPHPTravelsHomepage() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.phptravels.net/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String homePageTitle = driver.getTitle();
        Assert.assertEquals(homePageTitle, "PHPTRAVELS | Travel Technology Partner - PHPTRAVELS");
        WebElement acceptCookiesButton = driver.findElement(By.id("cookie_stop"));
        acceptCookiesButton.click();
        Thread.sleep(1000);
    }

    @When("he navigates to the sign in page")
    public void heNavigatesToTheSignInPage() {
        WebElement signInButton = driver.findElement(By.xpath("//*[@id=\"fadein\"]/header/div[1]/div/div/div[2]/div/div/a[2]"));
        signInButton.click();
        String loginPageTitle = driver.getTitle();
        Assert.assertEquals(loginPageTitle, "Login - PHPTRAVELS");
    }

    @And("he enters {string} as account email")
    public void heEntersAsAccountEmail(String email) {
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[1]/div/input"));
        emailField.sendKeys(email);
    }

    @And("he enters {string} as a password")
    public void heEntersAsAPassword(String password) {
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[2]/div[1]/input"));
        passwordField.sendKeys(password);
    }

    @And("he clicks Login button")
    public void heClicksLoginButton() {
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[3]/button"));
        loginButton.click();
    }

    @Then("ensure that the account is successfully logged in")
    public void ensureThatTheAccountIsSuccessfullyLoggedIn() {
        String userDashboardTitle = driver.getTitle();
        Assert.assertEquals(userDashboardTitle, "Dashboard - PHPTRAVELS");
    }

    @Then("ensure that the login is unsuccessful")
    public void ensureThatTheLoginIsUnsuccessful() {
        String loginPageTitle = driver.getTitle();
        Assert.assertEquals(loginPageTitle, "Login - PHPTRAVELS");
    }

    @And("he clicks on Logout button")
    public void heClicksOnLogoutButton() {
        WebElement logOutButton = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[3]/ul/li[5]/a"));
        logOutButton.click();
    }

    @Then("ensure that the account is successfully logged out")
    public void ensureThatTheAccountIsSuccessfullyLoggedOut() {
        String loginPageTitle = driver.getTitle();
        Assert.assertEquals(loginPageTitle, "Login - PHPTRAVELS");
    }

    @When("he navigates to the sign up page")
    public void heNavigatesToTheSignUpPage() {
        WebElement signupButton = driver.findElement(By.xpath("//*[@id=\"fadein\"]/header/div[1]/div/div/div[2]/div/div/a[1]"));
        signupButton.click();
        String loginPageTitle = driver.getTitle();
        Assert.assertEquals(loginPageTitle, "Signup - PHPTRAVELS");
    }

    @And("he enters {string} as first name and {string} as last name")
    public void heEntersAsFirstNameAndAsLastName(String firstName, String lastName) {
        WebElement firstNameInputField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[1]/div/input"));
        WebElement lastNameInputField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[2]/div/input"));
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);

    }

    @And("he enters {string} as first name,{string} as last name and {string} as phone number")
    public void heEntersAsFirstNameAsLastNameAndAsPhoneNumber(String firstName, String lastName, String phoneNumber) {
        WebElement firstNameInputField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[1]/div/input"));
        WebElement lastNameInputField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[2]/div/input"));
        WebElement phoneNumberInputField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[3]/div/input"));
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);
        phoneNumberInputField.sendKeys(phoneNumber);
    }

    @And("he enters email")
    public void heEntersEmail() {
        Random numGenerator = new Random();
        int randomNumber = numGenerator.nextInt(1000);
        String emailForSignup = "test" + randomNumber + "@gmail.com";
        newlyCreatedAccountEmail = emailForSignup;
        WebElement emailInputField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[4]/div/input"));
        emailInputField.sendKeys(emailForSignup);
    }

    @And("he enters {string} as password")
    public void heEntersAsPassword(String password) {
        WebElement passwordInputField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[5]/div/input"));
        passwordInputField.sendKeys(password);
    }

    @And("he clicks on Signup button")
    public void heClicksOnSignupButton() throws InterruptedException {
        Thread.sleep(3000);
        WebElement signupButton = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[7]/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signupButton);
        //        signupButton.click();
    }

    @Then("ensure that the sign up is successful")
    public void ensureThatTheSignUpIsSuccessful() throws InterruptedException {
        Thread.sleep(3000);
        WebElement congratsMessage = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/div"));
        String congratsMessageText = congratsMessage.getText();
        Assert.assertEquals(congratsMessageText, "Signup successfull please login.");
    }

    @Then("ensure that the sign up is unsuccessful due to already existing user")
    public void ensureThatTheSignUpIsUnsuccessfulDueToAlreadyExistingUser() throws InterruptedException {
        Thread.sleep(3000);
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/div[1]"));
        String errorMessageText = errorMessage.getText();
        Assert.assertEquals(errorMessageText, "Email already exist!");
    }

    @And("make sure that the sign into newly created account is successful")
    public void makeSureThatTheSignIntoNewlyCreatedAccountIsSuccessful() {
        WebElement emailInputField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[1]/div/input"));
        emailInputField.sendKeys(newlyCreatedAccountEmail);
        WebElement passwordInputField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[2]/div[1]/input"));
        passwordInputField.sendKeys("123456");
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[3]/button"));
        loginButton.click();
        String userDashboardTitle = driver.getTitle();
        Assert.assertEquals(userDashboardTitle, "Dashboard - PHPTRAVELS");
    }

    @And("he enters {string} as already used email")
    public void heEntersAlreadyUsedEmail(String usedEmail) {
        WebElement emailInputField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[1]/div/div[2]/div[2]/div/form/div[4]/div/input"));
        emailInputField.sendKeys(usedEmail);
    }

    @When("he navigates to hotels page")
    public void heNavigatesToHotelsPage() {
        WebElement hotelsPageButton = driver.findElement(By.xpath("//*[@id=\"fadein\"]/header/div[2]/div/div/div/div/div[2]/nav/ul/li[2]/a"));
        hotelsPageButton.click();
        String searchPageTitle = driver.getTitle();
        Assert.assertEquals(searchPageTitle, "Search Hotels - PHPTRAVELS");
    }

    @Then("ensure that search search form and featured hotels section are available")
    public void ensureThatSearchSearchFormAndFeaturedHotelsSectionAreAvailable() throws InterruptedException {
        Thread.sleep(1000);
        boolean isPresentSearchForm = driver.findElement(By.xpath("//*[@id=\"hotels-search\"]/div")).isDisplayed();
        Assert.assertTrue(isPresentSearchForm);
        boolean isPresentFeaturedSection = driver.findElement(By.xpath("//*[@id=\"fadein\"]/section[2]")).isDisplayed();
        Assert.assertTrue(isPresentFeaturedSection);
    }

    @And("he clicks on search field and enters {string} as desired destination")
    public void heClicksOnSearchFieldAndEntersAsDesiredDestination(String city) throws InterruptedException {
        WebElement searchField = driver.findElement(By.xpath("//*[@id=\"select2-hotels_city-container\"]"));
        searchField.click();
        WebElement searchFieldClicked = driver.findElement(By.xpath("//*[@id=\"fadein\"]/span/span/span[1]/input"));
        searchFieldClicked.sendKeys(city);
        Thread.sleep(3000);
        WebElement searchedCity = driver.findElement(By.xpath("//*[@id=\"select2-hotels_city-results\"]/li"));
        searchedCity.click();
    }

    @And("he selects checkin and checkout dates")
    public void heSelectsCheckinAndCheckoutDates() throws InterruptedException {
        WebElement checkinCalendar = driver.findElement(By.id("checkin"));
//        String initialDate = checkinCalendar.getText();
//        Assert.assertEquals(initialDate, "asdadasd");
        checkinCalendar.click();
        Thread.sleep(300);
        WebElement selectedDate8OfApril = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/div[1]/table/tbody/tr[2]/td[6]"));
        selectedDate8OfApril.click();
        Thread.sleep(300);
//        String newlySelectedDate = checkinCalendar.getText();
//        Assert.assertNotEquals(initialDate, newlySelectedDate);
        WebElement chechoutCalendar = driver.findElement(By.id("checkout"));
        WebElement selectDate22OfApril = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[3]/div[1]/table/tbody/tr[4]/td[6]"));
        selectDate22OfApril.click();
        Thread.sleep(300);
    }

    @And("he selects travelers number and nationality")
    public void heSelectsTravelersNumberAndNationality() throws InterruptedException {
        WebElement travelersDropdown = driver.findElement(By.xpath("//*[@id=\"hotels-search\"]/div/div/div[3]/div/div/div/a"));
        travelersDropdown.click();

        String initialRoomCount = driver.findElement(By.id("rooms")).getAttribute("value");
        String initialAdultsCount = driver.findElement(By.id("adults")).getAttribute("value");
        String initialChildsCount = driver.findElement(By.id("childs")).getAttribute("value");

        WebElement roomsPlusButton = driver.findElement(By.xpath("//*[@id=\"hotels-search\"]/div/div/div[3]/div/div/div/div/div[1]/div/div/div[3]"));
        roomsPlusButton.click();
        WebElement adultsPlusButton = driver.findElement(By.xpath("//*[@id=\"hotels-search\"]/div/div/div[3]/div/div/div/div/div[2]/div/div/div[2]/i"));
        adultsPlusButton.click();
        WebElement childsPlusButton = driver.findElement(By.xpath("//*[@id=\"hotels-search\"]/div/div/div[3]/div/div/div/div/div[3]/div/div/div[2]/i"));
        childsPlusButton.click();
        Thread.sleep(200);

        String updatedRoomCount = driver.findElement(By.id("rooms")).getAttribute("value");
        String updatedAdultsCount = driver.findElement(By.id("adults")).getAttribute("value");
        String updatedChildsCount = driver.findElement(By.id("childs")).getAttribute("value");

        Assert.assertNotEquals(initialRoomCount, updatedRoomCount);
        Assert.assertNotEquals(initialAdultsCount, updatedAdultsCount);
        Assert.assertNotEquals(initialChildsCount, updatedChildsCount);

        Select childAgeDropdown = new Select(driver.findElement(By.id("ages1")));
        childAgeDropdown.selectByVisibleText("5");

        Select nationalityDropdown = new Select(driver.findElement(By.id("nationality")));
        nationalityDropdown.selectByVisibleText("Bulgaria");
    }

    @And("he clicks on Search hotel button")
    public void heClicksOnSearchHotelButton() {
        WebElement searchButton = driver.findElement(By.id("submit"));
        searchButton.click();
    }

    @When("he navigates to the Featured Hotels section")
    public void heNavigatesToTheFeaturedHotelsSection() throws InterruptedException {
        WebElement featuredHotelSectoin = driver.findElement(By.xpath("//*[@id=\"fadein\"]/section[4]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", featuredHotelSectoin);
        Thread.sleep(2000);
        boolean isfeaturedHotelsSectionDisplayed = featuredHotelSectoin.isDisplayed();
        Assert.assertTrue(isfeaturedHotelsSectionDisplayed);
    }

    @And("he selects desired hotel")
    public void heSelectsDesiredHotel() {
        WebElement detailsButtonOfTheFirstFeaturedHotel = driver.findElement(By.xpath("//*[@id=\"fadein\"]/section[4]/div/div[2]/div/div/div/div[1]/div/div[6]/div/div[2]/div[2]/a"));
        detailsButtonOfTheFirstFeaturedHotel.click();
        WebElement aboutHoteTitle = driver.findElement(By.xpath("//*[@id=\"description\"]/div[2]/h3"));
        String aboutHotelTitleText = aboutHoteTitle.getText();
        Assert.assertTrue(aboutHotelTitleText.contains("About"));
    }

    @And("he selects desired room")
    public void heSelectsDesiredRoom() throws InterruptedException {
        WebElement bookNowButton = driver.findElement(By.xpath("//*[@id=\"availability\"]/div[2]/div[1]/div[2]/div/div[2]/form/div/div[4]/div/div/button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bookNowButton);
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bookNowButton);
        Thread.sleep(1000);

        String bookingPageTitle = driver.getTitle();
        Assert.assertEquals(bookingPageTitle, "Hotel Booking - PHPTRAVELS");
    }


    @And("he enters personal information")
    public void heEntersPersonalInformation() throws InterruptedException {
        WebElement firstNameField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[1]/div[2]/div/div/div[1]/div/div/input"));
        WebElement lastNameField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[1]/div[2]/div/div/div[2]/div/div/input"));
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[1]/div[2]/div/div/div[3]/div/div/input"));
        WebElement phone = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[1]/div[2]/div/div/div[4]/div/div/input"));
        WebElement address = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[1]/div[2]/div/div/div[5]/div/div/input"));
        firstNameField.sendKeys("Test");
        lastNameField.sendKeys("User");
        emailField.sendKeys("test.user@gmail.com");
        phone.sendKeys("+359888123456");
        address.sendKeys("Sofia., Bulgaria, Vitosha 19, blvd.");

        WebElement countryDropdown = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[1]/div[2]/div/div/div[6]/div/div/div/span/span[1]/span"));
        countryDropdown.click();
        WebElement countrySearchField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/span/span/span[1]/input"));
        countrySearchField.sendKeys("Bulgaria");
        Thread.sleep(200);
        WebElement countryResult = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li"));
        countryResult.click();

        WebElement nationalityDropdown = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[1]/div[2]/div/div/div[7]/div/div/div/span/span[1]/span"));
        nationalityDropdown.click();
        WebElement nationalitySearchField = driver.findElement(By.xpath("//*[@id=\"fadein\"]/span/span/span[1]/input"));
        nationalitySearchField.sendKeys("Bulgaria");
        Thread.sleep(200);
        WebElement nationalityResult = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li"));
        nationalityResult.click();
    }

    @And("he enters travellers information")
    public void heEntersTravellersInformation() {
        Select firstTravellerTitle = new Select(driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div/div[1]/select")));
        firstTravellerTitle.selectByVisibleText("MR");
        WebElement firstTravellerFirstName = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div/div[2]/input"));
        firstTravellerFirstName.sendKeys("Test");
        WebElement firstTravellerLastName = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div/div[3]/input"));
        firstTravellerLastName.sendKeys("User");

        Select secondTravellerTitle = new Select(driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[2]/div[2]/div[2]/div[2]/div/div[1]/select")));
        firstTravellerTitle.selectByVisibleText("MISS");
        WebElement secondTravellerFirstName = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[2]/div[2]/div[2]/div[2]/div/div[2]/input"));
        secondTravellerFirstName.sendKeys("TestUserWifeName");
        WebElement secondTravellerLastName = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[2]/div[2]/div[2]/div[2]/div/div[3]/input"));
        secondTravellerLastName.sendKeys("TestUserWifeFamily");

        Select childAge = new Select(driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[2]/div[2]/div[3]/div[2]/div/div[1]/select")));
        childAge.selectByVisibleText("5");
        WebElement childFirstName = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[2]/div[2]/div[3]/div[2]/div/div[2]/input"));
        childFirstName.sendKeys("TestChildName");
        WebElement childLastName = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[2]/div[2]/div[3]/div[2]/div/div[3]/input"));
        childLastName.sendKeys("TestChildFamily");
    }

    @And("he chooses payment method")
    public void heChoosesPaymentMethod() {
        WebElement payLaterMethod = driver.findElement(By.id("gateway_pay-later"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", payLaterMethod);
    }

    @And("he accepts T&C and confirms booking")
    public void heAcceptsTCAndConfirmsBooking() throws InterruptedException {
        WebElement termsAndConditionsCheckBox = driver.findElement(By.xpath("//*[@id=\"fadein\"]/div[2]/form/section/div/div/div[1]/div[4]/div/div/div/label"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", termsAndConditionsCheckBox);
        Thread.sleep(2000);
        WebElement submitButton = driver.findElement(By.id("booking"));
        submitButton.click();
    }

    @Then("ensure that the booking is completed")
    public void ensureThatTheBookingIsCompleted() {
        String pendingBookingPageTitle = driver.getTitle();
        Assert.assertEquals(pendingBookingPageTitle, "Hotel Invoice - PHPTRAVELS");
    }

    @And("he turns back to the homepage")
    public void heTurnsBackToTheHomepage() throws InterruptedException {
        WebElement homePageButton = driver.findElement(By.xpath("//*[@id=\"fadein\"]/header/div[2]/div/div/div/div/div[2]/nav/ul/li[1]/a"));
        homePageButton.click();
        Thread.sleep(500);
    }
}