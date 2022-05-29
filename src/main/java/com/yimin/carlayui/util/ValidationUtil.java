package com.yimin.carlayui.util;


import com.yimin.carlayui.entity.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtil {
    private static final Validator validator;

    static {
        //默认的校验器
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static List<String> validate(User user, Class<?>... groups) {
        //校验user
        final Set<ConstraintViolation<User>> validate = validator.validate(user,groups);
        //如果不合法，将不合法信息存放到list中
        if (validate.size() > 0) {
            final List<String> list = validate.stream().map(
                    v -> "属性=" + v.getPropertyPath() +
                            ",值=" + v.getInvalidValue() +
                            ",不通过的原因=" +
                            v.getMessage()).collect(Collectors.toList());

            return list;
        }
        //校验合法，返回null
        return null;
    }
}
