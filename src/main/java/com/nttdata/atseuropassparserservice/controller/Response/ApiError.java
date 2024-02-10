package com.nttdata.atseuropassparserservice.controller.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApiError {
    private int status;
    private String message;
}