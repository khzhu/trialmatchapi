package com.pughlab.trialmatchapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.pughlab.trialmatchapi.domain.*;
import com.pughlab.trialmatchapi.web.*;
import org.bson.types.ObjectId;
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
@Api(value="trialmatchapi", description="Operations pertaining to retriving clinical and genomic information for trials")
public class TrialMatchController {

    private TrialMatchService trialMatchService;
    private TrialService trialService;

    @Autowired
    public void setTrialMatchService(TrialMatchService trialMatchService) {
        this.trialMatchService = trialMatchService;
    }

    @Autowired
    public void setTrialService(TrialService trialService) {
        this.trialService = trialService;
    }

    @ApiOperation(value = "Add a Trial Match")
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

    private HashMap getTrialMatchesByHugoSymbol(String symbol) {
        HashMap<String, Object> trialMatches = new HashMap<String, Object>();
        trialMatches.put("name", symbol);
        List<HashMap> trails = new ArrayList<HashMap>();

        for (Trial trial: trialService.listAllTrials()) {
            HashMap<String, Object> trialMap= new HashMap<String, Object>();
            trialMap.put("title", trial.getTitle());
            trialMap.put("nctId", trial.getNctID());
            trialMap.put("status", trial.getStatus());
            trialMap.put("matches", trialMatchService.getTrialMatchByNctIdAndHugoSymbol(trial.getNctID(),symbol));
            trails.add(trialMap);
        }
        trialMatches.put("trials", trails);

        return trialMatches;
    }

    @ApiOperation(value = "View a list of available trial matches with a gene",response = Iterable.class)
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/matches/gene/{symbol}")
    public HashMap findTrialMatchesByGene(@PathVariable String symbol) {
        return getTrialMatchesByHugoSymbol(symbol);
    }

    @ApiOperation(value = "View available trial matches with a list of genes, seperated by comma",response = List.class)
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/matches/genes/{symbols}")
    public List<HashMap> findTrialMatchesByGenes(@PathVariable String symbols) {
        List<HashMap> trialMatches = new ArrayList<HashMap>();
        List<String> genes = Arrays.asList(symbols.trim().split(","));
        genes.forEach(gene-> trialMatches.add(getTrialMatchesByHugoSymbol(gene)));
        return trialMatches;
    }

    @ApiOperation(value = "Delete a trial match")
    @RequestMapping(value="/matches/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteTrialMatchWithId(@PathVariable String id) {
        trialMatchService.delete(id);
    }

}