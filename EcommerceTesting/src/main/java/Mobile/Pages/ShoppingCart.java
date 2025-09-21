package Mobile.Pages;

import Mobile.ExtendFromCore.AllPages;
import Mobile.ExtendFromCore.AllTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCart extends AllPages {

    private final By Qty = By.xpath("//*[@title='Qty']");
    private final By updateBtn = By.xpath("//*[@name='update_cart_action' and @title='Update']");
    private final By btnEmptyCart = By.xpath("//*[@id='empty_cart_button']");

    private final By lblCartEmpty = By.xpath("//h1[normalize-space()='Shopping Cart is Empty']");

    public ShoppingCart(WebDriver driver) {
        super(driver);
    }

    public void EnterQuantity(String text)
    {
        SendKeys(Qty,"1000");
    }
    public void UpdateCart()
    {
        Click(updateBtn);
    }
    public void VerifyQtyError()
    {
        By loc = By.xpath("//p[@class='item-msg error']");


        coreAssert.SoftAssertAreTrue(IsVisible(loc),"Verify over quantity message appears");
    }
    public void EmptyCart()
    {
        Click(btnEmptyCart);
    }

    public void VerifyCartIsEmpty()
    {
        coreAssert.SoftAssertAreTrue(IsVisible(lblCartEmpty),"Verify cart is empty");
    }

    public void SelectProductToCompare(String productTitle)
    {
        By loc = By.xpath("//a[@title='"+productTitle+"']//following-sibling::div//a[@class='link-compare']");
    }
}
