package com.api.homeapi.repository;
import java.util.List;
import com.api.homeapi.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long>{
    List<UserAccount> findAll();
    UserAccount findByUserName(String userName);
}
