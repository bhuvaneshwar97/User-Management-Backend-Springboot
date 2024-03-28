package com.userservice.userservice.Repository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.userservice.userservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, String>,PagingAndSortingRepository<User, String> {
	
	User getByUserId(String userId);
	
	@Query("SELECT u FROM User u WHERE u.status = true")
    Page<User> findAllActiveUsers(Pageable pageable);
	
	boolean existsByEmail(String email);

	@Query(value = "SELECT * FROM userdb.users u " +
            "WHERE (u.status = true) " +
            "AND ((:textRegex is null or CONVERT(u.user_id, CHAR) REGEXP :textRegex) " +
            "OR (:textRegex is null or u.first_name REGEXP :textRegex) " +
            "OR (:textRegex is null or u.last_name REGEXP :textRegex)) " +
            "LIMIT 5", nativeQuery = true)
	List<User> findByIdOrNameRegexWithLimit(String textRegex);

	
	@Query(value = "SELECT * FROM userdb.users u WHERE status = false",
           countQuery = "SELECT count(*) FROM userdb.users u WHERE status = false",
           nativeQuery = true)
    Page<User> findAllDeletedUsers(Pageable pageable);
	
	@Transactional
    @Modifying
    @Query("UPDATE User u SET u.status = false WHERE u.id = :id")
    void updateStatusToFalse(@Param("id") String id);
	
	@Modifying
    @Transactional
    @Query("UPDATE User u SET u.status = :status WHERE u.id IN :ids")
    void setStatusTrueByIds(@Param("status") boolean status, @Param("ids") List<String> userIds);

}
