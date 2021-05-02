package com.binbla.botBlog;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;

import javax.xml.crypto.Data;
import java.util.Date;

public final class PluginMain extends JavaPlugin {
    public static final PluginMain INSTANCE = new PluginMain();

    private PluginMain() {
        super(new JvmPluginDescriptionBuilder("com.binbla.botBlog.plugin", "1.0")
                .name("TypechoBot")
                .author("binbla")
                .build());
    }

    @Override
    public void onEnable() {
        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class, this::handleFMsg);
        getLogger().info("TypechoBot Plugin loaded!");
        getLogger().info(""+new Date().getTime()/1000);
    }
    void handleFMsg(MessageEvent fMsg){
        new HandleThread(fMsg);
    }
}
