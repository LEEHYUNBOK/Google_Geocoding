package com.example.springintroduction.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.model.GeocodingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.google.maps.GeocodingApi;

@RestController
public class GeocodingController {

    // 참고 : https://github.com/PaulEmmanuelSotir/TPs_3IF/blob/51e1b82837bd2e9e01fe84721f127c469f1f24a7/TP2_SI/services/src/main/java/test/testLivreur.java
    @GetMapping("/geocode")
    public ResponseEntity<GeocodingResult[]> getGeocode() throws Exception {
        GeoApiContext context = new GeoApiContext().setApiKey("google API 키");
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                "경복궁").await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(results[0]));

// Invoke .shutdown() after your application is done making requests
        return ResponseEntity.ok(results);
    }


}
