package com.dsgcode.myhome.repository;

import com.dsgcode.myhome.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitle(String title);
    List<Board> findByTitleContainingOrContentContaining(String title, String content);
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
