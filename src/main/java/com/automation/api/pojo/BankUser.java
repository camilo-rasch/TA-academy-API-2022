package com.automation.api.pojo;

import com.automation.api.pojo.enums.AccountType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BankUser {


    private Long id;

    private  String first_name;

    private  String last_name;

    private  String account_number;

    private  double amount;

    private AccountType account_type;

    private  String email;

    private  boolean active;

    private  String country;

    private  String telephone;

    /**
     *
     * @param first_name String
     * @param last_name String
     * @param account_number String
     * @param amount double
     * @param account_type Enum
     * @param email String
     * @param active boolean
     * @param country String
     * @param telephone String
     */
    public BankUser(String first_name, String last_name, String account_number, double amount, AccountType account_type,
                    String email, boolean active, String country, String telephone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.account_number = account_number;
        this.amount = amount;
        this.account_type = account_type;
        this.email = email;
        this.active = active;
        this.country = country;
        this.telephone = telephone;
    }
}
