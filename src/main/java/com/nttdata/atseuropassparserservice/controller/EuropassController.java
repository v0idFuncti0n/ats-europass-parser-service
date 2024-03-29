package com.nttdata.atseuropassparserservice.controller;

import com.nttdata.atseuropassparserservice.controller.Response.ApiError;
import com.nttdata.atseuropassparserservice.service.EuropassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EuropassController {

    private final EuropassService europassService;

    @Autowired
    public EuropassController(EuropassService europassService) {
        this.europassService = europassService;
    }

    @GetMapping(path = "/europass")
    public ResponseEntity<Object> europassToJson(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(europassService.europassToJson(file));
        } catch (Exception exception) {
            ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
        }
    }
}
