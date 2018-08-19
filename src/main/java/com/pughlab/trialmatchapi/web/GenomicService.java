package com.pughlab.trialmatchapi.web;


import com.pughlab.trialmatchapi.domain.Genomic;
import java.util.List;

public interface GenomicService {
    List<Genomic> listAllGenomics();

    Genomic getGenomicById(String id);

    List<Genomic> getGenomicByProteinChangeAndSampleId(String proteinChange, String sampleId);

    void delete(String id);
}