package com.game.leaderboard.service;

import com.game.leaderboard.model.Game;
import com.game.leaderboard.model.Player;
import com.game.leaderboard.model.Score;
import com.game.leaderboard.repository.GameRepository;
import com.game.leaderboard.repository.PlayerRepository;
import com.game.leaderboard.repository.ScoreRepository;
import com.game.leaderboard.request.ScoreRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public ScoreService(ScoreRepository scoreRepository, PlayerRepository playerRepository, GameRepository gameRepository) {
        this.scoreRepository = scoreRepository;
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    public Map<String, String> submitScore(ScoreRequest request){
        Map<String, String> response = new HashMap<>();

        Player player = playerRepository.findById(request.getPlayerId()).orElse(null);
        if(player == null){
            response.put("status", "error");
            response.put("message", "Player not found");
            return response;
        }

        Game game = gameRepository.findById(request.getGameId()).orElse(null);
        if(game == null){
            response.put("status", "error");
            response.put("message", "Game not found");
            return response;
        }

        Score score = new Score();
        score.setScore(request.getScore());
        score.setGame(game);
        score.setPlayer(player);
        score.setPlayedAt(request.getPlayedAt()!=null ? request.getPlayedAt() : LocalDateTime.now());

        scoreRepository.save(score);

        response.put("status", "success");
        response.put("message", "Score submitted successfully");
        return response;
    }

    public List<Score> findTopPlayerByGame(Long gameId) {
        return scoreRepository.findTop10ByGameIdOrderByScoreDesc(gameId);
    }

    public List<Score> findTopPlayerByGameAndCountry(Long gameId, String country) {
        return scoreRepository.findTop10ByGameIdAndPlayer_CountryOrderByScoreDesc(gameId, country);
    }
}
