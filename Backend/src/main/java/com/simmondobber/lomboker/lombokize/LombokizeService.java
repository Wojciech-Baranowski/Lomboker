package com.simmondobber.lomboker.lombokize;

import com.simmondobber.lomboker.lombokize.exceptions.LombokizeException;
import com.simmondobber.lomboker.lombokize.helpers.transformators.Lombokizer;
import com.simmondobber.lomboker.lombokize.transportObjects.CodeToLombokizeTO;
import com.simmondobber.lomboker.lombokize.transportObjects.LombokizedCodeTO;
import org.springframework.stereotype.Service;

@Service
public class LombokizeService {

    public LombokizedCodeTO lombokize(CodeToLombokizeTO codeToLombokizeTO) {
        try {
            Lombokizer lombokizer = new Lombokizer(codeToLombokizeTO);
            String lombokizedCode = lombokizer.lombokize(codeToLombokizeTO.getCodeToLombokize());
            return new LombokizedCodeTO(lombokizedCode);
        } catch (Exception e) {
            throw new LombokizeException("Lombokize process has failed.", e);
        }
    }
}
