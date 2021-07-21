package com.deals.date.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.deals.date.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, String> {

	public Admin findByEmail(String email);

	public List<Admin> findAll();

	public void delete(Admin myAdm);

}