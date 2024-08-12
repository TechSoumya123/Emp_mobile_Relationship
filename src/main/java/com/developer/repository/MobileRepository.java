package com.developer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.model.Mobile;

public interface MobileRepository extends JpaRepository<Mobile, Long> {

}
