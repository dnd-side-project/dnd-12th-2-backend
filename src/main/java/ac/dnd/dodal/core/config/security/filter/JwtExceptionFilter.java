package ac.dnd.dodal.core.config.security.filter;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.common.exception.BadRequestException;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.core.config.security.enums.ESecurityCode;
import ac.dnd.dodal.domain.user.enums.EUserCode;
import ac.dnd.dodal.domain.user.exception.UserException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONValue;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (SecurityException e) {
            e.printStackTrace();
            log.error("FilterException throw SecurityException Exception : {}", e.getMessage());
            setErrorResponse(response, ESecurityCode.ACCESS_DENIED_ERROR);

            filterChain.doFilter(request, response);
        } catch (MalformedJwtException e) {
            e.printStackTrace();
            log.error("FilterException throw MalformedJwtException Exception : {}", e.getMessage());
            setErrorResponse(response, ESecurityCode.TOKEN_MALFORMED_ERROR);

            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            log.error("FilterException throw IllegalArgumentException Exception : {}", e.getMessage());
            setErrorResponse(response, ESecurityCode.TOKEN_TYPE_ERROR);

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            log.error("FilterException throw ExpiredJwtException Exception : {}", e.getMessage());
            setErrorResponse(response, ESecurityCode.EXPIRED_TOKEN_ERROR);

            filterChain.doFilter(request, response);
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            log.error("FilterException throw UnsupportedJwtException Exception : {}", e.getMessage());
            setErrorResponse(response, ESecurityCode.TOKEN_UNSUPPORTED_ERROR);

            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            e.printStackTrace();
            log.error("FilterException throw JwtException Exception : {}", e.getMessage());
            setErrorResponse(response, ESecurityCode.TOKEN_UNKNOWN_ERROR);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // request에 저장된 예외 정보를 가져옴
            Object exception = request.getAttribute("exception");
            switch (exception) {
                case BadRequestException badRequestException ->
                        setErrorResponse(response, badRequestException.getResultCode());
                case ESecurityCode securityCode -> setErrorResponse(response, securityCode);
                case UserException userException -> setErrorResponse(response, userException.getResultCode());
                case JwtException jwtException -> setErrorResponse(response, ESecurityCode.TOKEN_UNKNOWN_ERROR);
                case null, default -> {
                    log.error("FilterException throw Exception Exception : {}", e.getMessage());
                    setErrorResponse(response, EUserCode.NOT_FOUND_USER);
                }
            }

            filterChain.doFilter(request, response);
        }
    }

    private void setErrorResponse(HttpServletResponse response, ResultCode securityCode) throws IOException {
        response.getWriter().write(JSONValue.toJSONString(ApiResponse.failure(securityCode)));
    }
}