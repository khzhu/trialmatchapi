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
    public void setProductRepository(TrialMatchRepository trialMatchRepository) {
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
    public TrialMatch saveTrialMatch(TrialMatch trialMatch) {
        logger.debug("saveProduct called");
        return trialMatchRepository.save(trialMatch);
    }

    @Override
    public void delete(String id) {
        logger.debug("deleteProduct called");
        trialMatchRepository.delete(getTrialMatchById(id));
    }
}
