package com.github.hobbylabs.reload_properties.controllers;

import com.github.hobbylabs.reload_properties.services.ReloadPropertiesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ReloadPropertiesController {

    private final ReloadPropertiesService reloadPropertiesService;

    @GetMapping(value = {"getReloadProperties"})
    public ResponseEntity<Void> getProduct() {
        reloadPropertiesService.getReloadProperties();
        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.OK);
        return response;
    }
}
