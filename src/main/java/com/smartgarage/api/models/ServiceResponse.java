package com.smartgarage.api.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceResponse {
    private Integer id;
    private String name;
    private Integer price;
    private BaseService baseService;
}
