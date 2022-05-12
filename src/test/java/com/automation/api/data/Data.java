package com.automation.api.data;

import com.automation.api.pojo.User;
import org.testng.annotations.DataProvider;

public class Data {

    @DataProvider(name = "users")
    public Object[][] inputData() {
        User carla = new User("Carla", "Giraldo", "111458", 20, "deposit",
                "carla@gmai.com", true, "Colombia","34183950", "business analyst");
        User carlos = new User("Carlos", "gomez", "11145843", 100, "deposit",
                "carlitos@gmai.com", true, "Colombia","3418332950", "java developer");

        return new Object[][] {{carla},{carlos}};
    }
}
