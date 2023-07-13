package com.architecturelogicielle.webapp.actualite;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class StyleController {

    @GetMapping(value = "/style.css", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public byte[] getStyleCss() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/style.css");
        return Files.readAllBytes(Path.of(resource.getURI()));
    }
}
