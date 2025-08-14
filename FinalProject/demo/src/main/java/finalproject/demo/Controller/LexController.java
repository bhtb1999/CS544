package finalproject.demo.Controller;

import finalproject.demo.Service.LexService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lex")
public class LexController {

    private final LexService lexService;


    public LexController(LexService lexService) {
        this.lexService = lexService;
    }


    @PostMapping("/text")
    public String chatWithLex(@RequestParam String text) {
        return lexService.sendTextToLex(text);
    }
}


