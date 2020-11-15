package com.base.useinsider.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private String status;

}
