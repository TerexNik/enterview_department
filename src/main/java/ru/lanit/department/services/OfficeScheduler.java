package ru.lanit.department.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OfficeScheduler {

    private static final Logger log = LoggerFactory.getLogger(OfficeScheduler.class);
    private final OfficeServiceImpl service;

    @Autowired
    public OfficeScheduler(OfficeServiceImpl service) {
        this.service = service;
    }

    @Scheduled(cron = "0 0 23 * * *")
    public void updateOffice() {
        log.info("It's 23 o'clock lets update Office value!");
        service.updateValue();
        log.info("Update office value complete");
    }
}
