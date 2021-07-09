package com.szx.meet.util;

import com.szx.meet.constants.BizErrorCode;
import com.szx.meet.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author szx
 * @Date 2021/7/9 21:22
 * @Description 正则验证
 */
@Slf4j
public class RegexUtils {

    /**
     * 手机号正则校验
     */
    private static final String PHONE_REGEX = "^1[3|4|5|6|7|8|9][0-9]\\d{4,8}$";

    /**
     * 身份证正则校验
     */
    private static final String ID_CARD_REGEX = "^[1-9]d{5}[1-9]d{3}((0d)|(1[0-2]))(([0|1|2]d)|3[0-1])d{4}$";

    /**
     * 功能描述:手机号码正则校验
     *
     * @param phone phone
     * @return boolean
     * @author szx
     * @date 2021/7/9 21:28
     */
    public static void regexPhone(String phone) {
        if (!StringUtils.hasText(phone)) {
            log.error("手机号不能为空");
            throw new BizException(BizErrorCode.PHONE_NOT_NULL);
        }
        if (phone.length() != 11) {
            log.error("{}不是大陆手机号", phone);
            throw new BizException(BizErrorCode.PHONE_ILLEGAL);
        }
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            throw new BizException(BizErrorCode.PHONE_ILLEGAL);
        }
    }

    /**
     * 功能描述:身份证号码正则校验
     *
     * @param idCardNum idCardNum
     * @return boolean
     * @author szx
     * @date 2021/7/9 21:29
     */
    public static boolean regexIdCardNum(String idCardNum) {
        return ID_CARD_REGEX.matches(idCardNum);
    }
}
