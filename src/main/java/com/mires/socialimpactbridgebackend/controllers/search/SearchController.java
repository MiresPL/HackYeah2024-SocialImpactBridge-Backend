package com.mires.socialimpactbridgebackend.controllers.search;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mires.socialimpactbridgebackend.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping(value = "/getAllNGOs", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> getAllNGOs() {
        return searchService.getAllNGOs();
    }

    @PostMapping(value = "/getAllFirms", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> getAllFirms() {
        return searchService.getAllFirms();
    }

    @PostMapping(value = "/findNGOsByPkdCode", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> findNGOsByPkdCode(@RequestBody Map<String, Object> body) {
        return searchService.findNGOsByPkdCode(JsonParser.parseString(body.get("pkdCodeIds").toString()).getAsJsonArray().asList().stream().map(JsonElement::getAsString).toList(), body.get("voivodeship").toString());
    }

    @PostMapping(value = "/findFirmsByPkdCode", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> findFirmsByPkdCode(@RequestBody Map<String, Object> body) {
        return searchService.findFirmsByPkdCode(JsonParser.parseString(body.get("pkdCodeIds").toString()).getAsJsonArray().asList().stream().map(JsonElement::getAsString).toList(), body.get("voivodeship").toString());
    }

    @PostMapping(value = "/findNGOsByName", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> findNGOsByName(@RequestBody Map<String, Object> body) {
        return searchService.findNGOsByName(body.get("name").toString());
    }

    @PostMapping(value = "/findFirmsByName", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> findFirmsByName(@RequestBody Map<String, Object> body) {
        return searchService.findFirmsByName(body.get("name").toString());
    }


}
