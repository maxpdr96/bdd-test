package com.hidarisoft.bddautomationapi.controller;

import com.hidarisoft.bddautomationapi.model.BddModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BddController {

    @PostMapping(value = "/v3/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BddModel> addUser(@RequestBody String body) {
        var bddModel = new BddModel(1,"miu","Miu","Santos","miu@email.com","teste","12345",1);

        return ResponseEntity.ok(bddModel);
    }

    @GetMapping(value = "/v3/user/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BddModel> getUser(@PathVariable String name) {
        var bddModel = new BddModel(1,"miu","Miu","Santos","miu@email.com","teste","12345",1);

        return ResponseEntity.ok(bddModel);
    }
}
