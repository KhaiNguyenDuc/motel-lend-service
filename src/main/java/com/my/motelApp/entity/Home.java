package com.my.motelApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "home")
public class Home {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "map")
	private String map;
	
	@Column(name = "distance")
	private float distance;
	
	@Column(name = "phone_chu")
	private String phone_chu;
	
	@Column(name = "name_chu")
	private String name_chu;
	
	@Column(name = "state")
	private boolean state;
	
	@OneToMany(mappedBy = "home",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Image> img_phong = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "info_id")
	private Info info;
	
	@ManyToOne
	@JoinColumn(name = "ward_id", nullable = true)
	private Ward ward;

	public Long getId() {
		return id;
	}
	

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getPhone_chu() {
		return phone_chu;
	}


	public void setPhone_chu(String phone_chu) {
		this.phone_chu = phone_chu;
	}


	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public String getName_chu() {
		return name_chu;
	}

	public void setName_chu(String name_chu) {
		this.name_chu = name_chu;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public List<Image> getImg_phong() {
		return img_phong;
	}

	public void setImg_phong(List<Image> img_phong) {
		this.img_phong = img_phong;
		for (Image image : img_phong) {
			image.setHome(this);
		}
	}
	
	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
	
	public void removeInfo(Info info) {
		info.setHome(null);
		this.info = null;
	}
	public Home() {
		super();
	}
	
	public void convert(Home home) {
		this.address = home.getAddress();
		this.distance = home.getDistance();
		imagesConvert(home.getImg_phong());
		this.info.convert(home.getInfo());
		this.map = home.getMap();
		this.name_chu = home.getName_chu();
		this.state = home.isState();
		this.ward = home.getWard();
		this.phone_chu = home.getPhone_chu();		
	}
	public void imagesConvert(List<Image> images) {
		int r = this.getImg_phong().size() - images.size();
		if(r > 0)
		{
			while(r > 0)
			{
				Image temp = this.getImg_phong().get(0);
				this.img_phong.remove(temp);
				temp.setHome(null);
				r = r -1;
			}
		}
		if(r < 0)
		{
			r = -r;
			while(r > 0)
			{
				Image temp = new Image();
				this.img_phong.add(temp);
				temp.setHome(this);
				r = r -1;
			}
		}
		
		for(int i=0;i<images.size();i++) {
			this.img_phong.get(i).convert(images.get(i));
		}
	}
	
	public Home(String address, String map, float distance, String name_chu, boolean state, Info info) {
		super();
		this.address = address;
		this.map = map;
		this.distance = distance;
		this.name_chu = name_chu;
		this.state = state;
		this.info = info;
	}
	
}
