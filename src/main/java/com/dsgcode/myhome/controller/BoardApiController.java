package com.dsgcode.myhome.controller;

import com.dsgcode.myhome.model.Board;
import com.dsgcode.myhome.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class BoardApiController {

    private final BoardRepository repository;


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
                    @RequestParam(required = false, defaultValue = "") String content) {
        if (StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return repository.findAll();
        } else {
            return repository.findByTitleContainingOrContentContaining(title, content);
        }
    }
    // end::get-aggregate-root[]

    @PostMapping("/boards")
    Board newBoard(@RequestBody Board newBoard) {
        return repository.save(newBoard);
    }

    // Single item

    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    @PutMapping("/boards/{id}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {

        return repository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return repository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return repository.save(newBoard);
                });
    }

    @Secured("ROLE_ADMIN")      // ROLE_ADMIN인 사람만 delete할 수 있게 함. -> 접근하면 403 에러가 뜰거임! 403: 보안 접근 에러임!
    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
