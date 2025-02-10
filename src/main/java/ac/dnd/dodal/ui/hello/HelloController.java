package ac.dnd.dodal.ui.hello;

import ac.dnd.dodal.common.annotation.UserId;
import ac.dnd.dodal.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public ApiResponse<Object> hello(@UserId Long userId) {
        System.out.println("Hello, " + userId);
        return ApiResponse.success(userId);
    }
}
