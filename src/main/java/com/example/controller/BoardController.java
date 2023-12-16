package com.example.controller;

import com.example.board.model.BoardVo;
import com.example.board.service.BoardService;
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
        model.addAttribute("list", boardService.getBoardList());
        return "board/posts";
    }

    @RequestMapping(value = "/menu/add", method = RequestMethod.GET)
    public String addMenu() {
        return "board/add";
    }

    @RequestMapping(value = "/menu/addok", method = RequestMethod.POST)
    public String addMenuOk(BoardVo vo) {
        int i = boardService.insertBoard(vo);
        if (i == 0)
            System.out.println("Failed to add data");
        else
            System.out.println("Data successfully added!");
        return "redirect:board/list";
    }

    @RequestMapping(value = "/menu/view/{id}", method = RequestMethod.GET)
    public String menuView(Model model,@PathVariable("id") int id) {
        BoardVo boardVo = boardService.getBoard(id);
        model.addAttribute("p", boardVo);
        return "board/view";
    }

    @RequestMapping(value = "/menu/edit/{id}", method = RequestMethod.GET)
    public String editMenu(Model model,@PathVariable("id") int id) {
        model.addAttribute("p", boardService.getBoard(id));
        return "board/edit";
    }

    @RequestMapping(value = "/menu/editok/{id}", method = RequestMethod.POST)
    public String editPlayerOk(BoardVo vo){
        int i = boardService.updateBoard(vo);
        if (i == 0)
            System.out.println("Failed to edit data");
        else
            System.out.println("Data successfully edited!");
        return "redirect:../board/view/";
    }

    @RequestMapping(value = "/menu/delete/{id}", method = RequestMethod.GET)
    public String deleteMenu(HttpServletRequest request,@PathVariable("id") int id) {
        int i = boardService.deleteBoard(id);
        if (i == 0)
            System.out.println("Failed to delete data");
        else
            System.out.println("Data deleted successfully!");
        return "redirect:../list";
    }
}
