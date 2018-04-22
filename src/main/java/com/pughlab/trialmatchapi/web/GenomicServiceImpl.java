package com.pughlab.trialmatchapi.web;


import com.pughlab.trialmatchapi.domain.Genomic;
import com.pughlab.trialmatchapi.repository.GenomicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenomicServiceImpl implements GenomicService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private GenomicRepository genomicRepository;

    @Autowired
    public void setTrialRepository(GenomicRepository genomicRepository) {
        this.genomicRepository = genomicRepository;
    }

    @Override
    public List<Genomic> listAllGenomics() {
        logger.debug("listAllGenomics called");
        return genomicRepository.findAll();
    }

    @Override
    public Genomic getGenomicById(String id) {
        logger.debug("getGenomicById called");
        return genomicRepository.findById(id);
    }

    @Override
    public Genomic getTrialByHugoSymbolAndProteinChange(String hugoSymbol, String proteinChange) {
        logger.debug("getTrialByHugoSymbolAndProteinChange called");
        return genomicRepository.findTrialByHugoSymbolAndProteinChange(hugoSymbol, proteinChange);
    }

    @Override
    public void delete(String id) {
        logger.debug("delete called");
        genomicRepository.delete(getGenomicById(id));
    }
}


