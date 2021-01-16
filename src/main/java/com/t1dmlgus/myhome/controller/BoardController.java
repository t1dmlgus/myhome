package com.t1dmlgus.myhome.controller;

import com.t1dmlgus.myhome.model.Board;
import com.t1dmlgus.myhome.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();

        model.addAttribute("boards", boards);

        return "board/list";
    }


    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){


        if(id == null){
            model.addAttribute("board", new Board());
        } else{
            Optional<Board> boardbyId = boardRepository.findById(id);
            model.addAttribute("board",boardbyId);
        }


        return "board/form";

    }

    @PostMapping("/form")
    public String boardSubmit(@ModelAttribute Board board){

        boardRepository.save(board);

        return "redirect:/board/list";
    }

}
