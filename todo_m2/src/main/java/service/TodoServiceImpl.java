package service;

import java.util.List;

import dao.TodoDao;
import dto.TodoDto;

public class TodoServiceImpl implements TodoService {

    TodoDao dao = new TodoDao();

    @Override
    public List<TodoDto> list() {
        return dao.getList();
    }

    @Override
    public TodoDto getRow(String no) {
        return dao.getRow(no);
    }

    @Override
    public boolean insert(TodoDto insertdto) {
        // 결과가 1이면 true
        // boolean flag = dao.insert(insertdto) == 1 ? true : false;
        // return falg;
        return dao.insert(insertdto) == 1;
    }

    @Override
    public boolean update(TodoDto updatedto) {
        return dao.update(updatedto) == 1;
    }

    @Override
    public boolean delete(String no) {
        return dao.delete(no) == 1;
    }
}
