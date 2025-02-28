package com.yzsoft.wx.utils;

import lombok.Data;

@Data
public class TokenParams {
    private String app_key;
    private String app_secret;
    private String grant_type;
    private String scope;
    private String ocode;
}
