package com.example.demo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CommentDAO;
import com.example.demo.dao.StudentDAO;
import com.example.demo.dao.SubjectDAO;
import com.example.demo.exception.user.SubjectNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.model.SubjectDTO;

/**
 * Offers services to Controller
 * 
 * @author Giovana Brito Oliveira
 *
 */
@Service
public class SubjectService {

	@Autowired
	private final CommentDAO commentDAO;

	private final SubjectDAO subjectDAO;

	/**
	 * SubjectService constructor
	 * 
	 * @param subjectDAO SubjectDAO
	 */
	SubjectService(SubjectDAO subjectDAO, CommentDAO commentDAO) {
		this.subjectDAO = subjectDAO;
		this.commentDAO = commentDAO;
	}

	/**
	 * Creates a new Subject
	 * 
	 * @param subject
	 * @return new Subject
	 */
	public Subject create(Subject subject) {
		return subjectDAO.save(subject);
	}

	/**
	 * Updates a subject
	 * 
	 * @param subjectToUpdate
	 * @return updated subject
	 * @throws SubjectNotFoundException
	 */
	public Subject update(Subject subjectToUpdate) throws SubjectNotFoundException {

		Subject subject = subjectDAO.findById(subjectToUpdate.getId());
		if (subject == null)
			throw new SubjectNotFoundException("Could not update. The subjrect does not exist.");

		return subjectDAO.save(subjectToUpdate);
	}

	/**
	 * Deletes a subject through his id number
	 * 
	 * @param id id number
	 */
	public void delete(long id) {
		subjectDAO.deleteById(id);
	}

	/**
	 * Find a subject through his id number
	 * 
	 * @param id id number
	 * @return Subject
	 */
	public Subject findById(long id) {
		return subjectDAO.findById(id);
	}

	/**
	 * Find a subject through his name
	 * 
	 * @param name
	 * @return Subject
	 */
	public Subject findByName(String name) {
		return subjectDAO.findByName(name);
	}

	/**
	 * Find subjects that have this substring in their names
	 * 
	 * @param substring string
	 * @return Subject List
	 */
	public List<Subject> findBySubstring(String substring) {
		return subjectDAO.findBySubstring(substring);
	}

	/**
	 * Return all the Subjects
	 * 
	 * @return Subject List
	 */
	public List<Subject> findAll() {
		return subjectDAO.findAll();
	}

	/**
	 * Add a like in a subject
	 * 
	 * @param id    subject id
	 * @param email user email
	 * @return updated Subject
	 */
	public Subject addLike(long id, String email) {
		Subject subject = subjectDAO.findById(id);
		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found!");
		}
		subject.addLike(email);
		return subjectDAO.save(subject); // updates the subject on database
	}

	/**
	 * Remove a like in a subject
	 * 
	 * @param id    subject id
	 * @param email user email
	 * @return updated subject
	 */
	public Subject removeLike(long id, String email) {
		Subject subject = subjectDAO.findById(id);
		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found!");
		}
		subject.removeLike(email);
		return subjectDAO.save(subject); // updates the subject on database
	}

	/**
	 * Returns how many likes the subject has
	 * 
	 * @param id subject id
	 * @return quantity of likes
	 */
	public int countLikes(long id) {
		Subject subject = subjectDAO.findById(id);
		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found!");
		}
		return subject.countLikes();
	}

	/**
	 * Returns a Boolean indicating whether the user passed as a parameter has or
	 * has not liked the subject
	 * 
	 * @param id    subject id
	 * @param email user id
	 * @return boolean
	 */
	public boolean itLiked(long id, String email) {
		Subject subject = subjectDAO.findById(id);
		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found!");
		}
		return subject.itLiked(email);
	}

	/**
	 * Ranking subjects by likes
	 * 
	 * @return List of subjects
	 */
	public List<SubjectDTO> sortByLikes() {
		List<Subject> list = subjectDAO.findAll();
		Subject[] subjects = this.convertList(list);
		likeQuickSort(subjects, 0, subjects.length - 1);
		List<SubjectDTO> sorted = new ArrayList<SubjectDTO>();
		for (int i = subjects.length - 1; i >= 0; i--) {
			sorted.add(new SubjectDTO(subjects[i].toString(), subjects[i].countLikes()));
		}
		return sorted;
	}

	/**
	 * Ranking subjects by comments
	 * 
	 * @return List of subjects
	 */
	public List<SubjectDTO> sortByComments() {
		List<Subject> list = subjectDAO.findAll();
		Subject[] subjects = this.convertList(list);
		commentQuickSort(subjects, 0, subjects.length - 1);
		List<SubjectDTO> sorted = new ArrayList<SubjectDTO>();
		for (int i = subjects.length - 1; i >= 0; i--) {
			sorted.add(new SubjectDTO(subjects[i].toString(), this.countComment(subjects[i])));
		}
		return sorted;
	}

	/**
	 * Convert a List in Array
	 * 
	 * @param list List to convert
	 * @return array of subjects
	 */
	public Subject[] convertList(List<Subject> list) {
		Subject[] array = new Subject[list.size()];

		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	/**
	 * Sorting algorithm
	 * 
	 * @param arr   array to be sorted
	 * @param begin first index
	 * @param end   last index
	 */
	public void likeQuickSort(Subject arr[], int begin, int end) {
		if (begin < end) {
			int partitionIndex = likePartition(arr, begin, end);

			likeQuickSort(arr, begin, partitionIndex - 1);
			likeQuickSort(arr, partitionIndex + 1, end);
		}
	}

	/**
	 * Sorting algorithm
	 * 
	 * @param arr   array to be sorted
	 * @param begin first index
	 * @param end   last index
	 */
	public void commentQuickSort(Subject arr[], int begin, int end) {
		if (begin < end) {
			int partitionIndex = commentPartition(arr, begin, end);

			commentQuickSort(arr, begin, partitionIndex - 1);
			commentQuickSort(arr, partitionIndex + 1, end);
		}
	}

	/**
	 * Quicksort Partition Method
	 * 
	 * @param arr   array to be sorted
	 * @param begin first index
	 * @param end   last index
	 * @return
	 */
	private int commentPartition(Subject[] arr, int begin, int end) {
		Subject pivot = arr[end];
		int i = (begin - 1);

		for (int j = begin; j < end; j++) {
			if (this.countComment(arr[j]) <= this.countComment(pivot)) {
				i++;

				Subject swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
			}
		}

		Subject swapTemp = arr[i + 1];
		arr[i + 1] = arr[end];
		arr[end] = swapTemp;

		return i + 1;
	}

	/**
	 * Quicksort Partition Method
	 * 
	 * @param arr   array to be sorted
	 * @param begin first index
	 * @param end   last index
	 * @return
	 */
	private int likePartition(Subject[] arr, int begin, int end) {
		Subject pivot = arr[end];
		int i = (begin - 1);

		for (int j = begin; j < end; j++) {
			if (arr[j].countLikes() <= pivot.countLikes()) {
				i++;

				Subject swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
			}
		}

		Subject swapTemp = arr[i + 1];
		arr[i + 1] = arr[end];
		arr[end] = swapTemp;

		return i + 1;
	}

	/**
	 * 
	 * Return how many comments a particular comment has
	 * 
	 * @param subject
	 * @return quantity of comments
	 */
	public int countComment(Subject subject) {
		return commentDAO.subjectComments(subject.getId()).size();
	}
}