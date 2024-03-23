package com.personal.myboard.repository;

import com.personal.myboard.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CateRepository extends JpaRepository<Categories, Integer> {

}
