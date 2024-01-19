package com.example.demo.model;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Optional;

@Service
@Log4j2
@Transactional
public class MicroServiceService  {

    @Autowired
    MicroServiceRepository microServiceRepository;


    public ZonedDateTime getNow() {
        return ZonedDateTime.now();
    }

    public String getUp() {
        createMicroService();
        Optional<MicroService> microService = microServiceRepository.findTopByOrderByIdDesc();

        if (microService.isPresent()) {
            log.info("ok");
            return microService.get().getSysState();
        }
        log.warn("NotOK");
        return "empty";
    }

    @PostConstruct
    public synchronized MicroService createMicroService() {
        log.info("createMicroService...........");
        MicroService microService = new MicroService();
        microService.setSysState("OK");
        microService.setCreatedOn(getNow());
        microService.setUpdatedOn(getNow());
        microService.setIsDeleted(false);
        microService.setVersion(0L);
        microService = microServiceRepository.save(microService);
        log.info("System is {}", microService.toString());
        return microService;

    }

    @Scheduled(initialDelay = 5 * 1000, fixedRate = 100 * 1000)
    public synchronized void doCheckOnline() {
        Optional<MicroService> microService = microServiceRepository.findTopByOrderByIdDesc();
        if (microService.isPresent()) {
            MicroService myRecord = microService.get();
            log.info("myRecord is {}", myRecord.getId());
            microServiceRepository.deleteByExcludedId(Arrays.asList(myRecord.getId()));
            log.info("..........delete All except one ....");
            createMicroService();
        }
    }

    public MicroService getMicroService() {

        Optional<MicroService> microService = microServiceRepository.findTopByOrderByIdDesc();

        if (microService.isPresent()) {
            log.info("getMicroService");
            return microService.get();
        }

        return null;
    }
}
