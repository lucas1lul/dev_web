package br.edu.iff.ccc.bsi.webdev.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController {


      @GetMapping("/")
        public String home(Model model) {
            System.out.println("[DEBUG] Processando requisição para /");
            model.addAttribute("pageTitle", "Bem-vindo ao BEC WebDev");
            return "views/home";
        }

    // Rota alternativa para home (opcional)
    @GetMapping("/home")
    public String homeAlternativo(Model model) {
        return home(model); 
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Login");
        return "views/login";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("pageTitle", "Cadastro");
        return "views/cadastro";
    }

}