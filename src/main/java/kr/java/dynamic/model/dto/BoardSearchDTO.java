package kr.java.dynamic.model.dto;

import java.time.LocalDate;

public record BoardSearchDTO(
        String title,
        String content,
        String writer,
        LocalDate fromDate,
        LocalDate toDate,
        Integer minViewCount,
        String searchType,
        String keyword
) {
}
