package com.t1dmlgus.myhome.repository;

import com.t1dmlgus.myhome.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitle(String title);

    List<Board> findByTitleOrContent(String title, String content);


}
