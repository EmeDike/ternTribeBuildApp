package org.ModifiedApplication.dike.controller;

import jdk.jfr.Category;
import org.ModifiedApplication.dike.dtos.request.ContributionCauseRequest;
import org.ModifiedApplication.dike.dtos.request.CreateCauseRequest;
import org.ModifiedApplication.dike.dtos.request.UpdateCauseRequest;
import org.ModifiedApplication.dike.dtos.response.CauseResponse;
import org.ModifiedApplication.dike.dtos.response.ContributionResponse;
import org.ModifiedApplication.dike.dtos.response.CreateCauseResponse;
import org.ModifiedApplication.dike.dtos.response.UpdateCauseResponse;
import org.ModifiedApplication.dike.model.Cause;
import org.ModifiedApplication.dike.services.CauseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CauseController {


    private final CauseService causeService;

    public CauseController(CauseService causeService) {
        this.causeService = causeService;
    }

    @PostMapping("/createCause")
    public ResponseEntity<CreateCauseResponse> createCause(@RequestBody CreateCauseRequest createCauseRequest) {
        CreateCauseResponse response = causeService.createCause(createCauseRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateCause")
    public ResponseEntity<UpdateCauseResponse> updateCause(@RequestBody UpdateCauseRequest createCauseRequest) {
        UpdateCauseResponse response = causeService.updateCause(createCauseRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findCause")
    public ResponseEntity<CauseResponse> findById(@PathVariable("id") Long id) {
        CauseResponse response = causeService.getCauseById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/findAllCause")
    public ResponseEntity<List<Cause>> findAllCause(){
        List<Cause> response = causeService.getAllCauses();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("deleteCause")
    public ResponseEntity<Long> deleteById(@PathVariable("id") Long id) {
        causeService.deleteCauseById(id);
        return ResponseEntity.ok(id);
    }
    @GetMapping("/contributeCause")
    public ResponseEntity<ContributionResponse> contributeCause(@RequestBody ContributionCauseRequest contributionCauseRequest, Long causeId) {
        ContributionResponse response = causeService.contributeToACause(contributionCauseRequest, Long.valueOf(causeId));
        return ResponseEntity.ok(response);
    }

}
