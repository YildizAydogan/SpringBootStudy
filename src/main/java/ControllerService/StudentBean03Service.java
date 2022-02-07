package ControllerService;



import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentBean03Service {
    List<StudentBean03> listofStudent=List.of(
           new StudentBean03(101L,"Zekeriya Canbal","ac@gmail.com", LocalDate.of(1980,12,5)),
           new StudentBean03(102L,"Yildiz Aydogan","ac@gmail.com", LocalDate.of(1980,12,5)),
           new StudentBean03(103L,"Mehmet Akgul","ac@gmail.com", LocalDate.of(1980,12,5)),
           new StudentBean03(104L,"Hatice Sengör","ac@gmail.com", LocalDate.of(1980,12,5)));




//id ile obje datasi veren method

   public StudentBean03 getstudentsbyid(Long id) {
    if(listofStudent.stream().filter(t->t.getId()==id).collect(Collectors.toList()).isEmpty()){

       return new StudentBean03();  //bu parametresiz contrucktoru return et
    }
    return listofStudent.stream().filter(t->t.getId()==id).findFirst().get();
   }



















}