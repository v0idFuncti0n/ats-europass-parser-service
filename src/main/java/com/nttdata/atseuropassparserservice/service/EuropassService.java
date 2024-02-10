package com.nttdata.atseuropassparserservice.service;

import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class EuropassService {
    public String europassToJson(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String resultpdf =  IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        String xmlContent = resultpdf.substring(resultpdf.indexOf("<?xml") , resultpdf.indexOf("</Candidate>") + 13 );
        return  xmlContent;



    }
}
