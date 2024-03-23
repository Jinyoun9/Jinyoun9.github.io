package com.personal.myboard.repository;

import com.personal.myboard.entity.Member;
import com.personal.myboard.entity.MyBoard;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemberRepository {

    private static Map<Integer, Member> store = new ConcurrentHashMap<>();

    private String username, password;
    private static final String url = "jdbc:mariadb://localhost:3306/myboard";

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {

        return this.findAll().stream()
                .filter(m -> m.getUsername().equals(loginId))
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

    @PostConstruct
    public Member init() {
        Member member = new Member();
        member.setUsername("test");
        member.setPassword("test!");
        return member;
    }
}