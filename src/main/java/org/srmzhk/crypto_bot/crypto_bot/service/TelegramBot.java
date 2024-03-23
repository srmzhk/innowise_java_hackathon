package org.srmzhk.crypto_bot.crypto_bot.service;

import lombok.Data;
import org.srmzhk.crypto_bot.crypto_bot.config.BotConfig;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.srmzhk.crypto_bot.crypto_bot.model.CurrencyModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        CurrencyModel currencyModel = new CurrencyModel();
        String answer = "";

        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText){
                case "/start":
                    execute(startCommandReceived(chatId, update.getMessage().getChat().getFirstName()));
                    break;
                case "/currentrate":
                    answer = CurrencyService.getCurrencyRate(messageText, currencyModel);
                    break;
                default:
                    answer = "Unknown command";
            }
            sendMessage(chatId, answer);
            }
        else if (update.hasCallbackQuery()) {
            String call_data = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            switch (call_data){
                case "CANCEL":
                    sendMessage(chatId, "Cancel");
                    break;
                case "3":
                    sendMessage(chatId, "Set 3% notification");
                    break;
                case "5":
                    sendMessage(chatId, "Set 5% notification");
                    break;
                case "10":
                    sendMessage(chatId, "Set 10% notification");
                    break;
                case "15":
                    sendMessage(chatId, "Set 15% notification");
                    break;
                default:
                    sendMessage(chatId, "Unknown command");
                    break;
            }
        }
    }


    private static SendMessage startCommandReceived(Long chatId, String name) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        String answer = "Hi, " + name + ", nice to meet you!" + "\n" +
                        "\n" + "select the percentage at which you will receive a notification:" + "\n";
        message.setText(answer);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("3%");
        inlineKeyboardButton1.setCallbackData("3");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("5%");
        inlineKeyboardButton2.setCallbackData("5");
        rowInline1.add(inlineKeyboardButton1);
        rowInline1.add(inlineKeyboardButton2);

        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("10%");
        inlineKeyboardButton3.setCallbackData("10");
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("15%");
        inlineKeyboardButton4.setCallbackData("15");
        rowInline2.add(inlineKeyboardButton3);
        rowInline2.add(inlineKeyboardButton4);

        List<InlineKeyboardButton> rowInline11 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton21 = new InlineKeyboardButton();
        inlineKeyboardButton21.setText("Cancel");
        inlineKeyboardButton21.setCallbackData("CANCEL");
        rowInline11.add(inlineKeyboardButton21);

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline11);

        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        return message;
    }

    private void sendMessage(Long chatId, String textToSend){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(textToSend);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Something went wrong...");
        }
    }
}