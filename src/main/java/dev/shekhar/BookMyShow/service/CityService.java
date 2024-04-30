package dev.shekhar.BookMyShow.service;

import dev.shekhar.BookMyShow.model.City;
import dev.shekhar.BookMyShow.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public City getCityByName(String cityName) {
        City city = cityRepository.findCityByName(cityName);
        return city;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(int cityId) {
        return cityRepository.findById(cityId).get();
    }

    public City  saveCity(String cityName) {
        City city = new City();
        city.setName(cityName );
        return cityRepository.save(city) ;
    }

    public City  saveCity(City city) {
        return cityRepository.save(city) ;
    }

    public boolean deleteCity(int cityId) {
        cityRepository.deleteById(cityId);
        return true;
    }
}
