package com.example.har4o_bot;

import com.example.har4o_bot.events.PavkataEventListener;
import com.example.har4o_bot.events.SashkaEventListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.List;

public class Bot {
    public static void main(String[] args) {
        Dotenv config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        JDABuilder.createLight(token)
                .addEventListeners(new PavkataEventListener())
                .addEventListeners(new SashkaEventListener())
                .enableIntents(List.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT))
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.watching("YouTube Kids"))
                .build();
    }
}
