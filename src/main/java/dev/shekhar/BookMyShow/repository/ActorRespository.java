package dev.shekhar.BookMyShow.repository;

import dev.shekhar.BookMyShow.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRespository extends JpaRepository<Actor, Integer> {
}
