package com.kuehne.city.service;


import com.kuehne.city.entity.City;
import com.kuehne.city.exception.CustomErrorException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface CityService{

    Page<City> getPageableCities(Integer pageNo, Integer pageSize) throws CustomErrorException;
    City updateCityByNameAndImage(City city) throws CustomErrorException;
    City findByCityName(String name) throws CustomErrorException;


}
