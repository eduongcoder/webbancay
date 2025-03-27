package com.example.demo.dto.respone;

import lombok.*;


@Getter
@Setter
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class UserReponse {
    private String idUser;

    private String email;
    private String avatar;


}
