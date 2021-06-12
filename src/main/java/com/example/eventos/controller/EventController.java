package com.example.eventos.controller;

import javax.validation.Valid;

import com.example.eventos.model.Convidado;
import com.example.eventos.model.Evento;
import com.example.eventos.repository.ConvidadoRepository;
import com.example.eventos.repository.EventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EventController {

    @Autowired
    private EventoRepository er;

    @Autowired
    private ConvidadoRepository cr;
    
    @RequestMapping(value="/cadastrarEvento", method = RequestMethod.GET)
    public String form() {
        return "evento/formEvento";
    }

    @RequestMapping(value="/cadastrarEvento", method = RequestMethod.POST)
    public String form(@Valid Evento e, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos"); 
            return "redirect:/cadastrarEvento";
        }
        er.save(e);
        attributes.addFlashAttribute("mensagem", "Evento adicionado com sucesso.");
        return "redirect:/";
    }

    @RequestMapping(value = "/")
    public ModelAndView eventList() {
        ModelAndView mv = new ModelAndView("index");
        Iterable<Evento> eventos = er.findAll();
        mv.addObject("evento", eventos);
        System.out.println(eventos.toString());
        return mv;
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ModelAndView detalhesEvento(@PathVariable("code") long code) {
        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        Evento e = er.findByCode(code);
        mv.addObject("evento", e);

        Iterable<Convidado> convidados = cr.findByEvento(e);
        mv.addObject("convidados", convidados);
        return mv;
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.POST)
    public String detalhesEventoPost(@PathVariable("code") long code, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/{code}";
        }
        Evento e = er.findByCode(code);
        convidado.setEvento(e);
        cr.save(convidado);
        attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
        return "redirect:/{code}";
    }

    @RequestMapping("/deletar")
    public String deletarEvento(long code) {
        Evento e = er.findByCode(code);
        er.delete(e);
        return "redirect:/";
    }

    @RequestMapping("/deletarConvidado")
    public String deletarConvidado(String rg) {
        Convidado c = cr.findByRg(rg);
        cr.delete(c);
        String code = "" + c.getEvento().getCode();
        
        return "redirect:/"+ code;
    }
}
