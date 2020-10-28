package com.github.hobbylabs.httpstatushandling.app;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class HttpStatusHandlingController {

    @Data
    private static class RequestData {
        private String message;
    }

    @Data
    private static class ErrorData {
        private int status;
        private String errorMessage;
    }

    @Autowired
    private MessageSource messageSource;

    @PostMapping
    public void doStuff(@RequestBody RequestData requestData) {
        System.out.println(requestData.getMessage());
        if (requestData.getMessage().equals("foo")) {
            throw new FooException("Foo 例外");
        };
    }

    private static class FooException extends RuntimeException {
        public FooException(String message) {
            super(message);
        }
    }

    @ResponseBody
    @ExceptionHandler(FooException.class)
    public Map<String, Object> handleFooException(FooException e) throws IOException {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Error foo");
        e.printStackTrace();

        return result;
    }
}
