package kr.java.dynamic.controller;

import kr.java.dynamic.model.domain.Board;
import kr.java.dynamic.model.dto.BoardSearchDTO;
import kr.java.dynamic.model.mapper.BoardMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class BoardController {
    private final BoardMapper boardMapper;

    public BoardController(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("boards", boardMapper.findAll());
        return "index";
    }

    @GetMapping("/board")
    public String board() {
        return "board";
    }

    @PostMapping("/board")
    public String board(@ModelAttribute Board board, RedirectAttributes redirectAttributes) {
        // pk, createdAt, updatedAt -> null
        try {
            boardMapper.insert(board);
            redirectAttributes.addFlashAttribute("msg", "정상적으로 추가");
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("msg", "에러 발생");
            return "redirect:/board";
        }
    }

    @GetMapping("/step1")
    public String step1(
            @ModelAttribute BoardSearchDTO dto,
            Model model) {
        model.addAttribute("boards", boardMapper.searchBoardsWithIf(dto));
        return "index";
    }

    @GetMapping("/step2")
    public String step2(
            @ModelAttribute BoardSearchDTO dto,
            Model model) {
        model.addAttribute("boards", boardMapper.searchBoardsWithChoose(dto));
        return "index";
    }
}
