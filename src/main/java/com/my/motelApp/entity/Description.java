package com.my.motelApp.entity;

import javax.persistence.CascadeType;
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
@Table(name = "description")
public class Description {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@JsonIgnore
	private long id;
	
	@Column(name = "content")
	private String content;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "info_id", nullable = true)
	@JsonIgnore
	private Info info;
	
	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public Description() {

	}
	
	public Description(String content) {
		super();
		this.content = content;
	}

	@Override
	public String toString() {
		return "Description [content=" + content + "]";
	}
	
	
}
