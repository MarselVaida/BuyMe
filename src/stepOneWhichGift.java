import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class stepOneWhichGift {

    private final WebDriver driver;
    private static final int Amount = 1500;

    @FindBy(css = "input[placeholder='מה הסכום?']")
    private static WebElement amount;

    @FindBy (xpath = "//*[@class='btn-wrapper']/button")
    private static WebElement submitAmountButton;

    //Constructors
    public stepOneWhichGift (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Getter
    public static WebElement getAmount() {
        return amount;
    }

    public static WebElement getSubmitAmountButton() {
        return submitAmountButton;
    }

    //Function that puts the amount of the gift card to buy and submit the approves the purchase
    public void enterAmountAndSubmit (){
        String number = Integer.toString(Amount);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",getAmount());
        getAmount().sendKeys(number);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",getSubmitAmountButton());
        getSubmitAmountButton().click();

    }
}
