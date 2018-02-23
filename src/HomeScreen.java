import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

//Class loginPage
//After you login to your user you can choose a gift to buy
//Use filter settings to optimize search

public class HomeScreen {

        private final WebDriver driver;

        static final String loginUrl= "https://buyme.co.il/";

        @FindBy(xpath = "//a[@class='top-bar-link no-underline']")
        private static WebElement loginButton;

        @FindBy(xpath = "//span[.='סכום']")
        private static WebElement amount;

        @FindBy (xpath = "//span[.='אזור']")
        private static WebElement area;

        @FindBy (xpath = "//span[.='קטגוריה']")
        private static WebElement category;

        @FindBy (css = "button[type='submit']")
        private static WebElement searchButton;

        //Constructors
        public HomeScreen (WebDriver driver){
            this.driver = driver;
            PageFactory.initElements(driver,this);
        }

        //Getter

        public static WebElement getLoginButton() {
                 return loginButton;
              }

        public static WebElement getAmount() {
            return amount;
        }

        public static WebElement getArea() {
            return area;
        }

        public static WebElement getCategory() {
            return category;
        }

        public static WebElement getSearchButton() {
            return searchButton;
        }


    //Function that selects an amount range from the drop-down list
        public  void chooseAmount(){
            getAmount().click();
            List<WebElement> amountList = driver.findElements(By.className("active-result"));
            amountList.get(1).click();
        }

        //Function that selects an area range from the drop-down list
        public void chooseArea(){
            getArea().click();
            List<WebElement> areaList = driver.findElements(By.className("active-result"));
            areaList.get(1).click();

        }

        //Function that selects a category range from the drop-down list
        public void chooseCategory(){
            getCategory().click();
            List<WebElement> categoryList = driver.findElements(By.className("active-result"));
            categoryList.get(1).click();
        }

        //Search button
        public static void searchGift(){
            getSearchButton().click();
        }

        //Click on login button
        public static void clickOnLoginButton(){
            getLoginButton().click();
        }


}


