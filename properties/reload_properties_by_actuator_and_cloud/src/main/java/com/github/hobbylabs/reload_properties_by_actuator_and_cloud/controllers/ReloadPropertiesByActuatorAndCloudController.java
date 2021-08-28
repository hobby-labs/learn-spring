package com.github.hobbylabs.reload_properties_by_actuator_and_cloud.controllers;

import com.github.hobbylabs.reload_properties_by_actuator_and_cloud.services.ReloadPropertiesByActuatorAndCloudService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ReloadPropertiesByActuatorAndCloudController {
    private final ReloadPropertiesByActuatorAndCloudService reloadPropertiesByActuatorAndCloudService;

    @GetMapping(value = {"getReloadProperties"})
    public ResponseEntity<Void> getProduct() {
        reloadPropertiesByActuatorAndCloudService.getReloadProperties();
        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.OK);
        return response;
    }
}
