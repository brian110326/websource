package service;

import java.util.List;

import dto.TodoDto;

// DAO의 CRUD 메소드를 호출하는 곳

public interface TodoService {
    List<TodoDto> list();

    TodoDto getRow(String no);

    boolean insert(TodoDto insertdto);

    boolean update(TodoDto updatedto);

    boolean delete(String no);
}