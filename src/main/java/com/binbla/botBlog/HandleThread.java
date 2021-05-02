package com.binbla.botBlog;

import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.utils.MiraiLogger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * >ClassName HandleThread.java
 * >Description TODO
 * >Author binbla
 * >Version 1.0.0
 * >CreateTime 2021-05-02  08:23
 */
public class HandleThread extends Thread {
    MessageEvent fMsg;

    HandleThread(MessageEvent fMsg) {
        this.fMsg = fMsg;
        this.start();
    }

    @Override
    public void run() {
        MessageChain feedback;
        String content[] = splitMessage();
        try {
            if ("".equals(content[1])) throw new TypechoException(101);
            DBConnection dbConnection = new DBConnection();
            switch (choseFunction(content[0])) {
                case POST:
                    dbConnection.postContent(content[1], content[2]);
                    dbConnection.close();
                    break;
                default:
                    break;
            }
        } catch (TypechoException e) {

        }

    }

    private Function choseFunction(String command) {
        if (Config.INSTANCE.getCommand_postContent().contains(command)) return Function.POST;
        if (Config.INSTANCE.getCommand_draftContent().contains(command)) return Function.DRAFT;
        return Function.UNKNOW;
    }

    private String[] splitMessage() {
        return fMsg.getMessage().contentToString().split("[\\s]", 3);//0指令 1标题 2内容
    }


}
