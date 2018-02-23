import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class senderReceiverPage {

    private final WebDriver driver;
    private static final String sender_Name ="Marsel";
    private static final String receiver_Name = "Yehuda";
    private static final String blessing_Text= "המון אושר לעוד הרבה שנים יחד";
    private static final String imagePath ="C:\\Users\\marsell21\\IdeaProjects\\BuyMe\\happy_buyme.jpg";
    private static final String colorText="rgba(247, 179, 54, 1)";


    @FindBy (xpath = "//*[@class='ember-view']/div[1]/label[1]/span[1]/span")
    private static WebElement anotherPersonButton;

    @FindBy (xpath = "//*[@id='for-friend']/div/div/div/input")
    private static WebElement receiverName;

    @FindBy (id = "sender-name")
    private static WebElement senderName;

    @FindBy (id = "msg")
    private static WebElement blessingText;

    @FindBy (name = "fileUpload")
    private static WebElement picture;

    @FindBy (xpath = "//span[.='לאיזה אירוע?']")
    private static WebElement eventType;

    @FindBy (className = "send-now")
    private static WebElement afterPayment;

    @FindBy (xpath = "//*[@class='ember-view']/div[2]/div[4]/div/div[1]/div[1]/div/button")
    private static WebElement smsButton;

    @FindBy (xpath = "//*[@class='main-container second-padding']/div[2]/div[1]/ul[1]/li[2]/div[2]/span")
    private static WebElement whoToSendText;

    @FindBy (xpath ="//*[@class='sender']/span[2]")
    private static WebElement giftSenderName;

    @FindBy(xpath = "//*[@class='receiver']/span[2]")
    private static WebElement giftReceiverName;

    @FindBy(xpath = "//*[@class='row text-and-img-row']/div[1]/p")
    private static WebElement giftBlessingText;

    //Constructors
    public senderReceiverPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Getter
    public static WebElement getAnotherPersonButton() {
        return anotherPersonButton;
    }

    public static WebElement getReceiverName() {
        return receiverName;
    }

    public static WebElement getSenderName() {
        return senderName;
    }

    public static WebElement getBlessingText() {
        return blessingText;
    }

    public static WebElement getPicture() {
        return picture;
    }

    public static WebElement getEventType() {
        return eventType;
    }

    public static WebElement getSmsButton() {
        return smsButton;
    }

    public static WebElement getAfterPayment() {
        return afterPayment;
    }

    public static String getColorText() {
        return colorText;
    }

    public static WebElement getGiftSenderName() {
        return giftSenderName;
    }

    public static WebElement getGiftReceiverName() {
        return giftReceiverName;
    }

    public static WebElement getGiftBlessingText() {
        return giftBlessingText;
    }

    public static WebElement getWhoToSendText() {
        return whoToSendText;
    }

    public static String getSender_Name() {
        return sender_Name;
    }

    public static String getReceiver_Name() {
        return receiver_Name;
    }

    public static String getBlessing_Text() {
        return blessing_Text;
    }

    //Function that select who to send the gift to
    public static void sendToAnotherPerson(){

        boolean isSelected = getAnotherPersonButton().isSelected();
        if (!isSelected)
            getAnotherPersonButton().click();
    }

    //Function that enters the receiver's name
    public static void enterReceiverName (){
        getReceiverName().clear();
        getReceiverName().sendKeys(receiver_Name);
    }

    //Function that enters the sender's name
    public static void enterSenderName (){
        getSenderName().clear();
        getSenderName().sendKeys(sender_Name);
    }

    //Function that inserts teh blessings to the gift
    public static void enterBlessing (){
        getBlessingText().clear();
        getBlessingText().sendKeys(blessing_Text);
    }

    //Function that fills in all the form in step 2
    public void fillAllForm() throws InterruptedException {
        enterSenderName();
        enterReceiverName();
        selectEventType();
        enterBlessing();
    }

    //Function that add a picture
    public void addPicture (){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",getPicture());
        getPicture().sendKeys(imagePath);
        
    }

    //Function that select type event from the drop-down list
    public void selectEventType () throws InterruptedException {
        getEventType().click();
        Thread.sleep(1000);
        List<WebElement> eventList = driver.findElements(By.className("active-result"));
        eventList.get(4).click();
    }


    public void checkSendAfterPayment (){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",getAfterPayment());
        boolean isSelected = getAfterPayment().isSelected();
        if (isSelected == false)
            getAfterPayment().click();
    }

    //Function that send the gift card via SMS
    public void sendBySms(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",getSmsButton());
        getSmsButton().click();
    }

    //Function that return the color of the 'who to end?' title
    public static String whoToSendColorText(){
        return getWhoToSendText().getCssValue("color");
    }

}
