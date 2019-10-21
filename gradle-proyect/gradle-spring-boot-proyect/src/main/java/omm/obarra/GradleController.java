package omm.obarra;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class GradleController {

    @GetMapping
    public String helloGradle() {
        return "Hello Gradle!";
    }

}