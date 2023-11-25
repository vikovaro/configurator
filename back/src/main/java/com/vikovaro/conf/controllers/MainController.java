package com.vikovaro.conf.controllers;

import com.vikovaro.conf.DTO.*;
import com.vikovaro.conf.utilities.CreateConfigutation;
import com.vikovaro.conf.utilities.DatabaseConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getConfiguration")
    public ResponseEntity<?> processGetConfiguration(@RequestHeader("price") int price) {


        if (price <= 0) {
            return new ResponseEntity<>("Цена должна быть больше нуля.", HttpStatus.BAD_REQUEST);
        }

        ConfigurationResponse response = CreateConfigutation.getConfigutation(price);

        if (response.getPrice() == -1) {
            return new ResponseEntity<>("Мы не смогли собрать сборку на такую сумму.", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getProcessors")
    public ResponseEntity<?> processGetProcessors() {
        ArrayList<Processor> processors = DatabaseConnection.getProcessors();


        return ResponseEntity.status(HttpStatus.OK).body(processors);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getMotherboards")
    public ResponseEntity<?> processGetMotherboards() {
        ArrayList<Motherboard> motherboards = DatabaseConnection.getMotherboards();
        return ResponseEntity.status(HttpStatus.OK).body(motherboards);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getVideocards")
    public ResponseEntity<?> processGetVideocards() {
        ArrayList<Videocard> videocards = DatabaseConnection.getVideocards();
        return ResponseEntity.status(HttpStatus.OK).body(videocards);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getPowerunits")
    public ResponseEntity<?> processGetPowerunits() {
        ArrayList<Powerunit> powerunits = DatabaseConnection.getPowerunits();
        return ResponseEntity.status(HttpStatus.OK).body(powerunits);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addComponent")
    public ResponseEntity<String> processSendInfo(@RequestBody AddRequest addRequest) {
        if (addRequest.getRang() < 1 || addRequest.getRang() > 3) {
            return new ResponseEntity<>("недопустимый ранг устройства",HttpStatus.BAD_REQUEST);
        }
        if (addRequest.getPrice() < 1) {
            return new ResponseEntity<>("недопустимая цена",HttpStatus.BAD_REQUEST);
        }
        DatabaseConnection.uploadToDataBase(addRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private static class ErrorResponse {
        private final int code;
        private final String message;

        public ErrorResponse(String message, int code) {

            this.message = message;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }


}
