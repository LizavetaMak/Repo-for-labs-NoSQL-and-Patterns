package com.musicalinstrument.demo.repository;


import com.musicalinstrument.demo.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface  UserRepository extends JpaRepository<User, Long>  {


    @Query(value = "SELECT * FROM User WHERE name = :name", nativeQuery = true)
    List<User> findAllByName(@Param("name") String name);
    @Transactional
    void deleteById(Long id_user);
    List<User> findAll();
    User save(User user);

}
