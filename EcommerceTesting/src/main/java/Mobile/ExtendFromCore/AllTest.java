package Mobile.ExtendFromCore;

import Mobile.Pages.HomePage;
import Mobile.Pages.MobilePage;


import Mobile.Pages.ShoppingCart;
import Mobile.Pages.SonyXperia;
import com.Comp.coreUI.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class AllTest extends BaseTest
{
    public HomePage hp;
    public MobilePage mp;
    public SonyXperia sonyXperia;
    public ShoppingCart sc;

    public void initAllPages()
    {
        hp= new HomePage(driver);
        mp = new MobilePage(driver);
        sonyXperia = new SonyXperia(driver);
        sc  = new ShoppingCart(driver);

    }
    @BeforeTest
    @Parameters({"browser", "env"})
    @Override
    public void Setup(@Optional("")String browser, @Optional("")String env)
    {
        super.Setup(browser, env);


    }



}
