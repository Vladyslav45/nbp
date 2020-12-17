package com.apinbp.nbp.scheduler;

import com.apinbp.nbp.model.CrossCourse;
import com.apinbp.nbp.repository.CrossConvertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

@Component
public class CrossCourseScheduler {

    private final Logger logger = LoggerFactory.getLogger(CrossCourseScheduler.class);

    @Autowired
    private CrossConvertRepository crossConvertRepository;

    private DecimalFormat decimalFormats = new DecimalFormat("#.####");

    @Scheduled(cron = "6 * * * * ?")
    public void updateCrossCourse(){
        List<CrossCourse> crossCourses = crossConvertRepository.findAll();
        crossCourses.forEach(crossCourse -> {
            crossCourse.getCurrencies().forEach(currency -> {
                double aDouble = (Math.random() * 0.011) - 0.007;
                currency.setBidCurrency(Double.parseDouble(decimalFormats.format(currency.getBidCurrency() + aDouble)));
            });
            logger.info("Zmiana kursu walut");
            crossConvertRepository.save(crossCourse);
        });
    }
}
