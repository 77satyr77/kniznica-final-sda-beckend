package org.example.final02.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MvcController {

    @GetMapping
    public String landingPage(){return "index";}


}
