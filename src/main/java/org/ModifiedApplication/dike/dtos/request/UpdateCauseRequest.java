package org.ModifiedApplication.dike.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCauseRequest {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
}
