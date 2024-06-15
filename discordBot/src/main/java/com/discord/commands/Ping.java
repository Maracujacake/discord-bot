package com.discord.commands;
import com.discord.App;

import ca.tristan.easycommands.EasyCommands;
import ca.tristan.easycommands.commands.EventData;
import ca.tristan.easycommands.commands.slash.SlashExecutor;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class Ping extends SlashExecutor {

   public String getName() {
      return "ping";
   }
 
   public String getDescription() {
      return "/ping to see how laggy you are ;)";
   }

   @Override
   public void execute(EventData data) {
      Long ms = App.jda.getGatewayPing();
      TextChannel canal = data.getTextChannel();
      canal.sendMessage("Pong! " + ms + "ms").queue();

   }

}
