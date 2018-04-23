package com.pughlab.trialmatchapi.web;


import com.pughlab.trialmatchapi.domain.Genomic;
import java.util.List;

public interface GenomicService {
    List<Genomic> listAllGenomics();

    Genomic getGenomicById(String id);

    Genomic getTrialByHugoSymbolAndProteinChange(String hugoSymbol, String proteinChange);

    void delete(String id);
}