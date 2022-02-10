package com.TechPro.SpringBootStudy.BasicAuthentication;

import com.TechPro.SpringBootStudy.BasicAuthentication.StudentBean05;
import com.TechPro.SpringBootStudy.BasicAuthentication.StudentBean05Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class StudentBean05Service {
    private StudentBean05Repository studentRepo;//Repository layer'a ulaşmak için data type'nan obj create edildi.
    //obj degerini cons'dan alacak
    @Autowired
    public StudentBean05Service(StudentBean05Repository studentRepo){

        this.studentRepo = studentRepo;
    }
    //Bu method id ile ögrc return edecek
    public StudentBean05 selectStudentById(Long id){
        // return studentRepo.findById(id).get();--> olmayan id hata verir code kırlrı bununiçin kontrol if çalışmalı
        if (studentRepo.findById(id).isPresent()){
            return studentRepo.findById(id).get();
        }
        return new StudentBean05();//isteen id yoksa bos cons run edilecek
    }//service layer'de repository'den alınan datalar methodda çalıştırıldı. bu metthod controlle layer'da call edilmeli



     public List<StudentBean05>selectAllStudent(){


        return studentRepo.findAll();
      }


      //Bu method var olan ogrc tum datalarını (PUT:fully update) update eder
     public StudentBean05 updateFullyStudentById(Long id, StudentBean05 newStudent ){//kullanıcıdan gelen id ve yeni bilgilerle id li studenti güncelliyecez
//name, Email, dateofbirth update edilecek
     StudentBean05 eskici  =studentRepo.findById(id).orElseThrow(()->new IllegalStateException("Girdiginiz id e ait bir ogrenci yok"));

      if(newStudent.getName()==null) { //kullanici yeni bir isim girmezse
          eskici.setName(null); //eski ogren ismini bos birak
      }else if (eskici.getName().equals(newStudent.getName())){
          eskici.setName(newStudent.getName());
      }
         //brd=bussines requarement direktor
         //1) email tekrarli olamz--> EXCEPTION
         //2) email gecerli (@ icermeli) olmali -->EXCEPTION
         //3) email

      Optional<StudentBean05> mail = studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
         if(mail.isPresent()) { //1. sart kontrol-->eger mail varsa containerda bunu kotrol eidyorum
           throw new IllegalStateException("Bu e-Mail daha once kullanilmistir");
         }else if (!newStudent.getEmail().contains("@")){
            throw new IllegalStateException("@ kArakteri kullanmalisiniz");
         }else if(newStudent.getEmail()==null){
             throw new IllegalStateException("Mutlaka bir email girmelisiniz");
         }else if(!newStudent.getEmail().equals(eskici.getEmail())){
            eskici.setEmail(newStudent.getEmail());
         }



         //dob aupdate edilecek
/*
1) girilen dob gelecekten olmamalı hayatın akısına ters oldg için excp
2) girilen dop yanı olmamalı gereksia işelem için excp
 */
         if (Period.between(newStudent.getDob(), LocalDate.now()).isNegative()) {//1. sart kontrol edildi
             throw new IllegalStateException("hatalı dob giridiniz");

         }else if (!newStudent.getDob().equals(eskici.getDob())) {//2. sart kontrol edildi
             eskici.setDob(eskici.getDob());

         }


      return studentRepo.save(eskici);

     }








}