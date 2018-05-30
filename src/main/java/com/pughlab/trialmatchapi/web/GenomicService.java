package com.pughlab.trialmatchapi.web;


import com.pughlab.trialmatchapi.domain.Genomic;
import java.util.List;

public interface GenomicService {
    List<Genomic> listAllGenomics();

    Genomic getGenomicById(String id);

    Genomic getGenomicByProteinChangeAndSampleId(String proteinChange, String sampleId);

    void delete(String id);
}