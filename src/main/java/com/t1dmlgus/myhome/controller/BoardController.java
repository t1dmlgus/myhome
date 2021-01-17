package com.t1dmlgus.myhome.controller;

import com.t1dmlgus.myhome.model.Board;
import com.t1dmlgus.myhome.repository.BoardRepository;
import com.t1dmlgus.myhome.validate.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;


    @GetMapping("/list")
    public String list(Model model){
        Page<Board> boards = boardRepository.findAll(PageRequest.of(0, 20));
                                                    // 기본

        model.addAttribute("boards", boards);

        return "board/list";
    }


    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){
    //                                                              Board id

        if(id == null){
            model.addAttribute("board", new Board());
        } else{

            Optional<Board> boardbyId = boardRepository.findById(id);
            model.addAttribute("board",boardbyId);
        }


        return "board/form";

    }

    @PostMapping("/form")
    //                        @ModelAttribute, @Valid
    public String boardSubmit(@Valid Board board, BindingResult bindingResult){

        boardValidator.validate(board, bindingResult);



        if(bindingResult.hasErrors()){
            return "board/form";
        }

        boardRepository.save(board);

        return "redirect:/board/list";
    }

}
