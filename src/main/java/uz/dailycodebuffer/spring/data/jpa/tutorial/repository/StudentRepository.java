package uz.dailycodebuffer.spring.data.jpa.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    public List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String firstname);

    List<Student> findByLastnameNotNull();

    List<Student> findByGuardianName(String guardianName);

    //JPQL
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    @Query(value = "Select * from public.tbl_student s where s.email_address = ?1",
           nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);


    @Modifying
    @Transactional
    @Query(value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true)
    int updateStudentNameByEmailId(String firstName, String emailId);




}
