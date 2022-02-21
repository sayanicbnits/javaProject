package com.cbnits.CBNITS_TRADE.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

//import com.cbnits.CBNITS_TRADE.UsersPackage.UserRepo;
import com.cbnits.CBNITS_TRADE.UsersPackage.Users;

public interface UserRepository extends JpaRepository<Users, UUID>{

}
