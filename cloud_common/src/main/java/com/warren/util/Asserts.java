package com.warren.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.warren.constant.IResultCode;
import com.warren.exception.ApiException;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

/**
 * @author warrren
 */
public class Asserts {


    public static void notNull(@Nullable Object object, IResultCode iResultCode) {
        if (object == null) {
            throw new ApiException(iResultCode);
        }
    }

    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new ApiException(9999, message);
        }
    }

    public static void notNull(@Nullable Object object, CharSequence template, Object... params) {
        if (object == null) {
            throw new ApiException(9999, StrUtil.format(template, params));
        }
    }

    public static void notEmpty(@Nullable String text, CharSequence template, Object... params) {
        if (StrUtil.isEmpty(text)) {
            throw new ApiException(9999, StrUtil.format(template, params));
        }
    }

    public static void notEmpty(@Nullable String text, IResultCode iResultCode) {
        if (StrUtil.isEmpty(text)) {
            throw new ApiException(iResultCode);
        }
    }

    public static void eq(Object o, Object o1, String msg) {
        if (!eq(o, o1)) {
            throw new ApiException(9999, msg);
        }
    }

    public static void eq(Object o, Object o1, IResultCode iResultCode) {
        if (!eq(o, o1)) {
            throw new ApiException(iResultCode);
        }
    }

    private static boolean eq(Object o, Object o1) {
        if (ObjectUtil.isNotEmpty(o) && ObjectUtil.isNotEmpty(o1)) {
            if (o.getClass() != o1.getClass()) {
                return false;
            }
            if (o instanceof String) {
                if (!o.equals(o1)) {
                    return false;
                }
            }
            if (o instanceof BigDecimal) {
                BigDecimal b = (BigDecimal) o;
                BigDecimal b1 = (BigDecimal) o1;
                if (b.compareTo(b1) != 0) {
                    return false;
                }
            }
            if (o instanceof Integer) {
                Integer b = (Integer) o;
                Integer b1 = (Integer) o1;
                if (b.compareTo(b1) != 0) {
                    return false;
                }
            }
            if (o instanceof Boolean) {
                if (o != o1) {
                    return false;
                }
            }
        }
        if (ObjectUtil.isEmpty(o)) {
            if (ObjectUtil.isNotEmpty(o1)) {
                return false;
            }
        }
        if (ObjectUtil.isEmpty(o1)) {
            return !ObjectUtil.isNotEmpty(o);
        }
        return true;
    }

    public static void fail(String message) {
        throw new ApiException(9999, message);
    }

    public static void fail(IResultCode errorCode) {
        throw new ApiException(errorCode);
    }

}
