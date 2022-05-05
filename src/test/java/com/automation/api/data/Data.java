package com.automation.api.data;

import com.automation.api.pojo.User;
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
}
