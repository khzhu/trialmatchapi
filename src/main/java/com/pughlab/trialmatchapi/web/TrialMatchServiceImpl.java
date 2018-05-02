package com.pughlab.trialmatchapi.web;



import com.pughlab.trialmatchapi.domain.TrialMatch;
import com.pughlab.trialmatchapi.repository.TrialMatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrialMatchServiceImpl implements TrialMatchService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private TrialMatchRepository trialMatchRepository;

    @Autowired
    public void setTrialMatchRepository(TrialMatchRepository trialMatchRepository) {
        this.trialMatchRepository = trialMatchRepository;
    }

    @Override
    public Iterable<TrialMatch> listAllTrialMatches() {
        logger.debug("listAllTrialMatches called");
        return trialMatchRepository.findAll();
    }

    @Override
    public TrialMatch getTrialMatchById(String id) {
        logger.debug("getTrialMatchById called");
        return trialMatchRepository.findById(id);
    }

    @Override
    public List<TrialMatch> getTrialMatchByHugoSymbol(String hugoSymbol) {
        logger.debug("getTrialMatchByHugoSymbol called");
        return trialMatchRepository.findTrialMatchByHugoSymbol(hugoSymbol);
    }

    @Override
    public List<TrialMatch> findTrialMatchByHugoSymbolExists(String[] hugoSymbols) {
        logger.debug("getTrialMatchByHugoSymbol called");
        return trialMatchRepository.findTrialMatchByHugoSymbolExists(hugoSymbols);
    }

    @Override
    public List<TrialMatch> getTrialMatchByNctIdAndHugoSymbol(String nctId, String hugoSymbol) {
        logger.debug("getTrialMatchByNctIdAndHugoSymbol called");
        return trialMatchRepository.findDistinctByNctIDAndAndHugoSymbol(nctId, hugoSymbol);
    }

    @Override
    public List<TrialMatch> getTrialMatchBySampleId(String sampleId) {
        logger.debug("getTrialMatchBySampleIds called");
        return trialMatchRepository.findTrialMatchBySampleID(sampleId);
    }

    @Override
    public List<TrialMatch> findDistinctByGenomicID(String genomicId) {
        logger.debug("findDistinctByGenomicID called");
        return trialMatchRepository.findDistinctByGenomicID(genomicId);
    }

    public TrialMatch saveTrialMatch(TrialMatch trialMatch) {
        logger.debug("saveTrialMatch called");
        return trialMatchRepository.save(trialMatch);
    }

    @Override
    public void delete(String id) {
        logger.debug("delete called");
        trialMatchRepository.delete(getTrialMatchById(id));
    }
}