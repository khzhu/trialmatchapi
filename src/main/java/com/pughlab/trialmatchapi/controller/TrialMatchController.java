package com.pughlab.trialmatchapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.pughlab.trialmatchapi.domain.TrialMatch;
import com.pughlab.trialmatchapi.web.TrialMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST web services for retrieving matched patient clinical and genomic information to trials
 *
 * @author  Kelsey Zhu
 * @version 1.0
 * @since   2018-04-09
 */

@RestController
@RequestMapping("/api")
@Api(value="trialmatchapi", description="Operations pertaining to retriving clinical and genomic information to trials")
public class TrialMatchController {

    private TrialMatchService trialMatchService;

    @Autowired
    public void setProductService(TrialMatchService trialMatchService) {
        this.trialMatchService = trialMatchService;
    }

    @ApiOperation(value = "Add a product")
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

    @ApiOperation(value = "View a list of available trial matches with a gene",response = Iterable.class)
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/matches/gene/{gene}")
    public Iterable<TrialMatch> findBookByHugoSymbol(@PathVariable String gene) {

        return trialMatchService.getTrialMatchByHugoSymbol(gene);
    }

    @ApiOperation(value = "Delete a trial match")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteBookWithId(@PathVariable String id) {
        trialMatchService.delete(id);
    }

}