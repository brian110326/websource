package service;

import java.util.List;

import dao.BoardDao;
import dto.BoardDto;

public class BoardServiceImpl implements BoardService {

    BoardDao dao = new BoardDao();

    @Override
    public List<BoardDto> list() {
        return dao.getList();
    }

    @Override
    public boolean create(BoardDto insertDto) {
        return dao.create(insertDto) == 1;
    }

}
