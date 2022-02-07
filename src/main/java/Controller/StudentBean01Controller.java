package Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@Controller    // SpringBoot bu class'i control layer olarak tanimlar
//Controller annotasyonu Dispatcher servisinden mutlak üzere bir View alınması zorunlu ilen RestController buna gereksinim duymaz.
// Herhangi bir Controller annotasyonu ile oluşturulmuş bir Controller'a ise sınıf bazında veya fonksiyon bazında ResponseBody
// annotastonu eklenerek Rest Response sağlanır ve herhangi bir 404 hatası alınmaz.


@RestController
public class StudentBean01Controller {
//class anatosyonu @RestController olmali  @GetMapping icin


    /*
     @RequestMapping(method= RequestMethod.GET, path="/getRequest")
                      //sadece get işlemlerinde  ve bu adresten geldiyese bu metoduu çalıştır
     @ResponseBody  //Methodun return ettigi  datayi gösterir -- Tavsiye edilmez
     public String getMethodeController() {
         return "Get Request Method calisti ... ";
     }

    */




    @GetMapping(path="/getRequest")
//--> Get Request'leri getMethod2() ile mapping yapıp path'deki url  calısır return edilen data gösterilir
    public String getMethode2() {
    return "Bu Method daha basarili calisir ... ";
    }






    //Tight coupling...
    @GetMapping(path = "/getObject")
    public StudentBean01 getObject(){
        return new StudentBean01("islam güzel",33,"ıg202233");
    }




    //Loose coupling...
     @Autowired//StudentBean01 data type'de IOC conteiner'e defoult obj create eder.
            StudentBean01 s;//StudentBean01 s =new StudentBean01();==>tight coupling new StudentBean01()-->@Compenent ,"="-->@Autowired
     @GetMapping(path = "/getObjectLoose")
     public StudentBean01 getObjectLoose() {
        s.setAge(41);
        s.setId("gh202241");
        s.setName("Gokhan");

       return s;
     }


    //Tight coupling...Parametreli url--> url ile girilen parametreyi return edilen datada atanmsı
    @GetMapping(path = "/getObjectParametreli/{school}")
    public StudentBean01 getObjectParametreli(@PathVariable String school) {

        return new StudentBean01("cengiz erdem", 44, String.format("ce1978%s", school));

    }


    //tight coupling..List return
    @GetMapping(path = "/getObjectList")
    public List<StudentBean01> getObjectList() {

        return List.of(new StudentBean01("gökhan bey", 33, "abc"),
                (new StudentBean01("islam bey", 43, "xyz")),
                (new StudentBean01("hatice  hanım", 27, "***")));

    }


    //Loose coupling ...

     @Autowired  //Trick:AUtowired loose coupling deki hersey icin kullanilmali her variable icin
     StudentBean01 t;
     @GetMapping(path = "/getInterfacestudy01")
     public String getInterfaceStudy(){

         return t.study();
     }

    @Autowired
    StudentBean02 k;
    @GetMapping(path = "/getInterfacestudy02")
    public String getInterfaceStudy02(){

        return k.study();
    }



                //bu olmadan hata verir. @Autowired ile tanimlanan objenin create edilecek data type'ni tanimlar.
               //Tanimlanacak data type annotation'a parametre olur
    @Autowired
    @Qualifier(value="studentBean02")
                StudentBean02Interface h;
//qualifier ile bu interfaceden oluşan deklarasyona IOC’deki studentBean01 veya studentBean02
// adındaki bekleyen variable’lardan hangisini atayacağına karar veriyoruz
    @GetMapping(path = "/getInterfacestudy002")
    public String getInterfaceStudy03(){

        return h.study();
    }


















}
