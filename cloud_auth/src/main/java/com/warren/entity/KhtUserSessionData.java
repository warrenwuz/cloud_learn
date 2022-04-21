package com.warren.entity;
import lombok.Data;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author pc
 * @date 2020/9/21  14:24
 */
@Data
public class KhtUserSessionData implements Serializable {
    private String ssid;
    private String refreshToken="";
    private String key;
    private Boolean isAdmin;
    private Boolean isTester;
    private Boolean totalCompany;
    private Long expired;
    private Set<String> attributes = new HashSet<String>();

    private Long userId;
    private Integer userType;
    private String userMobile;
    private String userName;
    private Long enterpriseId;
    private String enterpriseName;
    private Integer loginOutTime;
    private Integer useFlag;
    private String userIdentity;
    private String loginName;
    private String plateName;
    private Long roleId;
    private Long departmentId;
    private String departmentCode;
    private Long companyId;
    private Integer isFirstLogin;

}
