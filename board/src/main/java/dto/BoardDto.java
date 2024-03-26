package dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardDto {
    private int bno;
    private String name;
    private String password;
    private String title;
    private String content;
    private String attatch;
    private int reRef;
    private int reLev;
    private int reSeq;
    private int readCount;
    private Date regDate;
}
