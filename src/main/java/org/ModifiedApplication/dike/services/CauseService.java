package org.ModifiedApplication.dike.services;

import org.ModifiedApplication.dike.dtos.request.ContributionCauseRequest;
import org.ModifiedApplication.dike.dtos.request.CreateCauseRequest;
import org.ModifiedApplication.dike.dtos.request.UpdateCauseRequest;
import org.ModifiedApplication.dike.dtos.response.CauseResponse;
import org.ModifiedApplication.dike.dtos.response.ContributionResponse;
import org.ModifiedApplication.dike.dtos.response.CreateCauseResponse;
import org.ModifiedApplication.dike.dtos.response.UpdateCauseResponse;
import org.ModifiedApplication.dike.model.Cause;

import java.util.List;

public interface CauseService {
    CreateCauseResponse createCause(CreateCauseRequest createCauseRequest);
    UpdateCauseResponse updateCause(UpdateCauseRequest updateCauseRequest);
    CauseResponse getCauseById(Long id);
    List<Cause> getAllCauses();
    void deleteCauseById(Long id);
    ContributionResponse contributeToACause(ContributionCauseRequest contributionCauseRequest, Long causeId);

}
