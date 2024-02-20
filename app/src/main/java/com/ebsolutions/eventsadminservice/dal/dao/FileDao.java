package com.ebsolutions.eventsadminservice.dal.dao;

import io.micronaut.context.annotation.Prototype;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
@Prototype
public class FileDao {

    public String read(String fileName) throws IOException {
        log.info(fileName);

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        StringBuilder resultStringBuilder = new StringBuilder();

        if (inputStream == null) {
            throw new IOException("Resource File Not Found");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }

        return resultStringBuilder.toString();
    }

    public InputStream create(String inputText) throws IOException {
        if (inputText == null) {
            return null;
        }
        InputStream returnedValue = new ByteArrayInputStream(
                inputText.getBytes(StandardCharsets.UTF_8));

        try (returnedValue) {
            return returnedValue;
        } catch (Exception e) {
            log.error("ERROR::{}", this.getClass().getName(), e);
            throw new IOException("IO Melted!!");
        }
    }
}
