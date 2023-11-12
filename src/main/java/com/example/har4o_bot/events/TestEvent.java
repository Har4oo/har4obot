package com.example.har4o_bot.events;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class TestEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        String messageContent = event.getMessage().getContentRaw();

        User user = event.getAuthor();
        String usersName = user.getName();

        System.out.println("users name "+usersName);

        if (messageContent.equals("hello") && usersName.equals("pablo0443") || messageContent.equals("Hello") && usersName.equals("pablo0443")){
            event.getChannel().sendMessage("Zdravei, Pavka!").queue();
        }

        System.out.println(messageContent);
    }

}
