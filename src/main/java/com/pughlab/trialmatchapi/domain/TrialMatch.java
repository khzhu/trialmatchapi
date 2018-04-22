package com.pughlab.trialmatchapi.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Object stores matched patient clinical and genomic information to trials
 *
 * @author  Kelsey Zhu
 * @version 1.0
 * @since   2018-04-09
 */


@Document(collection = "trial_match")
public class TrialMatch {

    @Id
    @Field("_id")
    private String id;

    @NotNull
    @Size(min = 1)
    @Field("nct_id")
    private String nctID;

    @NotNull
    @Size(min = 1)
    @Field("title")
    private String trialTitle;

    @NotNull
    @Size(min = 1)
    @Field("code")
    private String code;

    @NotNull
    @Size(min = 1)
    @Field("match_type")
    private String matchType;

    @NotNull
    @Size(min = 1)
    @Field("match_level")
    private String matchLevel;

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
    @Field("genomic_id")
    private String genomicID;

    @NotNull
    @Size(min = 1)
    @Field("true_hugo_symbol")
    private String hugoSymbol;

    @NotNull
    @Size(min = 1)
    @Field("true_variant_classification")
    private String variantClassification;

    @NotNull
    @Size(min = 1)
    @Field("true_protein_change")
    private String proteinChange;

    @NotNull
    @Size(min = 1)
    @Field("chromosome")
    private String chromosome;

    @NotNull
    @Size(min = 1)
    @Field("position")
    private long position;

    @NotNull
    @Size(min = 1)
    @Field("dose")
    private String dose;

    @NotNull
    @Size(min = 1)
    @Field("mutation_effect")
    private String mutEffect;

    @NotNull
    @Size(min = 1)
    @Field("trial_accrual_status")
    private String trialStatus;

    @NotNull
    @Size(min = 1)
    @Field("oncogenicity")
    private String oncogenicity;

    @NotNull
    @Size(min = 1)
    @Field("vital_status")
    private String vitalStatus;

    public TrialMatch(String nctID, String trialTitle, String code, String matchType, String matchLevel, String sampleID,
                      String hugoSymbol, String variantClassification, String proteinChange, String chromosome,
                      long position, String dose, String patientID, String genomicID, String oncogenicity,
                      String mutEffect, String trialStatus, String vitalStatus) {
        this.nctID = nctID;
        this.trialTitle = trialTitle;
        this.code = code;
        this.matchType = matchType;
        this.matchLevel = matchLevel;
        this.sampleID = sampleID;
        this.patientID = patientID;
        this.genomicID = genomicID;
        this.hugoSymbol = hugoSymbol;
        this.variantClassification = variantClassification;
        this.proteinChange = proteinChange;
        this.matchType = matchType;
        this.chromosome = chromosome;
        this.position = position;
        this.dose = dose;
        this.mutEffect = mutEffect;
        this.oncogenicity = oncogenicity;
        this.trialStatus = trialStatus;
        this.vitalStatus = vitalStatus;
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

    public String getTrialTitle() {
        return  trialTitle;
    }

    public void setTrialTitle(String trialTitle) {
        this.trialTitle = trialTitle;
    }

    public String getCode() { return code; }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMatchLevel() { return matchLevel; }

    public void setMatchLevel(String matchLevel) {
        this.matchLevel = matchLevel;
    }

    public String getSampleID() {
        return  sampleID;
    }

    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }

    public String getHugoSymbol() {
        return hugoSymbol;
    }

    public void setHugoSymbol(String hugoSymbol) {
        this.hugoSymbol = hugoSymbol;
    }

    public String getVariantClassification() {
        return variantClassification;
    }

    public void setVariantClassification(String variantClassification) {
        this.variantClassification = variantClassification;
    }

    public String getProteinChange() {
        return proteinChange;
    }

    public void setProteinChange(String proteinChange) {
        this.proteinChange = proteinChange;
    }

    public String getChromosome() {
        return  chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getPatientID() { return  patientID; }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getGenomicID() { return genomicID; }

    public void setGenomicID(String genomicID) {
        this.genomicID = genomicID;
    }

    public String getTrialStatus() { return trialStatus; }

    public void setTrialStatus(String trialStatus) {
        this.trialStatus = trialStatus;
    }

    public String getOncogenicity() { return oncogenicity; }

    public void setOncogenicity(String oncogenicity) {
        this.oncogenicity = oncogenicity;
    }

    public String getMutEffect() { return mutEffect; }

    public void setMutEffect(String mutEffect) {
        this.mutEffect = mutEffect;
    }

    public String getVitalStatus() { return vitalStatus; }

    public void setVitalStatus(String vitalStatus) {
        this.vitalStatus = vitalStatus;
    }
}
