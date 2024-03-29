package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchDto {
    private int page; // 페이지번호
    private int amount; // 페이지당 게시물 수

    public SearchDto(int page, int amount) {
        this.page = page;
        this.amount = amount;
    }

    private String criteria;
    private String keyword;
}
