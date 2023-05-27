package com.simmondobber.lomboker.lombokize;

import com.simmondobber.lomboker.lombokize.transportObjects.CodeToLombokizeTO;
import com.simmondobber.lomboker.lombokize.transportObjects.LombokizedCodeTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public LombokizedCodeTO lombokize(@RequestBody CodeToLombokizeTO codeToLombokizeTO) {
        return this.lombokizeService.lombokize(codeToLombokizeTO);
    }
}
