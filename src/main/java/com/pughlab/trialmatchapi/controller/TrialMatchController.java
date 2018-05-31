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

    @Autowired
    public void setTrialMatchService(TrialMatchService trialMatchService) {
        this.trialMatchService = trialMatchService;
    }

    @Autowired
    public void setGenomicService(GenomicService genomicService) {
        this.genomicService = genomicService;
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
        trialMatchVariantMap.put("id", genomicId);
        trialMatchVariantMap.put("symbol", genomic.getHugoSymbol());
        trialMatchVariantMap.put("protein", genomic.getProteinChange().replace("p.",""));
        trialMatchVariantMap.put("mutEffect", genomic.getMutEffect());
        trialMatchVariantMap.put("oncogenicity", genomic.getOncogenicity());
        trialMatchVariantMap.put("sample", genomic.getSampleId());
        trialMatchVariantMap.put("matches", trialMatchService.findDistinctByGenomicID(genomicId));
        return  trialMatchVariantMap;
    }

    private HashMap getTrialMatchesByProteinChangeOfGeneAndSampleID(String gene, String proteinChange, String sampleId) {
        HashMap<String, Object> trialMatchVariantMap = new HashMap<String, Object>();
        Genomic genomic = genomicService.getGenomicByProteinChangeAndSampleId(proteinChange, sampleId);
        trialMatchVariantMap.put("id", genomic.getId());
        trialMatchVariantMap.put("symbol", genomic.getHugoSymbol());
        trialMatchVariantMap.put("protein", genomic.getProteinChange().replace("p.",""));
        trialMatchVariantMap.put("mutEffect", genomic.getMutEffect());
        trialMatchVariantMap.put("oncogenicity", genomic.getOncogenicity());
        trialMatchVariantMap.put("sample", genomic.getSampleId());
        trialMatchVariantMap.put("matches", trialMatchService.findDistinctByGeneAndProteinChangeAndSampleID(gene, proteinChange, sampleId));
        return  trialMatchVariantMap;
    }

    private Boolean variantExists(List<HashMap> variants, HashMap variant) {
        for(HashMap var: variants) {
            if (var.equals(variant)) {
                return true;
            }
        }
        return false;
    }

    private HashMap getTrialMatchVariantsByHugoSymbol(String symbol) {
        HashMap<String, Object> trialMatchMap = new HashMap<String, Object>() {{
            put("symbol", symbol);
        }};
        List<HashMap> variants = new ArrayList<HashMap>();
        List<TrialMatch> matches = trialMatchService.getTrialMatchByHugoSymbol(symbol);
        matches.forEach(match -> {
            if (match.getProteinChange() !=null) {
                HashMap<String, String> variant = new HashMap<String, String>() {{
                    put("protein",match.getProteinChange().replace("p.",""));
                    put("sample", match.getSampleID()); }};
                if (!(variantExists(variants, variant))) {
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
            value = "/matches/variants/{id}")
    public HashMap findTrialMatchesByVariant(@PathVariable String id) {
        return getTrialMatchesByGenomicId(id);
    }

    @ApiOperation(value = "View available trial matches with given protein change of the gene and sample ID",
            response = HashMap.class)
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/matches/{symbol}/{protein}")
    public HashMap findTrialMatchesByVariantOfGeneAndSampleId(@PathVariable("symbol") String symbol,
                                                              @PathVariable("protein") String protein,
                                                              @RequestParam("sample") String sample) {
        return getTrialMatchesByProteinChangeOfGeneAndSampleID(
                symbol, (protein.indexOf("p.") == -1) ? "p."+protein : protein, sample);
    }

    @ApiOperation(value = "View available variants of trial matches with a list of genes, separated by comma",
                  response = HashMap.class)
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/matches/genes/{symbols}")
    public List<HashMap> findTrialMatcheVariantsByGenes(@PathVariable String symbols) {
        List<HashMap> trialMatchList = new ArrayList<HashMap>();
        List<String> genes = Arrays.asList(symbols.trim().split(","));
        genes.forEach(gene -> {
            trialMatchList.add(getTrialMatchVariantsByHugoSymbol(gene));
        });
        return trialMatchList;
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