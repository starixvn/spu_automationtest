package lib;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.response.SendResponse;

import java.io.File;

public class Telegram {
    private String token;
    private String chatId;
    private TelegramBot bot;
    public Telegram() {
        GetProperties prop = new GetProperties();
        this.token = prop.getProperty("telegramToken");
        this.chatId = prop.getProperty("telegramChatid");
        this.bot = new TelegramBot(token);
    }

    public void sendMessage(String message) {
        SendMessage messageHandle = new SendMessage(this.chatId, message);
        SendResponse sendResponse = bot.execute(messageHandle);
    }
    public void sendFile(String filePath){
        File reportDirectory = new File(System.getProperty("user.dir"), filePath);
        SendDocument sendDocument = new SendDocument(chatId, reportDirectory);
        SendResponse sendResponse = bot.execute(sendDocument);
    }
}


