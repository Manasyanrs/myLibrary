package am.arnara.mylibrary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private int id;
    private String title;
    private String description;
    private double price;
    private String bookImg;
    private Author authorId;
    private User userId;
}
