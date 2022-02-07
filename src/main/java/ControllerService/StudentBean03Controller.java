package ControllerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean03Controller {



    private  StudentBean03Service std;


    @Autowired
    public StudentBean03Controller(StudentBean03Service std){
        this.std = std;
    }
    @GetMapping(path = "/getstudentbyid")
    public StudentBean03 getstudentbyidile() {


        return std.getstudentsbyid(106L);
    }
//aktiv code
    @GetMapping(path = "getStudentById/{id}")
    public StudentBean03 getStudentId(@PathVariable Long id){
        return std.getstudentsbyid(id);
    }






}
