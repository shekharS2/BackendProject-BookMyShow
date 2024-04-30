package dev.shekhar.BookMyShow.repository;

import dev.shekhar.BookMyShow.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRespository extends JpaRepository<Payment, Integer> {
}
