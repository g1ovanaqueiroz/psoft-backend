package com.example.demo.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.demo.model.Like;
import com.example.demo.model.Subject;
import com.example.demo.model.SubjectDTO;
import com.example.demo.service.SubjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Subject Controller
 * 
 * @author Giovana Brito Oliveira
 *
 */
@Api(value = "Disciplina", description = "Controller de disciplina")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping({ "/v1/subjects" })
public class SubjectController {

	private SubjectService subjectService;

	/**
	 * SubjectController constructor
	 * 
	 * @param subjectService SubjectService
	 */
	SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	/**
	 * Finds a Subject through his ID Number
	 * 
	 * @param id Subject ID
	 * @return Subject
	 */
	@ApiOperation(value = "Encontra uma disciplina através do seu ID, recebe o id como @PathVariable no caminho /api/v1/subjects/{id}")
	@CrossOrigin
	@GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Subject> findById(@PathVariable long id) {
		Subject subject = subjectService.findById(id);

		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found");
		}

		return new ResponseEntity<Subject>(subject, HttpStatus.OK);
	}

	/**
	 * Finds a Subject through his name
	 * 
	 * @param name Subject name
	 * @return Subject
	 */
	@ApiOperation(value = "Encontra uma disciplina através do seu nome, recebe o nome como @PathVariable no caminho /api/v1/subjects/name/{name}")
	@CrossOrigin
	@GetMapping(value = "/name/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Subject> findByName(@PathVariable String name) {
		Subject subject = subjectService.findByName(name);

		if (subject == null) {
			throw new SubjectNotFoundException("Subject not found");
		}

		return new ResponseEntity<Subject>(subject, HttpStatus.OK);
	}

	/**
	 * Finds the Subjects that have this substring in their names
	 * 
	 * @param substring
	 * @return Subject List
	 */
	@ApiOperation(value = "Encontra uma disciplina através de uma substring, recebe a substring como @PathVariable no caminho /api/v1/subjects/substring/{substring}")
	@CrossOrigin
	@GetMapping(value = "/substring/{substring}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Subject>> findBySubstring(@PathVariable String substring) {
		List<Subject> subjects = subjectService.findBySubstring(substring);

		if (subjects == null) {
			throw new SubjectNotFoundException("Subject not found");
		}

		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}

	/**
	 * Creates a new Subject
	 * 
	 * @param subject
	 * @return new Subject
	 */
	@ApiOperation(value = "Cria uma nova disciplina no BD, recebe o objeto disciplina como json no corpo da requisição")
	@CrossOrigin
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Subject> create(@RequestBody Subject subject) {
		Subject newSubject = subjectService.create(subject);

		if (newSubject == null) {
			throw new InternalError("Something went wrong");
		}

		return new ResponseEntity<Subject>(newSubject, HttpStatus.CREATED);
	}

	/**
	 * Deletes a Subject through his id number
	 * 
	 * @param id Subject id number
	 * @return HttpStatus
	 */
	@ApiOperation(value = "Deleta uma disciplina do BD, receba o id da disciplina as ser excluída como @PathVariable no caminho /api/v1/subjects/delete/{id}")
	@CrossOrigin
	@DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity delete(@PathVariable long id) {
		try {
			subjectService.delete(id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * Updates a Subject
	 * 
	 * @param subject Subject to update
	 * @return HttpStatus
	 */
	@ApiOperation(value = "Atualiza uma disciplina, recebe o objeto disciplina atualizado como json no corpo da requisição")
	@CrossOrigin
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject> update(@RequestBody Subject subject) {
		try {
			Subject updated = subjectService.update(subject);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * Return all the Subjects
	 * 
	 * @return Subject List
	 */
	@CrossOrigin
	@ApiOperation(value = "Método Get que retorna uma lista com todas as disciplinas cadastradas no BD")
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Subject>> findAll() {
		List<Subject> list = subjectService.findAll();
		return new ResponseEntity<List<Subject>>(list, HttpStatus.OK);
	}

	/**
	 * This method add a like in a subject
	 * 
	 * @param like like object
	 * @return updated subject
	 */
	@ApiOperation(value = "Método Put que adiciona um like a uma disciplina, recebe o email do usuário que deu like e o id da disciplina num json no corpo da requisição")
	@CrossOrigin
	@PutMapping(value = "/like", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject> addLike(@RequestBody Like like) {
		try {
			return new ResponseEntity<Subject>(subjectService.addLike(like.getId(), like.getEmail()), HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * This method remove a like from a subject
	 * 
	 * @param like like object
	 * @return updated subject
	 */
	@ApiOperation(value = "Método Delete que remove um like de uma disciplina, recebe o email do usuário e o id da disciplina num json no corpo da requisição")
	@CrossOrigin
	@DeleteMapping(value = "/like", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject> removeLike(@RequestBody Like like) {
		try {
			Subject subject = subjectService.removeLike(like.getId(), like.getEmail());
			return new ResponseEntity<Subject>(subject, HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * This method return how many likes the subject has
	 * 
	 * @param id subject id
	 * @return quantity of likes
	 */
	@ApiOperation(value = "Método Get que retorna quantos likes determinada disciplina possui, recebe o id da disciplina como @PathVariable no caminho /api/v1/subjects/like")
	@CrossOrigin
	@GetMapping(value = "/like")
	public ResponseEntity<Integer> countLikes(@PathVariable long id) {
		try {
			return new ResponseEntity<Integer>(subjectService.countLikes(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wrong");
		}
	}

	/**
	 * Ranking subjects by likes
	 * 
	 * @return List of Subjects
	 */
	@ApiOperation(value = "Retorna uma lista com todas as disciplina ordenadas por like, da com mais likes para a com menos likes")
	@GetMapping(value = "/ranking/like", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<List<SubjectDTO>> rankingByLike() {
		List<SubjectDTO> sortedList = subjectService.sortByLikes();
		return new ResponseEntity<List<SubjectDTO>>(sortedList, HttpStatus.OK);
	}

	/**
	 * Ranking subjects by comments
	 * 
	 * @return List of Subjects
	 */
	@ApiOperation(value = "retorna uma lista com todas as disciplina ordenadas por quantidade de comentário, do maior pro menor")
	@GetMapping(value = "/ranking/comment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<List<SubjectDTO>> rankingByComment() {
		List<SubjectDTO> sortedList = subjectService.sortByComments();
		return new ResponseEntity<List<SubjectDTO>>(sortedList, HttpStatus.OK);
	}

	/**
	 * Returns a Boolean indicating whether the user passed as a parameter has or
	 * has not liked the subject
	 * 
	 * @param like object like
	 * @return boolean
	 */
	@ApiOperation(value = "Retorna um booleano indicando se aquele usuário já curtiu aquela disciplina ou não, recebe o email do usuário e o id da disciplina como json no corpo da requisição")
	@CrossOrigin
	@GetMapping(value = "/like/itliked", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> itLiked(@RequestBody Like like) {
		try {
			return new ResponseEntity<Boolean>(subjectService.itLiked(like.getId(), like.getEmail()), HttpStatus.OK);
		} catch (Exception e) {
			throw new InternalError("Something went wwong");
		}
	}

}