package com.example.har4o_bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Har4oBotApplication {

	public static void main(String[] args) {
		JDABuilder builder = JDABuilder.createDefault("");
		
		builder.build();
	}

}
