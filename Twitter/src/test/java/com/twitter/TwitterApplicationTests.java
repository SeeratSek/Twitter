package com.twitter;

import com.twitter.models.Pie;
import com.twitter.repositories.PieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Autowired
	private PieRepository pieRepository;

	@Test
	public void findAllUsers() {
		List<Pie> users = pieRepository.findAll();
		assertNotNull(users);
		assertTrue(!users.isEmpty());
	}


}
