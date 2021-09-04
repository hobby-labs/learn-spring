package com.github.hobbylabs.ldapsample.controller;

import com.github.hobbylabs.ldapsample.data.People;
import com.github.hobbylabs.ldapsample.data.request.Query;
import com.github.hobbylabs.ldapsample.data.request.RequestQueryRoot;
import com.github.hobbylabs.ldapsample.data.request.Search;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.github.hobbylabs.ldapsample.service.LdapSampleService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class LdapSampleController {

    private final LdapSampleService ldapSampleService;

    @GetMapping(value = {"/getLdapData"})
    public List<People> getLdapData() {
        return ldapSampleService.getLdapData();
    }
}
