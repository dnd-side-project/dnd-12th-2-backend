package ac.dnd.dodal.ui.hello;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

    @GetMapping
    public ApiResponse<Object> hello() {
        return ApiResponse.success("Hello, Dodal World!");
    }

    @GetMapping("/test")
    public ApiResponse<Object> test(@UserId Long userId) {
        return ApiResponse.success(userId);
    }
}
