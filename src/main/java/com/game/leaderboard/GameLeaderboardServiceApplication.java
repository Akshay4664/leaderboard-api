package com.game.leaderboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GameLeaderboardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameLeaderboardServiceApplication.class, args);
	}

}
