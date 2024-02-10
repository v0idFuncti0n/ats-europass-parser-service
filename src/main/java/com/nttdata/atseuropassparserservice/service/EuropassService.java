package com.nttdata.atseuropassparserservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;



@Service
public class EuropassService {
    public JsonNode europassToJson(MultipartFile file) throws IOException {
        String pdfRawContent =  IOUtils.toString(file.getInputStream(), StandardCharsets.UTF_8);

        // Check if Europass PDF has XML SCHEMA
        if(!pdfRawContent.contains("http://www.europass.eu/1.0 Candidate.xsd")) {
            throw new IOException("Not Valid Europass PDF");
        }
        // Get XML part inside the Europass PDF
        String xmlContent =  pdfRawContent.substring(pdfRawContent.indexOf("<?xml") , pdfRawContent.indexOf("</Candidate>") + 13 );

        XmlMapper xmlMapper = new XmlMapper();
        // Map the XML to JSON
        return xmlMapper.readTree(xmlContent.getBytes());
    }
}
