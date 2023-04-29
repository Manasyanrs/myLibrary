package am.arnara.mylibrary.models;

import am.arnara.mylibrary.models.Author;
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
    private Author author_id;
}
