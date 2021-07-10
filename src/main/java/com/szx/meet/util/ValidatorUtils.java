package com.szx.meet.util;

import com.szx.meet.constants.BizErrorCode;
import com.szx.meet.exception.BizException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @Author szx
 * @Date 2021/7/10 11:16
 * @Description
 */
public class ValidatorUtils {

    private static final Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
            throw new BizException(BizErrorCode.PARAM_ERROR.getCode(), String.format("参数校验失败:%s", constraintViolations.iterator().next().getMessage()));
        }
    }

}
