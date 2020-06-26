package com.shashirajraja.onlinebookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.shashirajraja.onlinebookstore.entity.Authority;
import com.shashirajraja.onlinebookstore.entity.AuthorityId;

@RepositoryRestResource
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId> {

}
