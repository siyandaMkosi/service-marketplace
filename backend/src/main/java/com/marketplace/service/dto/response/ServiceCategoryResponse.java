package com.marketplace.service.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCategoryResponse {

    private Long id;

    private String code;

    private String name;

}
