package ac.dnd.dodal.core.security.filter;

import ac.dnd.dodal.common.enums.ResultCode;
import ac.dnd.dodal.common.response.ApiResponse;
import ac.dnd.dodal.core.security.enums.SecurityExceptionCode;
import ac.dnd.dodal.domain.user.enums.UserExceptionCode;
import ac.dnd.dodal.domain.user.exception.UserBadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (SecurityException e) {
            e.printStackTrace();
            log.error("FilterException throw SecurityException Exception : {}", e.getMessage());
            setErrorResponse(response, SecurityExceptionCode.ACCESS_DENIED_ERROR);

            filterChain.doFilter(request, response);
        } catch (MalformedJwtException e) {
            e.printStackTrace();
            log.error("FilterException throw MalformedJwtException Exception : {}", e.getMessage());
            setErrorResponse(response, SecurityExceptionCode.TOKEN_MALFORMED_ERROR);

            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            log.error("FilterException throw IllegalArgumentException Exception : {}", e.getMessage());
            setErrorResponse(response, SecurityExceptionCode.TOKEN_TYPE_ERROR);

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            log.error("FilterException throw ExpiredJwtException Exception : {}", e.getMessage());
            setErrorResponse(response, SecurityExceptionCode.EXPIRED_TOKEN_ERROR);

            filterChain.doFilter(request, response);
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            log.error("FilterException throw UnsupportedJwtException Exception : {}", e.getMessage());
            setErrorResponse(response, SecurityExceptionCode.TOKEN_UNSUPPORTED_ERROR);

            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            e.printStackTrace();
            log.error("FilterException throw JwtException Exception : {}", e.getMessage());
            setErrorResponse(response, SecurityExceptionCode.TOKEN_UNKNOWN_ERROR);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // request에 저장된 예외 정보를 가져옴
            Object exception = request.getAttribute("exception");
            if (exception instanceof UserBadRequestException userException) {
                setErrorResponse(response, userException.getResultCode());
            } else if (exception instanceof SecurityExceptionCode securityCode) {
                setErrorResponse(response, securityCode);
            } else if (exception instanceof JwtException) {
                setErrorResponse(response, SecurityExceptionCode.TOKEN_UNKNOWN_ERROR);
            } else {
                log.error("FilterException throw Exception Exception : {}", e.getMessage());
                setErrorResponse(response, UserExceptionCode.NOT_FOUND_USER);
            }

            filterChain.doFilter(request, response);
        }
    }

    private void setErrorResponse(HttpServletResponse response, ResultCode securityCode) throws IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponse<Object> apiResponse = ApiResponse.failure(securityCode);
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        response.getWriter().write(jsonResponse);
    }
}
