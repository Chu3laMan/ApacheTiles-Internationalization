package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4549528885968440185L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bookId;
	@NotNull
	private String bookName;
	@NotNull
	private String author;
	@NotNull
	private String isbn;
	
	
	
}
