package uz.java.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pageable<K> {
    private List<K> content;

    private Long page;

    private Long totalPage;
}
