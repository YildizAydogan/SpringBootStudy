package com.TechPro.SpringBootStudy.ControllerServiceRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean04controller {
    private StudentBean04Service stdSrvc;//service layer' ulaşmak için obj create edildi
    public StudentBean04controller(StudentBean04Service stdSrvc){
        this.stdSrvc = stdSrvc;
    }

    //bu method id ile ogrc returnn eden service methodu call edecek
    @GetMapping(path = "/selectStudentById/{id}")
    public StudentBean04 selectStudentById(@PathVariable Long id){

        return stdSrvc.selectStudentById(id);
    }
}