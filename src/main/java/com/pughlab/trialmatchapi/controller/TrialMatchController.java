package com.pughlab.trialmatchapi.controller;

import io.swagger.annotations.*;
import com.pughlab.trialmatchapi.domain.*;
import com.pughlab.trialmatchapi.web.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java .util.ArrayList;
import java.util.Arrays;

/**
 * REST web services for retrieving matched patient clinical and genomic information to trials
 *
 * @author  Kelsey Zhu
 * @version 1.0
 * @since   2018-04-09
 */

@RestController
@RequestMapping("/api")
@Api(value="trialmatchapi", description="Operations pertaining to retrieving clinical and genomic information for trials")
public class TrialMatchController {

    private TrialMatchService trialMatchService;
    private GenomicService genomicService;
    private ClinicalService clinicalService;

    @Autowired
    public void setTrialMatchService(TrialMatchService trialMatchService) {
        this.trialMatchService = trialMatchService;
    }

    @Autowired
    public void setGenomicService(GenomicService genomicService) {
        this.genomicService = genomicService;
    }

    @Autowired
    public void setClinicalService(ClinicalService clinicalService) {
        this.clinicalService = clinicalService;
    }

    @ApiOperation(value = "Add a trial Match")
    @RequestMapping(value = "/matches/add", method = RequestMethod.POST, produces = "application/json")
    public void createTrialMatch(@RequestBody TrialMatch trialMatch) {
        trialMatchService.saveTrialMatch(trialMatch);
    }

