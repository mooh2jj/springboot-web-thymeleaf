package com.dsgcode.myhome.service;

import com.dsgcode.myhome.model.Board;
import com.dsgcode.myhome.model.User;
import com.dsgcode.myhome.repository.BoardRepository;
import com.dsgcode.myhome.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    final private BoardRepository boardRepository;
    final private UserRepository userRepository;

    public Board save(String username, Board board) {
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }


}
