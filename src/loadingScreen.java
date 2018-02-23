import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Class that represents the site's loading page
public class loadingScreen {

    private final WebDriver driver;

    @FindBy (xpath = "//*[@id=\"app-loading-img\"]/div/div[2]")
        private static WebElement dot;

    //Constructors
    public loadingScreen(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Getter
    public static WebElement getDot() {
        return dot;
    }

    //Function that prints the height and width of the point on the loading screen
    public static void printDotSize (){
        System.out.println("Height:"+getDot().getSize().getHeight());
        System.out.println("Width:"+getDot().getSize().getWidth());
    }
}
