package com.personal.myboard.service;

import com.personal.myboard.entity.Categories;
import com.personal.myboard.entity.Member;
import com.personal.myboard.entity.MyBoard;
import com.personal.myboard.repository.CateRepository;
import com.personal.myboard.repository.MBRepository;
import com.personal.myboard.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MBService {
    @Autowired
    private MBRepository mbRepository;
    @Autowired
    private CateRepository cateRepository;
    private MemberRepository memberRepository = new MemberRepository();

    public void write(MyBoard myBoard){
        mbRepository.save(myBoard);
    }
    public void write(Categories categories){cateRepository.save(categories);}
    public List<Categories> categoriesList(){return cateRepository.findAll();}
    public List<MyBoard> myboardList(){
        return mbRepository.findAll();
    }
    public MyBoard boardView(int id){
        return mbRepository.findById(id).get();
    }
    public Categories categoriesView(int id){
        return cateRepository.findById(id).get();
    }
    public void boardDelete(int id){
        mbRepository.deleteById(id);
    }
    public void categoryDelete(int id){
        cateRepository.deleteById(id);
    }
    public Member login(){return memberRepository.init();}
}
