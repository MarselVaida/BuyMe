import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class businessPage {

    private final WebDriver driver;

    private static String pageUrl;

    @FindBy(xpath = "//*[@class='promo-items']/div/div[2]/figure/a/div/figcaption/div/span")
    private static WebElement CartersBusiness;

    //Constructors
    public businessPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        pageUrl = driver.getCurrentUrl();
    }

    //Getter
    public static WebElement getCartersBuisness() {
        return CartersBusiness;
    }

    //Click on business button to buy
    public static void clickOnBusinessButton(){
        getCartersBuisness().click();
    }

}
