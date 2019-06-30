package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.SubjectDAO;
import com.example.demo.exception.user.SubjectNotFoundException;
import com.example.demo.model.Subject;

@Service
public class SubjectService {

   private final SubjectDAO subjectDAO;

   SubjectService(SubjectDAO subjectDAO) {
       this.subjectDAO = subjectDAO;
   }

   public Subject create(Subject subject) {
       return subjectDAO.save(subject);
   }

   public Subject update(Subject subjectToUpdate) throws SubjectNotFoundException {


       Subject subject = subjectDAO.findById(subjectToUpdate.getId());
       if (subject == null)
           throw new  SubjectNotFoundException("Could not update. The subjrect does not exist.");

       return subjectDAO.save(subjectToUpdate);
   }

   public void delete(long id) {
       subjectDAO.deleteById(id);
   }

   public Subject findById(long id) {
       return subjectDAO.findById(id);
   }
   
   public Subject findByName(String name) {
	   return subjectDAO.findByName(name);
   }
   
   public List<Subject> findBySubstring(String substring) {
	   return subjectDAO.findBySubstring(substring);
   }
}