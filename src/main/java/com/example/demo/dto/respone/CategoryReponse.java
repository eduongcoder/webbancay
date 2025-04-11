package com.example.demo.dto.respone;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

@NoArgsConstructor
@AllArgsConstructor
public class CategoryReponse {
	String idCategory;
	String categoryName;


}
