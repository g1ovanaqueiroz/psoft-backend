package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentDAO;
import com.example.demo.exception.user.StudentNotFoundException;
import com.example.demo.model.Student;

/**
 * 
 * Offers some services to the controller
 * @author Giovana Brito Oliveira
 *
 */
@Service
public class StudentService {

   private final StudentDAO studentDAO;

   StudentService(StudentDAO studentDAO) {
       this.studentDAO = studentDAO;
   }

   // it creates an user
   public Student create(Student student) {
       return studentDAO.save(student);
   }

   // it updates an user
   public Student update(Student studentToUpdate) throws StudentNotFoundException {


       Student user = studentDAO.findByEmail(studentToUpdate.getEmail());
       if (user == null)
           throw new StudentNotFoundException("Could not update. The product does not exist.");

       return studentDAO.save(studentToUpdate);
   }

   // to delete an user
   public void delete(String email) {
       studentDAO.deleteById(email);
   }

   // it finds an user through his email
   public Student findByEmail(String email) {
       return studentDAO.findByEmail(email);
   }
   
   // it finds an user through his email and his password
   public Student findByLogin(String email, String password) {
       return studentDAO.findByLogin(email, password);
   }
}