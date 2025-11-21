package kr.java.dynamic.model.mapper;

import kr.java.dynamic.model.domain.Board;
import kr.java.dynamic.model.dto.BoardSearchDTO;

import java.util.List;

public interface BoardMapper {
    // 기본 CR.
    List<Board> findAll();
    Board findById(int id);
    int insert(Board board);

    // if 태그
    List<Board> searchBoardsWithIf(BoardSearchDTO dto);
}
