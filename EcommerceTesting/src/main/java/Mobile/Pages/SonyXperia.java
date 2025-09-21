package Mobile.Pages;

import Mobile.ExtendFromCore.AllPages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SonyXperia extends AllPages {

    private final By productQty = By.xpath("//*[@id='qty']");
    private final By btnAddtoCart= By.xpath("//*[@title='Add to Cart']");

    public SonyXperia(WebDriver driver) {
        super(driver);

    }

    public void EnterQuantity(String number)
    {
        SendKeys(productQty,"1000");
    }
    public void AddToCart()
    {
        Click(btnAddtoCart);
    }

    public void IsErrmessage (String text)
    {
        Boolean isVisible;
        //"The maximum quantity allowed for purchase is 500."
        By loc = By.xpath("//span[normalize-space()='"+text+"']");

        coreAssert.SoftAssertAreTrue(IsVisible(loc),"Verify if over quantity error message is present");

    }

}
