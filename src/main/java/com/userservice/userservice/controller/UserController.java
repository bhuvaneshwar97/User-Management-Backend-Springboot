package com.userservice.userservice.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.userservice.userservice.Exception.BadRequestException;
import com.userservice.userservice.Exception.DetailsNotFound;
import com.userservice.userservice.Service.UserService;
import com.userservice.userservice.entity.User;
import com.userservice.userservice.entity.UserUpdatePayload;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/create")
	public ResponseEntity<User> save(@RequestBody @Valid User payload) throws Exception {
		User newuser =  userService.save(payload);
		if(newuser != null) {
			return new ResponseEntity<>(newuser, HttpStatus.CREATED);
		}
		else {
			throw new BadRequestException("User Creation failed");
		}
	}
	
	@GetMapping("/user/getAll/{page}/{limit}")
	public ResponseEntity<Page<User>> getUsers(@PathVariable int page,@PathVariable int limit) throws Exception {
		if(page == 0) {
			page = 1;
		}
		if(limit == 0) {
			limit = 10;
		}
		Page<User> usersDetails =  userService.getAllActiveUsers(page, limit);
		if(usersDetails != null) {
			return new ResponseEntity<>(usersDetails, HttpStatus.OK);
		}
		else {
			throw new DetailsNotFound("User data not found");
		}
	}
	
	@GetMapping("/user/search/{searchtext}")
	public ResponseEntity<List<User>> getUsers(@PathVariable String searchtext) throws Exception {
		List<User> usersDetails =  userService.searchUser(searchtext);
		if(usersDetails != null) {
			return new ResponseEntity<>(usersDetails, HttpStatus.OK);
		}
		else {
			throw new DetailsNotFound("User data not found");
		}
	}
	
	@GetMapping("/user/getByID/{id}")
	public ResponseEntity<User> getByID(@PathVariable String id) throws Exception {
		User user =  userService.getUserById(id);
		if(user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		else {
			throw new DetailsNotFound("User details not found");
		}
	}
	
	@PutMapping("/user/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id,@RequestBody User payload) throws Exception {
		User userDetails =  userService.update(id, payload);
		if(userDetails != null) {
			return new ResponseEntity<>(userDetails, HttpStatus.OK);
		}
		else {
			throw new DetailsNotFound("User details not found");
		}
	}
	
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<String> disableUser(@PathVariable String id) throws Exception {
		String response =  userService.disableUserStatus(id);
		if(response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new DetailsNotFound("User details not found");
		}	
	}
	
	@GetMapping("/user/getalldeletedusers/{page}/{limit}")
	public ResponseEntity<Page<User>> getdeletedUsers(@PathVariable int page,@PathVariable int limit) throws Exception {
		if(page == 0) {
			page = 1;
		}
		if(limit == 0) {
			limit = 10;
		}
		Page<User> usersDetails =  userService.getAllDeletedUsers(page, limit);
		if(usersDetails != null) {
			return new ResponseEntity<>(usersDetails, HttpStatus.OK);
		}
		else {
			throw new DetailsNotFound("User data not found");
		}
	}
	
	@PutMapping("/user/updateuserstatus/{status}")
    public ResponseEntity<String> updateUserStatus(@PathVariable boolean status, @RequestBody UserUpdatePayload payload) throws Exception {
        String response =  userService.updateuserstatus(status, payload.getIds());
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new DetailsNotFound("User details not found");
        }
    }
}



