import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//The registration class represents all the elements and function of the registration page on the BuyMe site
public class SignUp {

    private final WebDriver driver;

    private static final String first_Name = "marsel";
    private static final String email_Address = "cx222@walla.com";
    private static final String Password= "123456";

    @FindBy (xpath = "//input[@placeholder='שם פרטי']")
    private static WebElement firstName;

    @FindBy (css = "input[placeholder='מייל']")
    private static WebElement emailAddress;

    @FindBy (id = "valPass")
    private static WebElement password;

    @FindBy (css = "input[placeholder='אימות סיסמה']")
    private static WebElement passwordAuthent;

    @FindBy (css = "label[for='register-consent']")
    private static WebElement allowButton;

    @FindBy (css = "button[type='submit']")
    private static WebElement registerButton;

    //Constructors
    public SignUp (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //getter
    public static WebElement getFirstName() {
        return firstName;
    }

    public static WebElement getEmailAddress() {
        return emailAddress;
    }

    public static WebElement getPassword() {
        return password;
    }

    public static WebElement getPasswordAuthent() {
        return passwordAuthent;
    }

    public static WebElement getAllowButton() {
        return allowButton;
    }

    public static WebElement getRegisterButton() {
        return registerButton;
    }

    //Function that enters the user's first name on the registration page
    public static void enterFirstName(){
        getFirstName().clear();
        getFirstName().sendKeys(first_Name);
    }

    //Function that enter email in registration page
    public static void enterEmailAddress(){
        getEmailAddress().clear();
        getEmailAddress().sendKeys(email_Address);
    }

    //Function that enters a new password and password verification on the registration page
    public static void enterPasswordAndVerify (){
        getPassword().clear();
        getPasswordAuthent().clear();
        getPassword().sendKeys(Password);
        getPasswordAuthent().sendKeys(Password);
    }

    //Function that fills in all required details to register a new user
    public static void FillInTheRegistrationDetails(){
        enterFirstName();
        enterEmailAddress();
        enterPasswordAndVerify();
    }

    //Function that confirms the the site policy
    //
    public static void selectAllowButton(){
        boolean isSelected = allowButton.isSelected();
        if (isSelected == false)
            getAllowButton().click();
    }

    public static void clickToRegister(){
        getRegisterButton().click();
    }


}
