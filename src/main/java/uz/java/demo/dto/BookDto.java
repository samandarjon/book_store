package uz.java.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String name;
    private Double price;
    private Double rate;
    private Long categoryId;
    private String filePath;
    private String ISBN_10;
    private String description;
    private Long languageId;
}
