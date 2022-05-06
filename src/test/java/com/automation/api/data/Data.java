package com.automation.api.data;

import com.automation.api.pojo.BankUser;
import com.automation.api.pojo.User;
import com.automation.api.pojo.enums.AccountType;
import org.testng.annotations.DataProvider;

public class Data {

    @DataProvider(name = "users")
    public Object[][] inputData() {
        User carla = new User("carla",
                "pondrea",
                "lasdffuidsa@p.com",
                "Colombia",
                "3452345",
                true,
                "Java dev");
        return new Object[][] {{carla}};
    }

    /**
     * This is the mock user for the bank test API
     * @return the user  Jaimito
     */
    @DataProvider(name = "bank user")
    public Object[][] randomBankUser() {
        BankUser jaimito = BankUser.builder()
                .first_name("Jaimito")
                .last_name("El Calvito")
                .account_number("123456-12345-12")
                .amount(100_000_000)
                .account_type(AccountType.SAVINGS)
                .email("jaimitoelcalvito@gmail.com")
                .active(true)
                .country("Colombia")
                .telephone("312123456")
                .build();

        return new Object[][] {{jaimito}};
    }
}
