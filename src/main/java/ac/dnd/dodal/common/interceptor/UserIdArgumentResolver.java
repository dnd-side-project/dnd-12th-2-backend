package ac.dnd.dodal.common.interceptor;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.exception.UnauthorizedException;
import ac.dnd.dodal.core.config.security.enums.SecurityExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Component
@Slf4j
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter = {}", parameter.getParameterType().equals(Long.class) &&
                parameter.hasParameterAnnotation(UserId.class));
        return parameter.getParameterType().equals(Long.class) && parameter.hasParameterAnnotation(UserId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        final Object userIdObj = webRequest.getAttribute("USER_ID", NativeWebRequest.SCOPE_REQUEST);
        log.info("resolveArgument = {}", userIdObj);
        if (userIdObj == null) {
            throw new UnauthorizedException(SecurityExceptionCode.ACCESS_DENIED_ERROR);
        }
        return Long.valueOf(userIdObj.toString());
    }
}
