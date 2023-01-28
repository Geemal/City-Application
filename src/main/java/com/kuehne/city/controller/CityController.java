package com.kuehne.city.controller;

import com.kuehne.city.entity.City;
import com.kuehne.city.exception.CustomErrorException;
import com.kuehne.city.response.ResponseHandler;
import com.kuehne.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class CityController {

    private final CityService cityService;


    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("/cities")
    public ResponseEntity<Object> getPageableCities(@RequestParam Integer pageNo,
                                                    @RequestParam Integer pageSize) throws CustomErrorException {
        Page<City> cityPages =  this.cityService.getPageableCities(pageNo, pageSize);

        if (cityPages.hasContent()) {
            return new ResponseHandler().generateResponse("Paginated", HttpStatus.OK, cityPages.getContent());
        }
        throw new CustomErrorException("Content Not Found", HttpStatus.NOT_FOUND, new Date());
    }

    @GetMapping("/city/{name}")
    public ResponseEntity<Object> getCityByName(@PathVariable("name") String name) throws CustomErrorException {


        City result = this.cityService.findByCityName(name);
        if (result.getName() != null && !result.getName().equals("")) {
            return new ResponseHandler().generateResponse("Object Found", HttpStatus.FOUND, result);
        }
        throw new CustomErrorException("Object Not Found", HttpStatus.NOT_FOUND, new Date());

    }


    @PreAuthorize("hasAuthority('ROLE_ALLOW_EDIT')")
    @PutMapping("/city")
    public ResponseEntity<Object> updateCityByNameAndImage(@RequestBody City city) throws CustomErrorException {

        if (city.getLink() != null) {
            City resultCity = this.cityService.updateCityByNameAndImage(city);
            if (resultCity != null) {
                return new ResponseHandler().generateResponse("Image Updated", HttpStatus.OK, resultCity);
            }
            throw new CustomErrorException("City Not Found", HttpStatus.NOT_FOUND, new Date());
        }
        throw new CustomErrorException("Passed parameter is empty", HttpStatus.BAD_GATEWAY, new Date());
    }

}
