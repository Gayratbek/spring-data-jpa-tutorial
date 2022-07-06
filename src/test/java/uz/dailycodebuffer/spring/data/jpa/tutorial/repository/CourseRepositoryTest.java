package uz.dailycodebuffer.spring.data.jpa.tutorial.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import uz.dailycodebuffer.spring.data.jpa.tutorial.entity.Course;
import uz.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;
import uz.dailycodebuffer.spring.data.jpa.tutorial.entity.Teacher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println("courrses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
       Teacher teacher = Teacher.builder().firstName("Gayrat").lastName("Komilov").build();
       Course course = Course.builder().title("Python").credit(6).teacher(teacher).build();
       courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);
        Pageable secondPageWithTwooRecords =
                PageRequest.of(1, 2);
        List<Course> courses = courseRepository.findAll(secondPageWithTwooRecords).getContent();

        long totalElements = courseRepository.findAll(secondPageWithTwooRecords).getTotalElements();

        long totalPages = courseRepository.findAll(secondPageWithTwooRecords).getTotalPages();

        System.out.println("totalPaged : " + totalPages);
        System.out.println("totalelements : " + totalElements);
        System.out.println("courses : " + courses);

    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(0, 5, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());

        Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));

//        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();

        List<Course> courses = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();
        System.out.println("courses : " + courses);
    }

    @Test
    public void saveCourseWithStudenAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("web teacher")
                .lastName("Lutfidinov")
                .build();

        Student student = Student.builder()
                .firstName("Saidakbar")
                .lastname("Lutfiddinov")
                .emailId("saidakbsaar.lutfiddionov@gmail.com")
                .build();

        Course course = Course.builder()
                .title("IT")
                .credit(6)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);


    }


}
