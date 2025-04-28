package me.dio.domain.repository;

import me.dio.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByAccountNumber(String accountNumber);
    // o spring já entende colocando o existsByAccountNumber que ele tem que fazer um join
    // pra verificar se a conta já existe.
}
