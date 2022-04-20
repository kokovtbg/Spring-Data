package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Player findByEmail(String email);

    List<Player> findAllByBirthDateBetween(LocalDate before, LocalDate after);
}
