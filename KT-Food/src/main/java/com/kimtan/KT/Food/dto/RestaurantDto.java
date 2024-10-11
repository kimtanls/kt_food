package com.kimtan.KT.Food.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
//các thuộc tính của đối tượng nhúng sẽ trở thành một phần của bảng cơ sở dữ liệu
// của thực thể chứa nó, thay vì được lưu trong một bảng riêng biệt.
@Embeddable
public class RestaurantDto {

    private String title;

    @Column(length = 1000)
    private List<String> images;

    private String description;

    private Long id;
}
