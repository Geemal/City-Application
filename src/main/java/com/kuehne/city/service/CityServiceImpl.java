package com.kuehne.city.service;


import com.kuehne.city.controller.AuthController;
import com.kuehne.city.entity.City;
import com.kuehne.city.exception.CustomErrorException;
import com.kuehne.city.repository.CityRepository;
import com.kuehne.city.response.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);


    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Page<City> getPageableCities(Integer pageNo, Integer pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<City> pagedResult = this.cityRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult;
        }
        logger.debug("Pageable content is empty");
        return new PageImpl<City>(new ArrayList<>());


    }

    @Override
    public City updateCityByNameAndImage(City city) {
        if (city.getLink() != null && city.getName() != null) {
            Optional<City> resultCity = this.cityRepository.findById((city.getCitySeq()));

            if (resultCity.isPresent()) {
                if (!resultCity.get().getName().equals(city.getName())
                        || !resultCity.get().getLink().equals(city.getLink()))
                    return this.cityRepository.save(city);
            }
            logger.debug("City isn't present -"+ city.getName());
        }
        return new City();
    }


    @Override
    public City findByCityName(String name) {

        City city = this.cityRepository.findByName(name);
        if (city != null) {
            return city;
        }
        logger.debug("City couldn't be found using name -"+ name);
        return new City();
    }
}
