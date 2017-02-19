package hello;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yunus on 18.02.17.
 */
@RestController
public class Rest {
    @RequestMapping("/")
    public String home() {
        return "Hello Docker World";
    }

    @RequestMapping("/fu")
    public String fu() {
        return "fu Hello Docker World";
    }
}
