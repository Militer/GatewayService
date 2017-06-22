package application.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: militer
 * Date: 14.05.2017.
 */
@Controller
@RequestMapping
public class RedirectToUi {
    @GetMapping("/")
    public ModelAndView redirectRoot(ModelMap model) {
        return new ModelAndView("redirect:/face-recognition-ui-service/index.html", model);
    }
}
