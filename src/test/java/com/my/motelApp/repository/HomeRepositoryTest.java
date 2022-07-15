package com.my.motelApp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.my.motelApp.entity.Image;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class HomeRepositoryTest {

	@Autowired
	private HomeRepository homeRepository;
	
	@Autowired
	private WardRepository wardRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private InfoRepository infoRepository;
	
	@Autowired
	private DescriptionRepository descriptionRepository;
	
	@Test
	void testCreateHome() {
		List<Image> images = List.of(
				new Image("Imgae/a"),
				new Image("Image/b/cái này nè")
				);
		List<Image> savedImages = imageRepository.saveAll(images);
		
		assertThat(savedImages).isNot(null);
	}

}
