package com.pughlab.trialmatchapi.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clinical Trial information
 *
 * @author  Kelsey Zhu
 * @version 1.0
 * @since   2018-04-19
 */

@Document(collection = "clinical")
public class Clinical {

    @Id
    @Field("_id")
    private String id;

    @NotNull
    @Size(min = 1)
    @Field("sample_id")
    private String sampleID;

    @NotNull
    @Size(min = 1)
    @Field("patient_id")
    private String patientID;


    @NotNull
    @Size(min = 1)
    @Field("vital_status")
    private String vitalStatus;

    public Clinical(String id, String sampleID, String patientID, String vitalStatus) {
        this.id = id;
        this.sampleID = sampleID;
        this.patientID = patientID;
        this.vitalStatus = vitalStatus;
    }

    public String getId() {
        return  id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSampleID() {
        return  sampleID;
    }

    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }


    public String getPatientID() { return  patientID; }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getVitalStatus() { return vitalStatus; }

    public void setVitalStatus(String vitalStatus) {
        this.vitalStatus = vitalStatus;
    }

}

