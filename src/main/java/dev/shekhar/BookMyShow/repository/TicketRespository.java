package dev.shekhar.BookMyShow.repository;

import dev.shekhar.BookMyShow.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRespository extends JpaRepository<Ticket, Integer> {
}
