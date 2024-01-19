package com.example.demo.model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dev")
@Log4j2
public class DevRestEndPoint  {


    @Autowired
    MicroServiceService microServiceService;


    @GetMapping("/up")
    public String getUp(HttpServletRequest request){
       String t =  request.getHeader(HttpHeaders.AUTHORIZATION);

        HttpServletRequest req1 = request;
        log.info("........getUp called .......");

        return microServiceService.getUp();
    }




    @GetMapping(value="/micro")
    public ResponseEntity<MicroService> getMicro(HttpServletRequest request){
        MicroService microService = microServiceService.getMicroService();
        return new ResponseEntity<>(microService, HttpStatus.OK);
    }
}
