package com.TechPro.SpringBootStudy;


import org.springframework.stereotype.Component;




@Component  //bilesen tamamlayici : kullanilan classtan obj IOC create eder
public class StudentBean01 {
//  njection yapacagimiz obje uretimi icin yani bu class
//  zayif baglantida kullancagiz diger classlardan cagiracagiz bunlari


    //pojo plan old java obj
    private String name;
    private int age;
    private String id;

    public StudentBean01() {
        System.out.println("Parametresiz constructor run edildi ...");
    }

    public StudentBean01(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentBean01{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }
}