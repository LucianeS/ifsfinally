package br.edu.ifrs.canoas.lds.ifsfinally.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Base64Utils;


@Entity
public class Doacao {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String title;
	
	@Column(columnDefinition = "TEXT")
	@Size(min = 1, max = 500)
	private String description;
	
	@Column(columnDefinition = "boolean default true")
	private boolean disponivel;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User responsible;
	
	@Email
	private String contactEmail;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date postedOn;
	
	private String location;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean isDisponivel) {
		this.disponivel = isDisponivel;
	}

	public User getResponsible() {
		return responsible;
	}

	public void setResponsible(User responsible) {
		this.responsible = responsible;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
