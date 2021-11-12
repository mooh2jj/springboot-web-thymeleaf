package com.dsgcode.myhome.controller;

import com.dsgcode.myhome.model.Board;
import com.dsgcode.myhome.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model) {

        var boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }
}
