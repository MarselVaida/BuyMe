import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//BuyMe site - Login page
//All elements on the login screen
public class Login {

    private WebDriver driver;
    public static final String loginPageURL = "https://buyme.co.il/?modal=login";

    @FindBy (xpath = "//span[@class='header-link bold']")
    private static WebElement notSignUp;

    @FindBy(xpath = "//button[@class='db fluid btn btn-theme']")
    private static WebElement loginToSite;

    @FindBy (className = "parsley-required")
    private static WebElement errorMessage;

    //Constructors
    public Login(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Getter
    public static WebElement getNotSignUp() {
        return notSignUp;
    }

    public static WebElement getLoginToSite() {
        return loginToSite;
    }

    public static WebElement getErrorMessage() {
        return errorMessage;
    }

    public String getLoginPageURL() {
        return loginPageURL;
    }

    //function that checks the error messages on the login page
    public boolean verifyErrorMessage() {
        new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("parsley-required")));
        return getErrorMessage().isDisplayed();
    }

    //Click on login button
    public static void clickOnLoginToSite(){
        getLoginToSite().click();
    }

    //Click on 'still not sign-up' button
    public static void clickOnNotSignUpButton(){
        getNotSignUp().click();
    }


}
