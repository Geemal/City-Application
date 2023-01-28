package com.kuehne.city.operation;

import com.kuehne.city.entity.City;
import com.kuehne.city.repository.CityRepository;
import com.kuehne.city.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//this file should be executed only one time to persist data to database
//@Service
public class ReadCityFile {

    Logger logger = LoggerFactory.getLogger(ReadCityFile.class);

    private final CityRepository cityRepository;

//    @Autowired
    public ReadCityFile(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

//    @Bean
    public void getExcelData() {


        List<City> list = new ArrayList<>();
        try {

            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/city/cities.csv"));

            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] readLine = line.trim().split(",");
                list.add(new City(readLine[1], readLine[2]));
            }
            list.remove(0);
            this.cityRepository.saveAll(list);
            logger.debug("cities were saved to database");
        } catch (IOException e) {
            logger.error("cities weren't saved to database");
            e.printStackTrace();
        }

    }
}
