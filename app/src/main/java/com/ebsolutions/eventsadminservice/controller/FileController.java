package com.ebsolutions.eventsadminservice.controller;

import com.ebsolutions.eventsadminservice.dal.dao.FileDao;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static io.micronaut.http.HttpResponse.ok;
import static io.micronaut.http.HttpResponse.serverError;

@Slf4j
@Controller("/files")
public class FileController {
    private final FileDao fileDao;

    public FileController(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    @Get(value = "/{fileName}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> get(@PathVariable String fileName) {
        try {
            log.info(fileName);

            String fileContent = this.fileDao.read(fileName);

            return ok(fileContent);
        } catch (IOException ioException) {
            return serverError(ioException);
        }
    }

    @Post()
    public HttpResponse<?> post(@Body Map<String, String> input) {
        try {
            String firstKey = input.keySet().stream().findFirst().orElse("Empty body provided");
//            return ok(this.fileDao.create(input.get(firstKey)));
            return ok(new String(this.fileDao.create(input.get(firstKey)).readAllBytes(), StandardCharsets.UTF_8));

//            return ok(input);
        } catch (IOException ioException) {
            return serverError(ioException);
        }
    }
}