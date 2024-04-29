package com.dollop.app.service;

import java.util.List;

import com.dollop.app.dtos.PageableResponse;
import com.dollop.app.entity.User;

public interface IUserService {
	/**
	 * Creates a new user.
	 *
	 * @param user The user to create.
	 * @return The ID of the created user.
	 */
	Integer createUser(User user);

	/**
	 * Updates an existing user.
	 *
	 * @param user   The user to update.
	 * @param userId The ID of the user to update.
	 * @return The updated user.
	 */
	User updateUser(User user, Integer userId);

	/**
	 * Deletes a user.
	 *
	 * @param userId The ID of the user to delete.
	 */
	void deleteUser(Integer userId);

	/**
	 * Retrieves a user by ID.
	 *
	 * @param userId The ID of the user to retrieve.
	 * @return The retrieved user.
	 */
	User getOneUser(Integer userId);

	/**
	 * Retrieves all users.
	 *
	 * @return A list of all users.
	 */
	List<User> getAllUser();

	/**
	 * Searches for users based on a keyword.
	 *
	 * @param keyword The keyword to search for.
	 * @return A list of users matching the keyword.
	 */
	List<User> SearchUser(String keyword);

	/**
	 * Retrieves a paginated list of all users.
	 *
	 * @param pageNumber The page number.
	 * @param pageSize   The page size.
	 * @param sortBy     The field to sort by.
	 * @param sortDir    The sort direction.
	 * @return A paginated list of users.
	 */
	PageableResponse<User> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

	/**
	 * Logs in a user.
	 *
	 * @param userName     The username of the user.
	 * @param userPassword The password of the user.
	 * @return The logged-in user.
	 */
	User loginUser(String userName, String userPassword);

	/**
	 * Registers a new user.
	 *
	 * @param user The user to register.
	 */
	void userRegistration(User user);
	
}
