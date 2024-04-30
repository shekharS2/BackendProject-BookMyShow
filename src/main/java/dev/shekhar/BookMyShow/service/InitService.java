package dev.shekhar.BookMyShow.service;

import dev.shekhar.BookMyShow.model.*;
import dev.shekhar.BookMyShow.model.constant.AuditoriumFeature;
import dev.shekhar.BookMyShow.model.constant.SeatStatus;
import dev.shekhar.BookMyShow.model.constant.SeatType;
import dev.shekhar.BookMyShow.model.constant.ShowSeatStatus;
import dev.shekhar.BookMyShow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private AuditoriumRepository auditoriumRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;

//    public boolean initialize() {
//        City city1 = new City("Delhi");
//        City city2 = new City("Imphal");
//        City city3 = new City("Chenai");
//        City city4 = new City("Kochi");
//
//        cityRepository.save(city1);
//        cityRepository.save(city2);
//        cityRepository.save(city3);
//        cityRepository.save(city4);
//
//        City savedCity1 = cityRepository.findCityByName("Delhi");
//        City savedCity2 = cityRepository.findCityByName("Imphal");
//        City savedCity3 = cityRepository.findCityByName("Chenai");
//        City savedCity4 = cityRepository.findCityByName("Kochi");
//
//        Theatre theatre1 = new Theatre("AshishMultiplex", "CP, Delhi");
//        Theatre theatre2 = new Theatre("NitinIMAX", "Select City , Delhi");
//
//        theatreRepository.save(theatre1);
//        theatreRepository.save(theatre2);
//
//        Theatre savedTheatre1 = theatreRepository.findTheatreByName("AshishMultiplex");
//        Theatre savedTheatre2 = theatreRepository.findTheatreByName("NitinIMAX");
//
//        List<Theatre> theatreList1 = new ArrayList<>();
//        theatreList1.add(savedTheatre1);
//        theatreList1.add(savedTheatre2);
//        savedCity1.setTheatres(theatreList1);
//        cityRepository.save(savedCity1);
//
//        for(int i = 1; i <= 5; i++) {
//            Seat s = new Seat(i, i, "A" + i, SeatType.GOLD, SeatStatus.AVAILABLE);
//            seatRespository.save(s);
//        }
//
//        List<Seat> savedSeats = seatRespository.findAll();
//
//        Auditorium auditorium = new Auditorium();
//        auditorium.setName("Audi01");
//        auditorium.setCapacity(5);
//        auditorium.setAuditoriumFeatures(List.of(AuditoriumFeature.DOLBY, AuditoriumFeature.IMAX));
//        auditorium.setSeats(savedSeats);
//        auditoriumRespository.save(auditorium);
//
//        Auditorium savedAuditorium = auditoriumRespository.findAuditoriumByName("Audi01");
//        List<Auditorium> auditoriums = new ArrayList<>();
//        auditoriums.add(savedAuditorium);
//
//        Theatre savedTheatreNew1 = theatreRepository.findTheatreByName("AshishMultiplex");
//        savedTheatreNew1.setAuditoriums(auditoriums);
//        theatreRepository.save(savedTheatreNew1);
//
//        // ISSUE
////        Theatre savedTheatreNew2 = theatreRepository.findTheatreByName("NitinIMAX");
////        savedTheatreNew2.setAuditoriums(auditoriums);
////        theatreRepository.save(savedTheatreNew2);
//
//        return true;
//    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean initialize(){
        City delhi = new City("Delhi");
        City bangalore = new City("Bangalore");
        City canberra = new City("Canberra");

        cityRepository.save(delhi);
        cityRepository.save(bangalore);
        cityRepository.save(canberra);

        City savedDelhi = cityRepository.findCityByName("Delhi");
        Theatre ashishTheatre = new Theatre("AshishMultiplex", "CP, Delhi");
        Theatre nithinTheatre = new Theatre("NithinIMAX", "Select City, Delhi");

        theatreRepository.save(ashishTheatre);
        theatreRepository.save(nithinTheatre);

        Theatre savedAshishTheatre = theatreRepository.findTheatreByName("AshishMultiplex");
        Theatre savedNithinTheatre = theatreRepository.findTheatreByName("NithinIMAX");

        List<Theatre> delhiTheatres = new ArrayList<>();
        delhiTheatres.add(savedAshishTheatre);
        delhiTheatres.add(savedNithinTheatre);
        savedDelhi.setTheatres(delhiTheatres);
        cityRepository.save(savedDelhi);

        for(int i=1;i<=5;i++){
            Seat s = new Seat(i, i, i+ " "+i, SeatType.GOLD, SeatStatus.AVAILABLE);
            seatRepository.save(s);
        }

        List<Seat> savedSeats = seatRepository.findAll(); // returns all the data of the table, "select * from table"

        Auditorium auditorium = new Auditorium();
        auditorium.setName("Audi01");
        auditorium.setCapacity(5);
        auditorium.setAuditoriumFeatures(List.of(AuditoriumFeature.DOLBY, AuditoriumFeature.IMAX));
        auditorium.setSeats(savedSeats);
        auditoriumRepository.save(auditorium);

        Auditorium savedAudi = auditoriumRepository.findAuditoriumByName("Audi01");
        Theatre savedTheatre = theatreRepository.findTheatreByName("AshishMultiplex");
        List<Auditorium> auditoriums = new ArrayList<>();
        auditoriums.add(savedAudi);
        savedTheatre.setAuditoriums(auditoriums);
        theatreRepository.save(savedTheatre);

        Movie movie = new Movie("Titanic", "best movie ever");
        movieRepository.save(movie);

        Show show = new Show();
        show.setStartTime(LocalDateTime.now());
        show.setEndTime(LocalDateTime.now().plusMinutes(180));
        show.setMovie(movieRepository.findMovieByName("Titanic"));
        show.setAuditorium(auditoriumRepository.findAuditoriumByName("Audi01"));

        showRepository.save(show);

        for(int i=1;i<=5;i++){
            ShowSeat s = new ShowSeat(1251, showRepository.findById(1).get(), seatRepository.findSeatBySeatNumber(i+" "+i), ShowSeatStatus.AVAILABLE);
            showSeatRepository.save(s);
        }




        return true;
    }
}
