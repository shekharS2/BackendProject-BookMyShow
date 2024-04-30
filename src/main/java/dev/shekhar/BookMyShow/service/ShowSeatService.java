package dev.shekhar.BookMyShow.service;

import dev.shekhar.BookMyShow.model.ShowSeat;
import dev.shekhar.BookMyShow.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowSeatService {
    @Autowired
    private ShowSeatRepository showSeatRepository;

    public ShowSeat getShowSeat(int showSeatId) {
        return showSeatRepository.findById(showSeatId).get();
    }

    public ShowSeat saveShowSeat(ShowSeat showSeat) {
        return showSeatRepository.save(showSeat);
    }
}
