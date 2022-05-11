package com.automation.api.pojo;

/**
 * Base pojo class to handle id in pojos.
 * @author js.lozano
 */
public class BasePojo {
    protected String id;

    /**
     * Get id
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Set id
     * @param id String
     */
    public void setId(String id) {
        this.id = id;
    }
}
