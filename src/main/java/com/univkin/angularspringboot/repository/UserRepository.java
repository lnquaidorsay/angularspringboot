package com.univkin.angularspringboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.univkin.angularspringboot.entite.UserEntite;

public interface UserRepository extends PagingAndSortingRepository<UserEntite, Long> {
	UserEntite findByEmail(String email);

	UserEntite findByUserId(String userId);

	@Query("SELECT user FROM UserEntity user")
	Page<UserEntite> findAllUsers(Pageable pageableRequest);

	@Query(value = "SELECT * FROM users u WHERE (u.prenom LIKE %:search% OR u.nom LIKE %:search%) AND u.email_verification_status = :status", nativeQuery = true)
	Page<UserEntite> findAllUserByCriteria(Pageable pageableRequest, @Param("search") String search,
			@Param("status") int status);
}
