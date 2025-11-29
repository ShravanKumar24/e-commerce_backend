package com.ecommerce.repositories;

import com.ecommerce.entites.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    @Query("""
			 select t from Token t inner join User u on t.user.id = u.id
			 where t.user.id = :userId and t.loggedOut = false
			 """)
    List<Token> findAllValidTokenByUser(Long userId);

}

