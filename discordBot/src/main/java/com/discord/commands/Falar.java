package com.discord.commands;
import com.discord.App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ca.tristan.easycommands.commands.EventData;
import ca.tristan.easycommands.commands.slash.SlashExecutor;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class Falar extends SlashExecutor{
    public Map<String, String> frases;

    public Falar() {
        frases = new HashMap<>();
        carregarRespostas();
    }

     private void carregarRespostas() {
        File file = new File("/workspaces/discord-bot/discordBot/falas.csv");
        System.out.println("Caminho absoluto do arquivo CSV: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("Arquivo respostas.csv não encontrado!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",", 2);
                if (partes.length >= 2) {
                    String mensagem = partes[0].replace("\"", "").trim();
                    String resposta = partes[1].replace("\"", "").trim();
                    frases.put(mensagem, resposta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return "falar";
    }

    public String getDescription() {
        return "/falar para conversar com o bot";
    }

    @Override
    public void execute(EventData data) {
        TextChannel canal = data.getTextChannel();
        String mensagem = data.getCommand().getOptions().get(0).getAsString();
        String resposta = frases.get(mensagem);
        canal.sendMessage(resposta).queue();
    }

}

