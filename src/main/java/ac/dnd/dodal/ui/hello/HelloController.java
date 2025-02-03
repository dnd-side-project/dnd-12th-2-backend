package ac.dnd.dodal.ui.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import ac.dnd.dodal.common.response.ApiResponse;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public ApiResponse<String> hello() {
        return ApiResponse.success("Hello, Dodal World!");
    }
}
