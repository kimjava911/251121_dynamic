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

    // choose 태그 -> 우선순위 -> 먼저 우선순위가 있는게 검색이 되면 뒤는 무시
    List<Board> searchBoardsWithChoose(BoardSearchDTO dto);

    // where -> if/choose 혼합
    List<Board> searchBoardsWithWhere(BoardSearchDTO dto);
}
