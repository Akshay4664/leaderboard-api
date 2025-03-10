package com.game.leaderboard.controller;

import com.game.leaderboard.model.Score;
import com.game.leaderboard.request.ScoreRequest;
import com.game.leaderboard.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leaderboard")
public class ScoreController {

    private ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("score/submit")
    public ResponseEntity<Map<String, String>> submitScore(@RequestBody ScoreRequest request){
        Map<String, String> response = scoreService.submitScore(request);
        if(response.containsKey("status") && response.get("status").equals("error")){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(Map.of("message", "Score submitted successfully"));
    }

    @GetMapping("/top/game/{gameId}")
    public List<Score> getTopPlayersByGame(@PathVariable Long gameId, @RequestParam(defaultValue = "10") int limit) {
        return scoreService.findTopPlayerByGame(gameId);
    }

    @GetMapping("/top/game/{gameId}/country/{country}")
    public List<Score> getTopPlayersByGameAndCountry(@PathVariable Long gameId, @PathVariable String country, @RequestParam(defaultValue = "10") int limit) {
        return scoreService.findTopPlayerByGameAndCountry(gameId, country);
    }
}
