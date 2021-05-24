package com.github.hobbylabs.ldapsample.controller;

import com.github.hobbylabs.ldapsample.domain.LdapData;
import com.github.hobbylabs.ldapsample.service.LdapSampleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class LdapSampleController {

    private final LdapSampleService ldapSampleService;

    @RequestMapping(value = {"/getLdapData"}, method = RequestMethod.GET)
    public LdapData getLdapData() {
        return ldapSampleService.getLdapData();
    }
}
