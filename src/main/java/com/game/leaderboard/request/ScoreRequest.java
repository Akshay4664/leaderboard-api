package com.game.leaderboard.request;

import com.game.leaderboard.model.Player;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScoreRequest {
    private Long playerId;
    private Long gameId;
    private Integer score;
    private LocalDateTime playedAt = LocalDateTime.now();
}
