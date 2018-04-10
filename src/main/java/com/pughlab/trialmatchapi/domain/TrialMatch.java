package com.pughlab.trialmatchapi.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Trial Match object stores matched patient clinical and genomic information to trials
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
    private String title;

    @NotNull
    @Size(min = 1)
    @Field("match_type")
    private String matchType;

    @NotNull
    @Size(min = 1)
    @Field("sample_id")
    private String sampleID;

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

    public TrialMatch(String nctID, String title, String matchType, String sampleID, String hugoSymbol,
                      String variantClassification, String proteinChange, String chromosome,
                      long position, String dose) {
        this.nctID = nctID;
        this.title = title;
        this.matchType = matchType;
        this.sampleID = sampleID;
        this.hugoSymbol = hugoSymbol;
        this.variantClassification = variantClassification;
        this.proteinChange = proteinChange;
        this.matchType = matchType;
        this.chromosome = chromosome;
        this.position = position;
        this.dose = dose;
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

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
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
}
