package com.personal.myboard.controller;

import com.personal.myboard.service.LoginService;
import com.personal.myboard.entity.Categories;
import com.personal.myboard.entity.Member;
import com.personal.myboard.entity.MyBoard;
import com.personal.myboard.service.MBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class MBController {
    private LocalDateTime localDateTime;
    private DateTimeFormatter formatter;
    private String formattedDateTime;
    @Autowired
    private MBService mbService;
    private LoginService login;
    @GetMapping("jinyoun9.github.io/index")
    public String Home(Model model){
        model.addAttribute("categories", mbService.categoriesList());
        model.addAttribute("list", mbService.myboardList());
        return "index";
    }
    @GetMapping("/editcategory")
    public String editCategoryForm(Model model){
        model.addAttribute("categories", mbService.categoriesList());
        return "editcategory";
    }
    @PostMapping("/addcategorypro")
    public String addCategoryPro(Categories categories, Model model){
        mbService.write(categories);
        model.addAttribute("message", "카테고리가 추가되었습니다.");
        model.addAttribute("searchUrl", "/index");
        return "message";
    }
    @GetMapping("/modifycategorypro/{id}")
    public String modifyCategoryPro(@PathVariable("id")int id, Categories categories, Model model){
        Categories ctTemp = mbService.categoriesView(id);
        ctTemp.setCategory(categories.getCategory());
        mbService.write(ctTemp);
        model.addAttribute("message", "카테고리가 수정되었습니다.");
        model.addAttribute("searchUrl", "/index");
        return "message";
    }
    @GetMapping("/deletecategorypro/{id}")
    public String deleteCategoryPro(@PathVariable("id") int id, Model model){
        mbService.categoryDelete(id);
        model.addAttribute("message", "카테고리가 삭제되었습니다.");
        model.addAttribute("searchUrl", "/index");
        return "message";
    }
    @GetMapping("/write")
    public String boardWrite(Model model){
        model.addAttribute("list", mbService.categoriesList());
        return "write";
    }
    @PostMapping("/writepro")
    public String boardWritePro(MyBoard myboard, Model model) throws IOException {
        localDateTime = localDateTime.now();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        formattedDateTime = localDateTime.format(formatter);
        myboard.setDatetime(formattedDateTime);
        mbService.write(myboard);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/index");
        return "message";
    }
    @GetMapping("/modify/{id}")
    public String boardModify(@PathVariable("id") int id, Model model){ // PathVariable은 {}안에 있는 값을 받아온다.
        model.addAttribute("categories", mbService.categoriesList());
        model.addAttribute("myboard", mbService.boardView(id));
        return "modify";
    }
    @PostMapping("/update/{id}")
    public String boardUpdate(@PathVariable("id") int id, MyBoard myBoard){
        MyBoard mbTemp = mbService.boardView(id);
        mbTemp.setTitle(myBoard.getTitle());
        mbTemp.setContent(myBoard.getContent());
        mbTemp.setCategory(myBoard.getCategory());
        localDateTime = localDateTime.now();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        formattedDateTime = localDateTime.format(formatter);
        mbTemp.setDatetime(formattedDateTime);
        mbService.write(mbTemp);
        return "redirect:/index";
    }
    @GetMapping("/delete/{id}")
    public String boardDelete(@PathVariable("id") int id){
        mbService.boardDelete(id);
        return "redirect:/index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/loginpro")
    public String loginPro(Member member, Model model) {
        String inputID = member.getUsername();
        String inputPW = member.getPassword();
        Member compareMember = mbService.login();
        if(compareMember.getUsername().equals(inputID) && compareMember.getPassword().equals(inputPW)){
            model.addAttribute("message", inputID+"님 환영합니다.");
            model.addAttribute("searchUrl", "/index");
        }
        else{
            model.addAttribute("message", "아이디 혹은 비밀번호가 일치하지 않습니다.");;
        }
        return "message";

    }
}
