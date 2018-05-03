package com.pughlab.trialmatchapi.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clinical Information
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
    @Field("GENDER")
    private String gender;

    @NotNull
    @Size(min = 1)
    @Field("vital_status")
    private String vitalStatus;

    @NotNull
    @Size(min = 1)
    @Field("ONCOTREE_PRIMARY_DIAGNOSIS_NAME")
    private String oncotreeCode;

    @NotNull
    @Size(min = 1)
    @Field("ORD_PHYSICIAN_NAME")
    private String physicianName;

    public Clinical(String id, String sampleID, String patientID, String gender,
                    String vitalStatus, String oncotreeCode, String physicianName) {
        this.id = id;
        this.sampleID = sampleID;
        this.patientID = patientID;
        this.gender = gender;
        this.vitalStatus = vitalStatus;
        this.oncotreeCode = oncotreeCode;
        this.physicianName = physicianName;
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

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getVitalStatus() { return vitalStatus; }

    public void setVitalStatus(String vitalStatus) {
        this.vitalStatus = vitalStatus;
    }

    public String getOncotreeCode() { return  oncotreeCode; }

    public void setOncotreeCode(String oncotreeCode) {
        this.oncotreeCode = oncotreeCode;
    }

    public String getPhysicianName() { return physicianName; }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }
}

