package Mobile.Pages;

import Mobile.ExtendFromCore.AllPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class MobilePage extends AllPages
{
    private final By mobileHeadingXpath = By.xpath("//h1[normalize-space()='Mobile']");
    private final By mobileHeadingCSS = By.cssSelector("div[class='page-title category-title'] h1");

    private final By selectDropDown = By.xpath("//div[@class='category-products']/div[@class='toolbar']/div[@class='sorter']/div[@class='sort-by']/select[1]");
    private final By sortArrowXpth  = By.xpath("//div[@class='category-products']/div[@class='toolbar']/div[@class='sorter']/div[@class='sort-by']/a");
    public final By productlist =  By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li");

    private final By product = By.xpath("//a[@title='Xperia']//following-sibling::div//button");
    private final By btnCompare = By.xpath("//button[@title='Compare']//span[contains(text(),'Compare')]");

    private final By hdPopUP = By.xpath("//h1[contains(text(),'Compare Products')]");
    private final By btnClosPopup = By.xpath("//button[@title='Close Window']");


    public MobilePage(WebDriver driver) {

        super(driver);
    }

    public void Selectsort(String text)
    {

        //Click(selectDropDown);
        SelectByText(selectDropDown,text);
        Click(sortArrowXpth );
    }

    public void SelectAddToCart(String productTitle)
    {
       final By product = By.xpath("//a[@title='"+productTitle+"']//following-sibling::div//button");
       Click(product);

    }

    public void SelectProductToCompare(String productTitle)
    {
        By loc = By.xpath("//a[@title='"+productTitle+"']//following-sibling::div//a[@class='link-compare']");
        Click(loc);
    }

    public void ClickCompare()
    {
        Click(btnCompare);
    }
    public void VerifyComparePopUP()
    {
      coreAssert.SoftAssertAreTrue(IsVisible(hdPopUP),"Verify pop up is opened");  ;
    }

    public void ClosePopup()
    {
        Click(btnClosPopup);
    }

    public void VerifySort(By locator, String text)
    {
         List<WebElement> prodlist = FindElements(locator);
         String[] beforeSort = new String[prodlist.size()];
         for(int i =0; i<prodlist.size();i++)
         {
             beforeSort[i]=prodlist.get(i).getText().trim();
            // System.out.println(beforeSort[i]);
         }
        //Click Sort by Name
        Selectsort(text);
         //now sorting the before sort array
        Arrays.sort(beforeSort);


        prodlist = FindElements(locator);
        String[] aftreSort = new String[prodlist.size()];
        System.out.println("Verifying by:"+text);
        for(int i =0; i<prodlist.size();i++)
        {
            aftreSort[i]=prodlist.get(i).getText().trim();
            System.out.println(aftreSort [i]);
        }

        //Assert.assertEquals(beforeSort,aftreSort);
        coreAssert.SoftAssertAreEqual(beforeSort,aftreSort,"It is  sorting as expected.");
        System.out.println("-------------------------------------------------------------------------");
    }


}
