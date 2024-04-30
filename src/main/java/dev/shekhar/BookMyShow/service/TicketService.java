package dev.shekhar.BookMyShow.service;

import dev.shekhar.BookMyShow.model.ShowSeat;
import dev.shekhar.BookMyShow.model.Ticket;
import dev.shekhar.BookMyShow.model.constant.ShowSeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private ShowSeatService showSeatService;

    public Ticket bookTicket(List<Integer> showSeatIds, int userId) throws Exception {
        checkAndLockSeats(showSeatIds, userId);
        startPayment(showSeatIds);
        return new Ticket();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void checkAndLockSeats(List<Integer> showSeatIds, int userId) throws Exception {
        for(int showSeatId : showSeatIds) {
            ShowSeat showSeat = showSeatService.getShowSeat(showSeatId);
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new Exception("Seat is not available");
            }
        }

        for(int showSeatId : showSeatIds) {
            ShowSeat showSeat = showSeatService.getShowSeat(showSeatId);
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeatService.saveShowSeat(showSeat);
        }
    }

    public boolean startPayment(List<Integer> showSeatIds) {
        return true;
    }

    public String greet() {
        return "HELLO WORLD";
    }
}
