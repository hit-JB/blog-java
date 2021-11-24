package com.hit.vueblog.commom.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码并不能为空")
    private String password;
}
