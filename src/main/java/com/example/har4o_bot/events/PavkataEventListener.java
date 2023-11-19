package com.example.har4o_bot.events;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class PavkataEventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user = event.getAuthor();
        String usersName = user.getName();

        if (usersName.equals("pablo0443") || usersName.equals("Har4o")) {

            String messageContent = event.getMessage().getContentRaw();

            String messageContentButInLowerCase = messageContent.toLowerCase();

            if (messageContentButInLowerCase.equals("hello")) {
                event.getChannel().sendMessage("Zdravei, Pavka!").queue();
            }
            if (messageContentButInLowerCase.equals("niggers"))
                event.getChannel().sendMessage("da, znaem pavka <3").queue();

            System.out.println(messageContent);
        }
    }

}
