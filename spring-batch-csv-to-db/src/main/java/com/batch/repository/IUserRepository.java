package com.batch.repository;

import org.springframework.data.repository.CrudRepository;

import com.batch.model.User;

public interface IUserRepository extends CrudRepository<User, Long> {

}
