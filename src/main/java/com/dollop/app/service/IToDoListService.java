package com.dollop.app.service;

import com.dollop.app.entity.ToDoList;

public interface IToDoListService {
	//createToDoList
	/***
	 * 
	 * @param keyword
	 * @return
	 */
	Integer createToDoList(ToDoList toDoList);
	//getToDoById
	/***
	 * 
	 * @param keyword
	 * @return
	 */
	ToDoList getToDoById(Integer toDoListId);
	//updateToDoList
	/***
	 * 
	 * @param keyword
	 * @return
	 */
	ToDoList updateToDoList(ToDoList toDoList,Integer toDoListId);
	//deleteToDoList
	/***
	 * 
	 * @param keyword
	 * @return
	 */
	void deleteToDoList(Integer toDoListId);
}
