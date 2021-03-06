package com.pughlab.trialmatchapi.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Trial Information
 *
 * @author  Kelsey Zhu
 * @version 1.0
 * @since   2018-04-19
 */


@Document(collection = "trial")
public class Trial {

    @Id
    @Field("_id")
    private String id;

    @NotNull
    @Size(min = 1)
    @Field("nct_id")
    private String nctID;

    @NotNull
    @Size(min = 1)
    @Field("long_title")
    private String title;

    @NotNull
    @Size(min = 1)
    @Field("status")
    private String status;

    @NotNull
    @Size(min = 1)
    @Field("last_updated")
    private String lastUpdated;

    public Trial(String nctID, String title, String status, String lastUpdated) {
        this.nctID = nctID;
        this.title = title;
        this.status = status;
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return  id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNctID() {
        return nctID;
    }

    public void setNctID(String nctID) {
        this.nctID = nctID;
    }

    public String getTitle() {
        return  title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastUpdated() { return  lastUpdated; }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}

