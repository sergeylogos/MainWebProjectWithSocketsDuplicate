package ua.com.owu.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.entity.Product;
import ua.com.owu.entity.User;
import ua.com.owu.services.ProductService;
import ua.com.owu.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;


@Controller
public class StartController {

    private static final Logger logger = Logger.getLogger(StartController.class);
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(Principal principal, Model model, HttpServletRequest request) {
        model.addAttribute("principal", principal);

        if (request.getHeaderNames().hasMoreElements()) {
            String nextElement = request.getHeaderNames().nextElement();
            System.out.println(nextElement);
            System.out.println(request.getHeader(nextElement));
        }

        return "index";
    }

    @GetMapping("/usersPage")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "usersPage";
    }


    @GetMapping("/productsPage")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        return "productsPage";
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

    @GetMapping("/pageNumber")
    public String pageable(@RequestParam("pageNumber") Integer pageNumber, Model model) {
        model.addAttribute("all", userService.allUsersPageable(pageNumber).getContent());
        return "index";
    }


    @GetMapping("/admin")
    public String adminPage(ModelMap model, Principal principal) {
        model.addAttribute("principal", principal);

        return "adminPage";
    }

    @GetMapping("/login")
    public String login() {
        return "logingPage";
    }


    @GetMapping("/accessDeniedPage")
    public String accessDeniedPage() {
        return "accessDeniedPage";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
//        return "redirect:/login?logout";
        return "index";
    }


    @GetMapping("/join")
    public String joinUP(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("products", productService.findAll());

        return "joinUPPage";
    }


    /***************************************POST********************************************************/

    @PostMapping("/save")
    public String save(@RequestParam("name") String name, @RequestParam("password") String passwprd) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(passwprd));
        user.setUsername(name);
        userService.save(user);
        return "index";
    }

    @PostMapping(value = "/saveProduct", headers = "host=localhost:8080")
    public String saveProduct(Model model, HttpServletRequest request, Product product) {

        if (request.getHeaderNames().hasMoreElements()) {
            String nextElement = request.getHeaderNames().nextElement();
            System.out.println(nextElement);
            System.out.println(request.getHeader(nextElement));
        }

        productService.save(product);


        return "index";
    }

    @PostMapping("/joinUP")
    public String joinUP(@RequestParam int userID, @RequestParam int productID) {

        User user = userService.findOne(userID);
        Product product = productService.findOne(productID);

        product.setUser(user);
        productService.save(product);
        return "redirect:/join";
    }

}
