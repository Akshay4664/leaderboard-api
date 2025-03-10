package com.game.leaderboard.repository;

import com.game.leaderboard.model.Game;
import com.game.leaderboard.model.Score;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    // Get Top X Players for a Specific Game
//    @Query("SELECT s FROM Score s WHERE s.game.id = :gameId " +
//            "AND s.score = (SELECT MAX(sub.score) FROM Score sub WHERE sub.player = s.player AND sub.game.id = :gameId) " +
//            "ORDER BY s.score DESC")
//    List<Score> findTopScoresByGame(@Param("gameId") Long gameId, Pageable pageable);
}
