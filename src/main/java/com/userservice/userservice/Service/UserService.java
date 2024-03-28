package com.userservice.userservice.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.userservice.userservice.Exception.BadRequestException;
import com.userservice.userservice.Exception.DetailsNotFound;
import com.userservice.userservice.Repository.UserRepository;
import com.userservice.userservice.entity.User;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User userDetails) throws Exception {
		if(userRepository.existsByEmail(userDetails.getEmail())) {
			throw new BadRequestException("User already exist with this email");
		}else {
			try {
				userDetails.setStatus(true);
				User newuser =userRepository.save(userDetails);
				return newuser;
			}catch(Exception e) {
				throw new Exception(((NestedRuntimeException) e).getMostSpecificCause().getMessage());
			}
		}
	}
	
	public Page<User> getAllActiveUsers(int pageNo, int pageSize) throws Exception {
        try {
            Pageable paging = PageRequest.of(pageNo - 1, pageSize);
            return userRepository.findAllActiveUsers(paging);
        } catch (Exception e) {
            throw new Exception(((NestedRuntimeException) e).getMostSpecificCause().getMessage());
        }
    }
	
	public Page<User> getAllDeletedUsers(int pageNo, int pageSize) throws Exception{
		Pageable paging = PageRequest.of(pageNo-1, pageSize);
		try {
			Page<User> allUsers = userRepository.findAllDeletedUsers(paging);
			return allUsers;
		}catch(Exception e){
			throw new Exception(((NestedRuntimeException) e).getMostSpecificCause().getMessage());
		}
	}
	
	public List<User> searchUser(String searchkeyword) throws Exception{
		try {
			List<User> allUsers = userRepository.findByIdOrNameRegexWithLimit(searchkeyword);
			return allUsers;
		}catch(Exception e){
			throw new Exception(((NestedRuntimeException) e).getMostSpecificCause().getMessage());
		}
	}
	
	public User getUserById(String id) throws Exception {
		try {
			User userDetails = userRepository.getByUserId(id);
			return userDetails;
		}catch(Exception e) {
			throw new Exception(((NestedRuntimeException) e).getMostSpecificCause().getMessage());
		}
	}
	
	public User setUserData(User payload, User oldUserDetails) {
		if(payload.getFirstName() != null) {
			oldUserDetails.setFirstName(payload.getFirstName());
		}
		if(payload.getLastName() != null) {
			oldUserDetails.setLastName(payload.getLastName());
		}
		if(payload.getEmail() != null ) {
			oldUserDetails.setEmail(payload.getEmail());
		}
		if(payload.getDob() != null) {
			oldUserDetails.setDob(payload.getDob());
		}
		if(payload.getGender() != null) {
			oldUserDetails.setGender(payload.getGender());
		}
		if(payload.getFullAddress() != null) {
			oldUserDetails.setFullAddress(payload.getFullAddress());
		}
		if(payload.getMobile() != 0) {
			oldUserDetails.setMobile(payload.getMobile());
		}
		return oldUserDetails;
	}
	
	public User update(String id, User payload) throws Exception {
		if(userRepository.existsById(id)) {
			User oldDetails =  userRepository.getByUserId(id);
			if(oldDetails.getEmail().equals(payload.getEmail())) {
				oldDetails = setUserData(payload,oldDetails);
			}else {
				if(userRepository.existsByEmail(payload.getEmail())) {
					throw new BadRequestException("User details already exist");
				}else {
					oldDetails = setUserData(payload,oldDetails);
				}
			}
			try {
				oldDetails = userRepository.save(oldDetails);
				return oldDetails;
			}catch(Exception e){
				throw new Exception(((NestedRuntimeException) e).getMostSpecificCause().getMessage());
			}
		}else {
			throw new DetailsNotFound("User details not found.!"); 
		}
	}
	
	public String disableUserStatus(String id) throws Exception {
        if (userRepository.existsById(id)) {
            try {
            	userRepository.updateStatusToFalse(id);
                return "User status updated successfully";
            } catch (Exception e) {
                throw new Exception(((NestedRuntimeException) e).getMostSpecificCause().getMessage());
            }
        } else {
            throw new DetailsNotFound("User does not exist!");
        }
    }
	
	public String updateuserstatus(boolean status, List<String> userIds) throws Exception {
		try {
			userRepository.setStatusTrueByIds(status, userIds);
			return "User status updated successfully";
		}catch(Exception e) {
				throw new Exception(((NestedRuntimeException) e).getMostSpecificCause().getMessage());
		}
	}
}
