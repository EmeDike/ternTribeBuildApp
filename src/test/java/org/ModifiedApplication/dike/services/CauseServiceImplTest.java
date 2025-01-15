package org.ModifiedApplication.dike.services;

import org.ModifiedApplication.dike.dtos.request.ContributionCauseRequest;
import org.ModifiedApplication.dike.dtos.request.CreateCauseRequest;
import org.ModifiedApplication.dike.dtos.request.UpdateCauseRequest;
import org.ModifiedApplication.dike.dtos.response.CauseResponse;
import org.ModifiedApplication.dike.dtos.response.ContributionResponse;
import org.ModifiedApplication.dike.dtos.response.CreateCauseResponse;
import org.ModifiedApplication.dike.dtos.response.UpdateCauseResponse;
import org.ModifiedApplication.dike.exceptions.CauseException;
import org.ModifiedApplication.dike.model.Cause;
import org.ModifiedApplication.dike.repositories.CauseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.smartcardio.CardException;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CauseServiceImplTest {

    @Autowired
    private CauseService causeService;
    @Autowired
    private CauseRepository causeRepository;

    @Test
    void createCause() {
        CreateCauseRequest createCauseRequest = new CreateCauseRequest();
        createCauseRequest.setId(1L);
        createCauseRequest.setTitle("first cause");
        createCauseRequest.setDescription("first cause on my laps");
        createCauseRequest.setImageUrl(",kjsuwyegfi7q34gf3gckefgiq37r37fyrcg3iyg");
        CreateCauseResponse createCauseResponse = causeService.createCause(createCauseRequest);
        assertThat(createCauseResponse.getId()).isNotNull();
        assertThat(createCauseResponse.getMessage()).isNotNull();
    }

    @Test
    void updateCause() {
        UpdateCauseRequest updateCauseRequest = new UpdateCauseRequest();
        updateCauseRequest.setId(1L);
        updateCauseRequest.setTitle("second cause");
        updateCauseRequest.setDescription("first cause on my head");
        updateCauseRequest.setImageUrl(",kjsuwyegfi7q34gf3gckefgiq37r37fyrcg3iyg");
        UpdateCauseResponse updateCauseResponse = causeService.updateCause(updateCauseRequest);
        assertThat(updateCauseResponse.getId()).isNotNull();
        assertThat(updateCauseResponse.getMessage()).isNotNull();
    }

    @Test
    void findCause() throws CauseException {
        Long id = 1L;
        CauseResponse causeResponse = causeService.getCauseById(id);
        assertThat(causeResponse).isNotNull();
    }

    @Test
    void deleteCause() {
        Long causeId = 1L;

        CauseException exception = assertThrows(CauseException.class, () -> causeService.deleteCauseById(causeId));
        assertEquals("cause not found", exception.getMessage());


    }
    @Test
    void contributeCause() {
        Long causeId = 1L;
        Cause cause = new Cause();
        cause.setId(causeId);
        cause.setTitle("Save the Environment");
        causeRepository.save(cause);

        ContributionCauseRequest contributionCauseRequest = new ContributionCauseRequest();
        contributionCauseRequest.setName("Dike");
        contributionCauseRequest.setAmount(BigDecimal.valueOf(2000));
        contributionCauseRequest.setEmail("dike1@gmail.com");

        ContributionResponse contributionResponse = causeService.contributeToACause(contributionCauseRequest, causeId);

        assertThat(contributionResponse).isNotNull();
        assertThat(contributionResponse.getId()).isNotNull();
        assertThat(contributionResponse.getMessage()).isEqualTo("Contributed successfully to cause: Save the Environment");
    }
}