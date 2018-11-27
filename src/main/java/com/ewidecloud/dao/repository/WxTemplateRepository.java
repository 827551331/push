package com.ewidecloud.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ewidecloud.entity.WxTemplate;

@Repository
public interface WxTemplateRepository extends JpaRepository<WxTemplate, Integer>{

}
