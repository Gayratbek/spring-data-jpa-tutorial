package uz.dailycodebuffer.spring.data.jpa.tutorial.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import uz.dailycodebuffer.spring.data.jpa.tutorial.entity.Guardian;
import uz.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder().emailId("gayrat.komilov@gmail.com")
                .firstName("Gayrat")
                .lastname("Komilov").build();
//                .guardianName("Nikhil")
//                .guardianEmail("guy8020@mail.ru")
//                .guardianMobile("998905732311").build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Nikhil")
                .email("guy8020@mail.ru")
                .mobile("998905732311").build();

        Student student = Student.builder()
                .emailId("test@gmail.com")
                .firstName("Test")
                .lastname("Komilov")
                .guardian(guardian).build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList= " + studentList);

    }

    @Test
    public void printStudentByFirstNamre(){
        List<Student> students = studentRepository.findByFirstName("Test");
        System.out.println("students: " + students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("t");
        System.out.println("students: " + students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Nikhil");
        System.out.println("students:" + students);
    }
    @Test
    public void printgetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("test@gmail.com");
        System.out.println("student :" + student);
    }
    @Test
    public void printgetStudentFirstNameByEmailAddress(){
        String student = studentRepository.getStudentFirstNameByEmailAddress("test@gmail.com");
        System.out.println("student first name = " + student);
    }

    @Test
    public void printgetStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("test@gmail.com");
        System.out.println("student first name = " + student);
    }
    @Test
    public  void updateStudentNameByEmailId(){
        studentRepository.updateStudentNameByEmailId("Gayrat test", "test@gmail.com");
    }



}