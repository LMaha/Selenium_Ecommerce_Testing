package Mobile.Data;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
@DataProvider(name = "SearchDataProvider")
    public static Object[] GetSearchCriteriaByName1()
    {
        return new Object[]{  "Position","Name", "Price"};
    }
}
