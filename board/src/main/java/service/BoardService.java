package service;

import java.util.List;

import dto.BoardDto;

public interface BoardService {
    List<BoardDto> list();

    boolean create(BoardDto insertDto);

    BoardDto getRow(int bno);

    boolean update(BoardDto updateDto);

    boolean delete(BoardDto deleteDto);
}
