package com.automation.api.data;

import com.automation.api.pojo.BankTransaction;
import org.testng.annotations.DataProvider;

public class DataProv {

    @DataProvider(name = "dataInfo")
    public Object[][] createDataInfo() {
        return new Object[][]{{new BankTransaction()},
                {new BankTransaction()},
                {new BankTransaction()},
                {new BankTransaction()},
                {new BankTransaction()},
                {new BankTransaction()},
                {new BankTransaction()},
                {new BankTransaction()},
                {new BankTransaction()},
                {new BankTransaction()}};
    }

}
