package com.nttdata.atseuropassparserservice.service;

import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;


@Service
public class EuropassService {
    public String europassToJson(MultipartFile file) throws IOException {
        String pdfRawContent =  IOUtils.toString(file.getInputStream(), StandardCharsets.UTF_8);
        // Get XML part inside the Europass PDF
        return pdfRawContent.substring(pdfRawContent.indexOf("<?xml") , pdfRawContent.indexOf("</Candidate>") + 13 );
    }
}
