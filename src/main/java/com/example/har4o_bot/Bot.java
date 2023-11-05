package com.example.har4o_bot;

import com.example.har4o_bot.events.HelloWorldEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class Har4oBotApplication {

    public static void main(String[] args) {
        //MTE3MDExNDAyODA5NDM3Mzk1OQ.GfXb2q.MyG5F7sjVHfofFsbwseE3Tc2HOtbqoZp00T8O8
        if (args.length < 1) {
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }
        // args[0] would be the token (using an environment variable or config file is preferred for security)
        // We don't need any intents for this bot. Slash commands work without any intents!
        JDA jda = JDABuilder.createLight(args[0], Collections.emptyList())
                .addEventListeners(new Bot())
                .setActivity(Activity.playing("Type /ping"))
                .build();

        // Sets the global command list to the provided commands (removing all others)
        jda.updateCommands().addCommands(
                Commands.slash("ping", "Calculate ping of the bot"),
                Commands.slash("ban", "Ban a user from the server")
                        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS)) // only usable with ban permissions
                        .setGuildOnly(true) // Ban command only works inside a guild
                        .addOption(OptionType.USER, "user", "The user to ban", true) // required option of type user (target to ban)
                        .addOption(OptionType.STRING, "reason", "The ban reason") // optional reason
        ).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // make sure we handle the right command
        switch (event.getName()) {
            case "ping":
                long time = System.currentTimeMillis();
                event.reply("Pong!").setEphemeral(true) // reply or acknowledge
                        .flatMap(v ->
                                event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time) // then edit original
                        ).queue(); // Queue both reply and edit
                break;
            case "ban":
                // double check permissions, don't trust Discord on this!
                if (!event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                    event.reply("You cannot ban members! Nice try ;)").setEphemeral(true).queue();
                    break;
                }
                User target = event.getOption("user", OptionMapping::getUser);
                // optionally check for member information
                Member member = event.getOption("user", OptionMapping::getMember);
                if (!event.getMember().canInteract(member)) {
                    event.reply("You cannot ban this user.").setEphemeral(true).queue();
                    break;
                }
                // Before starting our ban request, tell the user we received the command
                // This sends a "Bot is thinking..." message which is later edited once we finished
                event.deferReply().queue();
                String reason = event.getOption("reason", OptionMapping::getAsString);
                AuditableRestAction<Void> action = event.getGuild().ban(target, 0); // Start building our ban request
                if (reason != null) // reason is optional
                    action = action.reason(reason); // set the reason for the ban in the audit logs and ban log
                action.queue(v -> {
                    // Edit the thinking message with our response on success
                    event.getHook().editOriginal("**" + target.getAsTag() + "** was banned by **" + event.getUser().getAsTag() + "**!").queue();
                }, error -> {
                    // Tell the user we encountered some error
                    event.getHook().editOriginal("Some error occurred, try again!").queue();
                    error.printStackTrace();
                });
                break;
            default:
                System.out.printf("Unknown command %s used by %#s%n", event.getName(), event.getUser());
        }
    }
    }

}
