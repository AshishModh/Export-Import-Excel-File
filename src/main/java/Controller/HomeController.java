package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {


    @GetMapping("/")
    public String PrintHello(ModelMap model) {
        model.addAttribute("message", "Welcome To Home Page!!!!");
        return "HomePage";
    }

}
