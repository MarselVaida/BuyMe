import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class sendBySms {

    private final WebDriver driver;
    private static final String sender_phone = "0546976907";
    private static final String receiver_phone = "0521234567";

    @FindBy(xpath = "//*[@class='step-form']/div[4]/div/div[4]/div[1]/div/div[1]/div/input")
    private static WebElement senderPhone;

    @FindBy (id = "resendReciver")
    private static WebElement receiverPhone;

    @FindBy (xpath = "//*[@class='overlay-buttons']/button[2]")
    private static WebElement saveButton;

    @FindBy (xpath = "//*[@class='ember-view']/div[2]/div[5]/button")
    private static  WebElement forPayment;

    //Constructors
    public sendBySms (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Getter
    public static WebElement getSenderPhone() {
        return senderPhone;
    }

    public static WebElement getReceiverPhone() {
        return receiverPhone;
    }

    public static WebElement getSaveButton() {
        return saveButton;
    }

    public static WebElement getForPayment() {
        return forPayment;
    }

    //Function that enters the sender's phone number
    public void enterSenderPhone(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",getSenderPhone());
        getSenderPhone().sendKeys(sender_phone);
    }

    //Function that enters the receiver's phone number
    public void enterReceiverPhone(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",getReceiverPhone());
        getReceiverPhone().sendKeys(receiver_phone);
    }

    //Function that enters all the required information in order to send the gift via SMS, save the details and move to the payment step
    public void sendGiftBySms(){
        enterSenderPhone();
        enterReceiverPhone();
        getSaveButton().click();
        getForPayment().click();
    }



}
