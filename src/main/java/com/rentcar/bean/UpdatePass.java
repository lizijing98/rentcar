package com.rentcar.bean;

import lombok.Data;

/**
 * @author zyt
 */
@Data
public class UpdatePass {
    private String oldPassword;

    private String newPassword;

    private String newPassword2;
}
