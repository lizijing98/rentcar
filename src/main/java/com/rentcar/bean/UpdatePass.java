package com.rentcar.bean;

import lombok.Data;

/**
 * @author lzj
 */
@Data
public class UpdatePass {
    private String oldPassword;

    private String newPassword;

    private String newPassword2;
}
