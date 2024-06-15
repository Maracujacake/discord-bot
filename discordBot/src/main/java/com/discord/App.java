package com.discord;

import java.io.IOException;
import java.net.URISyntaxException;

import ca.tristan.easycommands.EasyCommands;
import ca.tristan.easycommands.commands.defaults.HelpCmd;
import ca.tristan.easycommands.commands.defaults.ReloadConfigCmd;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.GatewayIntent;
import com.discord.commands.Ping;
import com.discord.commands.Falar;


public class App 
{   
    public static JDA jda;
    public static void main( String[] args ) throws  IOException, URISyntaxException, InterruptedException {
       EasyCommands easyCom = new EasyCommands();
        final GatewayIntent[] gatewaysIntents = {GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MEMBERS};
       jda = easyCom.registerListeners(
       
       ).addExecutor(
        new HelpCmd(easyCom),
        new Ping(),
        new Falar(),
        new ReloadConfigCmd(easyCom)
       ).addEnabledCacheFlags(

       ).addGatewayIntents(
        gatewaysIntents
       ).buildJDA(

       );
        
    }
}
