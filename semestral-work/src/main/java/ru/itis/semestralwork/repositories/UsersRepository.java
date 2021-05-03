package ru.itis.semestralwork.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.semestralwork.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("select account from User account where " +
            "(:q = 'empty' or UPPER(account.username) like UPPER(concat('%', :q, '%' )))")
    Page<User> search(@Param("q") String q, Pageable pageable);
}