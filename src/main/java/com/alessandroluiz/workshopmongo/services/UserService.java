package com.alessandroluiz.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alessandroluiz.workshopmongo.domain.User;
import com.alessandroluiz.workshopmongo.dto.UserDTO;
import com.alessandroluiz.workshopmongo.repository.UserRepository;
import com.alessandroluiz.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void deleteById(String id) {
		if (findById(id) != null) {
			repository.deleteById(id);
		}
	}
	
	public User update(User obj) {
		User newObj = repository.findById(obj.getId()).get();
		updateData(newObj, obj);
		return repository.save(newObj);
		
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
