package dev.shekhar.BookMyShow.controller;

import dev.shekhar.BookMyShow.dto.CityRequestDTO;
import dev.shekhar.BookMyShow.model.City;
import dev.shekhar.BookMyShow.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/city/{name}")
    public ResponseEntity getCityByName(@PathVariable("name") String cityName) {
        City savedCity = cityService.getCityByName(cityName);
        return ResponseEntity.ok(savedCity);
    }

    @GetMapping("/city")
    public ResponseEntity getAllCities(){
        List<City> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);  // we would return cityResponseDTO
    }


    @PostMapping("/city")
    public ResponseEntity createCity(@RequestBody CityRequestDTO cityRequestDTO) {
        try {
            String cityName = cityRequestDTO.getName();
            if(cityName == null || cityName.isEmpty() || cityName.isBlank()) {
                throw new Exception("City name is invalid");
            }
            City savedCity = cityService.saveCity(cityName);
            return ResponseEntity.ok(savedCity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity deleteCity(@PathVariable("id") int cityId) {
        boolean isCityDeleted = cityService.deleteCity(cityId);
        return ResponseEntity.ok(isCityDeleted);
    }
}
