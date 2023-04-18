package com.wang.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: wangguangpeng
 * @Date: 2023/4/18
 * @Description:
 */

@Setter
@Getter
@NoArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;


}
