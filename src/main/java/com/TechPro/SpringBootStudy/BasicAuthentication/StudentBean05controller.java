package com.TechPro.SpringBootStudy.BasicAuthentication;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentBean05controller {
    private StudentBean05Service stdSrvc;//service layer' ulaşmak için obj create edildi
    public StudentBean05controller(StudentBean05Service stdSrvc){
        this.stdSrvc = stdSrvc;
    }

    //bu method id ile ogrc returnn eden service methodu call edecek
    @GetMapping(path = "/selectStudentById/{id}")
    public StudentBean05 selectStudentById(@PathVariable Long id){

        return stdSrvc.selectStudentById(id);
    }

    @GetMapping(path = "/selectAllStudents")
    public List<StudentBean05> selectallStudents(){
       return stdSrvc.selectAllStudent();
    }


    @PutMapping(path = "/updateFullyStudentById/{id}")
    public StudentBean05 updateFullyStudentById(@PathVariable Long id,@RequestBody StudentBean05 newStd){


        return stdSrvc.updateFullyStudentById(id,newStd);
    }



}