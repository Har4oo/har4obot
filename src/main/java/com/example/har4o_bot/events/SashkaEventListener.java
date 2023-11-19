package com.example.har4o_bot.events;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

public class SashkaEventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user = event.getAuthor();
        String usersName = user.getName();

        if (usersName.equals(".alekscho.") || usersName.equals("Har4o")) {

            String messageContent = event.getMessage().getContentRaw();

            String messageContentButInLowerCase = messageContent.toLowerCase();

            if (messageContentButInLowerCase.equals("hello")) {
                event.getChannel().sendMessage("Hello, Mrs. Tomova 😳").queue();
            }
            if (messageContentButInLowerCase.equals("hi"))
                event.getChannel().sendMessage("Hi, Mrs. Tomova 😳").queue();

            System.out.println(messageContent);
        }
    }
    @SubscribeEvent
    public void ohHeyAMessage(MessageReceivedEvent event) {
        System.out.println(event.getMessage().getContentDisplay());
    }

}
