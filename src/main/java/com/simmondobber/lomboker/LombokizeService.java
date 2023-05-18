package com.simmondobber.lomboker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LombokizeService {

    @Autowired
    public LombokizeService() {

    }

    public String lombokize(String classCode) {
        List<String> CodeLinesTexts = Arrays.asList(classCode.split("\n"));


        return classCode;
    }

}
