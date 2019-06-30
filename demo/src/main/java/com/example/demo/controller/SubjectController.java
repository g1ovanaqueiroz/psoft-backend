package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.user.SubjectNotFoundException;
import com.example.demo.model.Subject;
import com.example.demo.service.SubjectService;

@RestController
@RequestMapping({"/v1/subjects"})
public class SubjectController {

   private SubjectService subjectService;

   SubjectController(SubjectService subjectService) {
       this.subjectService = subjectService;
   }
   
   @GetMapping(value = "/{id}")
   @ResponseBody
   public ResponseEntity<Subject> findById(@PathVariable long id) {
       Subject subject = subjectService.findById(id);

       if (subject == null) {
           throw new SubjectNotFoundException("Subject not found");
       }

       return new ResponseEntity<Subject>(subject, HttpStatus.OK);
   }
   
   @GetMapping(value = "/name/{name}")
   @ResponseBody
   public ResponseEntity<Subject> findByName(@PathVariable String name){
	   Subject subject = subjectService.findByName(name);
	   
	   if (subject == null) {
           throw new SubjectNotFoundException("Subject not found");
       }
	   
	   return new ResponseEntity<Subject>(subject, HttpStatus.OK);
   }
   
   @GetMapping(value = "/substring/{substring}")
   @ResponseBody
   public ResponseEntity<List<Subject>>	findBySubstring(@PathVariable String substring){
	   List<Subject> subjects = subjectService.findBySubstring(substring);
	   
	   if (subjects == null) {
		   throw new SubjectNotFoundException("Subject not found");
	   }
	   
	   return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
   }
 
   @PostMapping(value = "/create")
   @ResponseBody
   public ResponseEntity<Subject> create(@RequestBody Subject subject) {
       Subject newSubject = subjectService.create(subject);

       if (newSubject == null) {
           throw  new InternalError("Something went wrong");
       }
       
       return new ResponseEntity<Subject>(newSubject, HttpStatus.CREATED);
   }
   
   @DeleteMapping(value = "/{id}")
   public ResponseEntity delete(@PathVariable long id) {
       try {
           subjectService.delete(id);
           return new ResponseEntity(HttpStatus.OK);
       } catch (Exception e) {
           throw new InternalError("Something went wrong");
       }
   }
   
   @PutMapping(value = "/")
   public ResponseEntity<Subject> update(@RequestBody Subject subject) {
       try {
           Subject updated = subjectService.update(subject);
           return new ResponseEntity<>(updated, HttpStatus.OK);
       } catch (Exception e) {
           throw  new InternalError("Something went wrong");
       }
   }

}