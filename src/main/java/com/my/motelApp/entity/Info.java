package com.my.motelApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "info")
public class Info {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@JsonIgnore
	private Long id;
	
	@Column(name = "giaphong")
	private String giaphong;
	
	@Column(name = "dien")
	private String dien;
	
	@Column(name = "nuoc")
	private String nuoc;
	
	@Column(name = "xe")
	private String xe;
	
	@Column(name = "wifi")
	private String wifi;
	
	@Column(name = "maylanh")
	private String maylanh;
	
	@Column(name = "rac")
	private String rac;
	
	@OneToMany(mappedBy = "info",cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonProperty(value = "description")
	private List<Description> descriptions = new ArrayList<>();

	@OneToOne(mappedBy = "info")
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




	public String getGiaphong() {
		return giaphong;
	}

	public void setGiaphong(String giaphong) {
		this.giaphong = giaphong;
	}

	public String getDien() {
		return dien;
	}

	public void setDien(String dien) {
		this.dien = dien;
	}

	public String getNuoc() {
		return nuoc;
	}

	public void setNuoc(String nuoc) {
		this.nuoc = nuoc;
	}

	public String getXe() {
		return xe;
	}

	public void setXe(String xe) {
		this.xe = xe;
	}

	public String getWifi() {
		return wifi;
	}

	public void setWifi(String wifi) {
		this.wifi = wifi;
	}

	public String getMaylanh() {
		return maylanh;
	}

	public void setMaylanh(String maylanh) {
		this.maylanh = maylanh;
	}

	public String getRac() {
		return rac;
	}

	public void setRac(String rac) {
		this.rac = rac;
	}

	public List<Description> getDescriptions() {
		return descriptions;
	}

	
	public void setDescription(List<Description> descriptions) {
		for (Description description : descriptions) {
			description.setInfo(this);
		}
		this.descriptions = descriptions;
	}

	public void addDescriptions(Description des) {
		des.setInfo(this);
		this.descriptions.add(des);
	}
	
	public void removeDescriptions(Description description) {
		this.descriptions.remove(description);
		description.setInfo(null);
	}
	public void convert(Info info) {
		this.giaphong = info.getGiaphong();
		this.dien = info.getDien();
		this.nuoc = info.getNuoc();
		this.xe = info.getXe();
		this.wifi = info.getWifi();
		this.maylanh = info.getMaylanh();
		this.rac = info.getRac();
		this.desConvert(info.getDescriptions());
	}
	public void desConvert(List<Description> des) {
		int r = this.getDescriptions().size() - des.size();
		if(r > 0)
		{
			while(r > 0)
			{
				Description temp = this.getDescriptions().get(0);
				this.descriptions.remove(temp);
				temp.setInfo(null);
				r = r -1;
			}
		}
		if(r < 0)
		{
			r = -r;
			while(r > 0)
			{
				Description temp = new Description();
				this.descriptions.add(temp);
				temp.setInfo(this);
				r = r -1;
			}
		}

		for(int i=0;i<des.size();i++) {
			this.descriptions.get(i).convert(des.get(i));
		}
	}
	public Info(String giaphong, String dien, String nuoc, String xe, String wifi, String maylanh, String rac) {
		super();
		this.giaphong = giaphong;
		this.dien = dien;
		this.nuoc = nuoc;
		this.xe = xe;
		this.wifi = wifi;
		this.maylanh = maylanh;
		this.rac = rac;
	}
	
	
	public Info() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Info [giaphong=" + giaphong + ", dien=" + dien + ", nuoc=" + nuoc + ", xe=" + xe + ", wifi=" + wifi
				+ ", maylanh=" + maylanh + ", rac=" + rac + ", description=" + descriptions + "]";
	}
	
	

}