    @ApiOperation(value = "View a list of available trial matches",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(method = RequestMethod.GET, value="/matches")
    public Iterable<TrialMatch> findAllTrialMatches() {
        return trialMatchService.listAllTrialMatches();
    }

    @ApiOperation(value = "Search a trial match with an ID",response = TrialMatch.class)
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/matches/id/{id}")
    public TrialMatch findTrialMatchById(@PathVariable String id) {
        return trialMatchService.getTrialMatchById(id);
    }

    private HashMap getTrialMatchesByGenomicId(String genomicId) {
        HashMap<String, Object> trialMatchVariantMap = new HashMap<String, Object>();
        Genomic genomic = genomicService.getGenomicById(genomicId);
        trialMatchVariantMap.put("genomicId", genomicId);
        trialMatchVariantMap.put("hugoSymbol", genomic.getHugoSymbol());
        trialMatchVariantMap.put("proteinChange", genomic.getProteinChange().replace("p.",""));
        trialMatchVariantMap.put("exonNumber", genomic.getExonNumber());
        trialMatchVariantMap.put("mutEffect", genomic.getMutEffect());
        trialMatchVariantMap.put("oncogenicity", genomic.getOncogenicity());
        trialMatchVariantMap.put("sampleId", genomic.getSampleId());
        trialMatchVariantMap.put("matches", trialMatchService.findDistinctByGenomicID(genomicId));
        return  trialMatchVariantMap;
    }

    private HashMap getTrialMatchesByProteinChangeOfGeneAndSampleID(String gene, String proteinChange, String sampleId) {
        HashMap<String, Object> trialMatchVariantMap = new HashMap<String, Object>();
        Genomic genomic = genomicService.getGenomicByProteinChangeAndSampleId(proteinChange, sampleId);
        Clinical clinical = clinicalService.getClinicalBySampleId(sampleId);
        trialMatchVariantMap.put("genomicId", genomic.getId());
        trialMatchVariantMap.put("hugoSymbol", genomic.getHugoSymbol());
        trialMatchVariantMap.put("proteinChange", genomic.getProteinChange().replace("p.",""));
        trialMatchVariantMap.put("exonNumber", genomic.getExonNumber());
        trialMatchVariantMap.put("cancerType", clinical.getCancerType());
        trialMatchVariantMap.put("mutEffect", genomic.getMutEffect());
        trialMatchVariantMap.put("oncogenicity", genomic.getOncogenicity());
        trialMatchVariantMap.put("matches", trialMatchService.findDistinctByGeneAndProteinChangeAndSampleID(gene, proteinChange, sampleId));
        return  trialMatchVariantMap;
    }

    private HashMap getTrialMatchVariantsByHugoSymbol(String symbol) {
        HashMap<String, Object> trialMatchMap = new HashMap<String, Object>() {{
            put("hugoSymbol", symbol);
        }};
        List<HashMap> variants = new ArrayList<HashMap>();
        List<TrialMatch> matches = trialMatchService.getTrialMatchByHugoSymbol(symbol);
        matches.forEach(match -> {
            if (match.getProteinChange() !=null) {
                HashMap<String, String> variant = new HashMap<String, String>() {{
                    put("proteinChange",match.getProteinChange().replace("p.",""));
                    put("sampleId", match.getSampleID()); }};
                if (!variants.contains(variant)) {
                    variants.add(variant);
                }
            }
        });

        trialMatchMap.put("variants", variants);
        return trialMatchMap;
    }

    private List<TrialMatch> getTrialMatchBySampleIds(List<String> sampleIds) {
        List<TrialMatch> trials = new ArrayList<TrialMatch>();
        sampleIds.forEach(sampleId -> {
            if (!sampleId.isEmpty()) {
                trials.addAll(trialMatchService.getTrialMatchBySampleId(sampleId));
            }
        });
        return trials;
    }

    @ApiOperation(value = "View available trial matches with a given protein change",
                  response = HashMap.class)
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/matches/variants/{genomicId}")
    public HashMap findTrialMatchesByVariant(@PathVariable String genomicId) {
        return getTrialMatchesByGenomicId(genomicId);
    }

    @ApiOperation(value = "View available trial matches with a given protein change of the gene and sample ID or a list of sample IDs seperated by comma",
            response = HashMap.class)
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/matches/{hugoSymbol}/{proteinChange}")
    public HashMap findTrialMatchesByVariantOfGeneAndSample(@PathVariable("hugoSymbol") String hugoSymbol,
                                                              @PathVariable("proteinChange") String proteinChange,
                                                              @RequestParam("sample") String sample) {
        HashMap<String, HashMap> trialMatchMap = new HashMap<String, HashMap>();
        List<String> sampleIds = Arrays.asList(sample.trim().split(","));
        sampleIds.forEach(sampleId ->
                trialMatchMap.put(sampleId, getTrialMatchesByProteinChangeOfGeneAndSampleID(
                        hugoSymbol, (proteinChange.indexOf("p.") == -1) ? "p."+proteinChange : proteinChange, sampleId)));
        return trialMatchMap;
    }

    @ApiOperation(value = "View available variants of trial matches with a list of genes, separated by comma",
                  response = HashMap.class)
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/matches/genes/{hugoSymbols}")
    public List<HashMap> findTrialMatcheVariantsByGenes(@PathVariable String hugoSymbols) {
        List<HashMap> trialMatchVariantList = new ArrayList<HashMap>();
        List<String> genes = Arrays.asList(hugoSymbols.trim().split(","));
        genes.forEach(gene -> {
            trialMatchVariantList.add(getTrialMatchVariantsByHugoSymbol(gene));
        });
        return trialMatchVariantList;
    }

    @ApiOperation(value = "View available trial matches with a list of sample IDs, separated by comma",
                  response = List.class)
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/matches/samples/{ids}")
    public List<TrialMatch> findTrialMatchesBySampleIds(@PathVariable String ids) {
        List<String> sampleIds = Arrays.asList(ids.trim().split(","));
        return getTrialMatchBySampleIds(sampleIds);
    }

    @ApiOperation(value = "Delete a trial match")
    @RequestMapping(value="/matches/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteTrialMatchWithId(@PathVariable String id) {
        trialMatchService.delete(id);
    }

}