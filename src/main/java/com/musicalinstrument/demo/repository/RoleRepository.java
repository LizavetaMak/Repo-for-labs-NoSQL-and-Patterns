package com.musicalinstrument.demo.repository;


import com.musicalinstrument.demo.jpa.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>  {


}
