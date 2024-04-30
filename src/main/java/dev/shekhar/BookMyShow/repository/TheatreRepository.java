package dev.shekhar.BookMyShow.repository;

import dev.shekhar.BookMyShow.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
    Theatre findTheatreByName(String theatreName);
}
