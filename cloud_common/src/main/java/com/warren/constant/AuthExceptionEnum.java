package com.warren.constant;

import lombok.Getter;

/**
 * @author warren
 */
@Getter
public enum AuthExceptionEnum implements IResultCode {
    // 登录和用户相关的
    SESSION_TIMEOUT(10001, "登录会话已经失效"),
    USER_ACCOUNT_OUT_OF_SERVICE(10034, "该账号已被停用，登录失败！请联系系统管理员。"),
    USER_ROLE_OUT_OF_SERVICE(10035, "该账号关联角色已被停用，登录失败！请联系系统管理员。"),
    USER_DEPARTMENT_OUT_OF_SERVICE(10036, "账号关联部门已被停用，登录失败！请联系系统管理员。"),
    USER_NOT_LOGIN(10002, "用户尚未登录"),
    REQ_NONCE_ERROR(10003, "随机码为空或错误"),
    REQ_TIMESTAMP_ERROR(10004, "时间戳不存在或错误"),
    REQ_SIGN_ERROR(10005, "签名信息不存在或是错误"),
    USER_NAME_PASSWORD_ERROR(10006, "用户名或密码错误"),
    USER_PASSWORD_ERROR_TIMES_EXCEED(10007, "密码出错次数过多，账户已停用，请联系管理员"),
    CAPTCHA_ERROR(10008, "验证码错误或已过期"),
    USER_ORG_NOT_EXIST(10009, "用户不属于该组织，或组织机构不存在"),
    OLD_PWD_ERROR(10010, "原密码不正确"),
    IMPORT_EXCEL_ERROR(10011, "导入excel异常"),
    EXPORT_EXCEL_EMPTY(10012, "导出数据为空"),
    DATE_TOO_LONG(10013, "导出数据时间范围应选3个月之内"),
    USER_NO_EXIST(10014, "用户不存在"),
    PASSWORD_NO_STANDARD(10015, "密码长度8-12位，包括大小写字母、数字及特殊符号组成。"),
    USER_NO_REPEAT_VERIFY(10016, "当前发运客户已审核通过，不可重复审核"),
    USER_NO_ENOUGH_MONEY(10017, "当前发运客户没有足够的金额可供操作"),
    USER_MOBILE_HAS_EXIST(10023, "手机号已存在，无法创建企业及其关联账号"),
    CUSTOMER_HAS_VERIFY(10018, "发运客户已审核通过，不可修改相关信息"),
    CUSTOMER_NO_VERITY(10019, "发运客户尚未审核通过"),
    CUSTOMER_NO_EXIST(10020, "发运客户不存在或错误"),
    CUSTOMER_HAS_FREEZE(10021, "发运客户已冻结"),
    CUSTOMER_NO_SYSTEM_USER(10022, "发运客户对应的系统用户不存在"),
    ENTERPRISE_HAVE_CHILD(10024, "当前企业仍有子企业/部门，无法删除"),
    ENTERPRISE_HAVE_ROLE(10025, "当前企业仍有关联角色，无法删除"),
    ENTERPRISE_HAVE_ACCOUNT(10026, "当前企业仍有用户账号，无法删除"),
    ROLE_HAVE_ACCOUNT(10027, "当前角色关联用户账号，无法删除"),
    USER_NOT_ALLOW_DELETE(10028, "当前用户禁止删除"),
    PARENT_ENTERPRISE_CAN_NOT_SELF(10029, "不得选择本企业（或部门）作为上级企业（或部门）"),
    DEPARTMENT_NO_EXIST(10030, "部门不存在"),
    INTERNAL_ENTERPRISE_HAS_EXITS(10031, "内部企业已存在，不得创建多个内部企业"),
    ENTERPRISE_HAS_EXITS(10032, "企业信息已存在，不可重复创建"),
    DEPARTMENT_MAX_LEVEL(10033, "机构添加超过允许的最大层级"),
    SUBMIT_TIMEOUT(10034, "登录时间超时"),
    DEPARTMENT_NOT_LAW(10034, "不允许在默认机构外创建其他机构！");
    private int code;
    private String msg;

    AuthExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
