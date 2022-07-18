package com.my.motelApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "image")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@JsonIgnore
	private Long id;
	
	@Column(name = "link")
	private String link;
	
	@ManyToOne
	@JoinColumn(name = "home_id")
	@JsonIgnore
	private Home home;
	
	public long getId() {
		return id;
	}

	
	public Home getHome() {
		return home;
	}


	public void setHome(Home home) {
		this.home = home;
	}


	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void convert(Image image) {
		this.link = image.getLink();
	}
	
	public Image(String link) {
		super();
		this.link = link;
	}
	
}
