package com.example.controller;

import com.example.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(value = "/menu/list", method = RequestMethod.GET)
    public String MenuList(Model model) {
        model.addAttribute("list", boardService.infoSimpleList().getSimpleInfoList());
        return "posts";
    }

    @RequestMapping(value = "/menu/add", method = RequestMethod.GET)
    public String addMenu() {
        return "add";
    }

    @RequestMapping(value = "/menu/addok", method = RequestMethod.POST)
    public String addMenuOk(HttpServletRequest request) {
        int i = boardService.insertMenu(request);
        if (i == 0)
            System.out.println("Failed to add data");
        else
            System.out.println("Data successfully added!");
        return "redirect:list";
    }

    @RequestMapping(value = "/menu/view/{id}", method = RequestMethod.GET)
    public String menuView(Model model,@PathVariable("id") int id) {
        model.addAttribute("p", boardService.info(id).getInfo());
        return "view";
    }

    @RequestMapping(value = "/menu/edit/{id}", method = RequestMethod.GET)
    public String editMenu(Model model,@PathVariable("id") int id) {
        model.addAttribute("p", boardService.getVo(id));
        return "edit";
    }

    @RequestMapping(value = "/menu/editok/{id}", method = RequestMethod.POST)
    public String editPlayerOk(HttpServletRequest request,@PathVariable("id") String id){
        int i = boardService.editMenu(request);
        if (i == 0)
            System.out.println("Failed to edit data");
        else
            System.out.println("Data successfully edited!");
        return "redirect:../view/" + id;
    }

    @RequestMapping(value = "/menu/delete/{id}", method = RequestMethod.GET)
    public String deleteMenu(HttpServletRequest request,@PathVariable("id") int id) {
        int i = boardService.deleteMenu(request,id);
        if (i == 0)
            System.out.println("Failed to delete data");
        else
            System.out.println("Data deleted successfully!");
        return "redirect:../list";
    }
}
