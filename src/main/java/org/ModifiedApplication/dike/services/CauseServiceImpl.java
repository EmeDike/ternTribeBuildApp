package org.ModifiedApplication.dike.services;

import lombok.AllArgsConstructor;
import org.ModifiedApplication.dike.dtos.request.ContributionCauseRequest;
import org.ModifiedApplication.dike.dtos.request.CreateCauseRequest;
import org.ModifiedApplication.dike.dtos.request.UpdateCauseRequest;
import org.ModifiedApplication.dike.dtos.response.CauseResponse;
import org.ModifiedApplication.dike.dtos.response.ContributionResponse;
import org.ModifiedApplication.dike.dtos.response.CreateCauseResponse;
import org.ModifiedApplication.dike.dtos.response.UpdateCauseResponse;
import org.ModifiedApplication.dike.exceptions.CauseException;
import org.ModifiedApplication.dike.model.Cause;
import org.ModifiedApplication.dike.model.Contributions;
import org.ModifiedApplication.dike.repositories.CauseRepository;
import org.ModifiedApplication.dike.repositories.ContributionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CauseServiceImpl implements CauseService {
    private final CauseRepository causeRepository;
    private final ModelMapper mapper = new ModelMapper();
    private final ContributionRepository contributionRepository;


    @Override
    public CreateCauseResponse createCause(CreateCauseRequest createCauseRequest) {
        if(causeRepository.existsById(createCauseRequest.getId())) throw new CauseException("Cause already exists");
       if(createCauseRequest.getId() == null) {
           throw new CauseException("createCauseRequest.id");
       }
       Cause cause = new Cause();
       cause.setId(createCauseRequest.getId());
       cause.setDescription(createCauseRequest.getDescription());
       cause.setTitle(createCauseRequest.getTitle());
       cause.setImageUrl(createCauseRequest.getImageUrl());
       causeRepository.save(cause);
       CreateCauseResponse createCauseResponse = new CreateCauseResponse();
       createCauseResponse.setId(cause.getId());
       createCauseResponse.setMessage(cause.getDescription());
       return createCauseResponse;
    }

    @Override
    public UpdateCauseResponse updateCause(UpdateCauseRequest updateCauseRequest) {
        Cause findCause = causeRepository.findById(updateCauseRequest.getId()).orElseThrow(
                () -> new CauseException("cause with id" + updateCauseRequest.getId() + " not found"));

        findCause.setId(updateCauseRequest.getId());
        findCause.setDescription(updateCauseRequest.getDescription());
        findCause.setTitle(updateCauseRequest.getTitle());
        findCause.setImageUrl(updateCauseRequest.getImageUrl());
        causeRepository.save(findCause);
        UpdateCauseResponse updateCauseResponse = new UpdateCauseResponse();
        updateCauseResponse.setId(findCause.getId());
        updateCauseResponse.setMessage(findCause.getDescription());
        return updateCauseResponse;
    }

    @Override
    public CauseResponse getCauseById(Long id) {
        return mapper.map(findCauseById(id), CauseResponse.class);
    }

    @Override
    public List<Cause> getAllCauses() {
        return causeRepository.findAll();
    }

    private Cause findCauseById(Long id) throws CauseException{
        return causeRepository.findById(id)
                .orElseThrow(() -> new CauseException("cause not found"));
    }

    @Override
    public void deleteCauseById(Long id) {
        Cause cause = causeRepository.findById(id).orElseThrow(() -> new CauseException("cause not found"));
        causeRepository.delete(cause);

    }

    @Override
    public ContributionResponse contributeToACause(ContributionCauseRequest contributionCauseRequest, Long causeId) {
        Cause foundCause = causeRepository.findById(causeId)
                .orElseThrow(() -> new CauseException("cause not found"));

        Contributions contributions = new Contributions();
        contributions.setName(contributionCauseRequest.getName());
        contributions.setEmail(contributionCauseRequest.getEmail());
        contributions.setAmount(contributionCauseRequest.getAmount());
        contributions.setCause(foundCause);
        Contributions savedContribution = contributionRepository.save(contributions);

        ContributionResponse contributionResponse = new ContributionResponse();
        contributionResponse.setId(savedContribution.getId());
        contributionResponse.setMessage("Contributed successfully to cause: " + foundCause.getTitle());
        return contributionResponse;
    }
}
