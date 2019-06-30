package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SubjectProfile {
	
	@Id
	private long id;
	
	HashSet<String> likes;
	ArrayList<Comentario> comentarios;
	
	public SubjectProfile() {
		
	}
	
	public SubjectProfile(Subject subject) {
		this.likes = new HashSet<String>();
		this.id = subject.getId();
		this.comentarios = new ArrayList<Comentario>();
	}

	public long getId() {
		return id;
	}

	public int getLikes() {
		return likes.size();
	}
	
	public HashSet<String> getWhoLiked() {
		return likes;
	}

	public void setLike(String email) {
		this.likes.add(email);
	}

	public ArrayList<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentario(Comentario comentario) {
		this.comentarios.add(comentario);
	}
}
