package service;

import java.util.List;

import dto.BookDto;

public interface BookService {
    // dao 호출
    // CRUD
    List<BookDto> listAll();

    List<BookDto> searchListAll(String criteria, String keyword);

    BookDto read(int code);

    boolean update(BookDto updateDto);

    boolean create(BookDto insertDto);

    boolean delete(int code);
}
