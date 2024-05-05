package com.xyz.common.jpa.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * The persistent class for the language database table.
 * 
 */
@Entity
@Table(name="language")
@NamedQuery(name="LanguageVO.findAll", query="SELECT l FROM LanguageVO l")
public class LanguageVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="language_code")
	private String languageCode;

	private String language;
	@OneToMany(mappedBy = "languageCode",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<MovieVO>movies=new ArrayList<>();
	public LanguageVO() {
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<MovieVO> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieVO> movies) {
		this.movies = movies;
	}
	
}