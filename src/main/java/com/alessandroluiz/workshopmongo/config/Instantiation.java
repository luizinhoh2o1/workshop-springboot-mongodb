package com.alessandroluiz.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.alessandroluiz.workshopmongo.domain.Post;
import com.alessandroluiz.workshopmongo.domain.User;
import com.alessandroluiz.workshopmongo.dto.AuthorDTO;
import com.alessandroluiz.workshopmongo.repository.PostRepository;
import com.alessandroluiz.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		postRepository.deleteAll();
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2021"), "Vamo que vamo!", "Vamos programar galera!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("22/03/2021"), "Vencemos!", "Vencemos mais um concurso de programacao!",  new AuthorDTO(alex));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}
}
