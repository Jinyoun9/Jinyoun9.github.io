package com.personal.myboard.repository;


import com.personal.myboard.entity.MyBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MBRepository extends JpaRepository<MyBoard, Integer> {

}
