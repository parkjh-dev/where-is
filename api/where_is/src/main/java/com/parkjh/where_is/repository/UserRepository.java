package com.parkjh.where_is.repository;

import com.parkjh.where_is.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    User findByEmail(String email);
    User findByPhone(String phone);
    List<User> findByBirthday(LocalDate birthday);
    List<User> findByNameContaining(String name);
    //List<User> findByNicknameContaining(String nickname);

    @Query("SELECT u FROM User u WHERE " +
           "(:name IS NULL OR u.name LIKE %:name%) AND " +
           "(:phone IS NULL OR u.phone LIKE %:phone%) AND " +
           "(:nickname IS NULL OR u.nickname LIKE %:nickname%) AND " +
           "(:email IS NULL OR u.email LIKE %:email%) AND " +
           "(:birthday IS NULL OR u.birthday = :birthday)")
    Page<User> findUsersWithSearchCriteria(
            @Param("name") String name, 
            @Param("phone") String phone, 
            @Param("nickname") String nickname, 
            @Param("email") String email, 
            @Param("birthday") LocalDate birthday, 
            Pageable pageable
    );
}