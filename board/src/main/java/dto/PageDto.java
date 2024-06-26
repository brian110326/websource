package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;

    private SearchDto searchDto;
    private int total;

    public PageDto(SearchDto searchDto, int total) {
        this.searchDto = searchDto;
        this.total = total;

        // 3 => 0.3 => 1 => 10
        endPage = (int) (Math.ceil(searchDto.getPage() / 10.0)) * 10;
        startPage = endPage - 9;

        // getAmount : 페이지당 게시물 수
        int realEnd = (int) (Math.ceil((total / 1.0) / searchDto.getAmount()));

        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }

}
