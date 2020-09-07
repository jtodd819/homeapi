package com.api.homeapi.repository;
import java.util.List;
import com.api.homeapi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findAll();
}
