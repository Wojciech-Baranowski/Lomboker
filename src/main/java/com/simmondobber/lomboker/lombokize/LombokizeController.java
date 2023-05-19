package com.simmondobber.lomboker.lombokize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LombokizeController {

    private final LombokizeService lombokizeService;

    @Autowired
    public LombokizeController(LombokizeService lombokizeService) {
        this.lombokizeService = lombokizeService;
    }

    @PostMapping("/lombokize")
    public ResponseEntity<String> lombokize(@RequestBody String classCode) {
        return new ResponseEntity<>(this.lombokizeService.lombokize(classCode), HttpStatus.OK);
    }
}
