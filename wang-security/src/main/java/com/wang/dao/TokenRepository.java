package com.wang.dao;

import com.wang.pojo.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {


    @Query(value = """
        select t from Token t inner join User u\s
        on t.id = u.id
        where u.id = :id and (t.expired = false or t.revoked = false )\s
        """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}
