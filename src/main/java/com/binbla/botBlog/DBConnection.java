package com.binbla.botBlog;

import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.utils.MiraiLogger;

import java.sql.*;
import java.util.Date;

/**
 * >ClassName DBConnection.java
 * >Description 管理连接部分
 * >Author binbla
 * >Version 1.0.0
 * >CreateTime 2021-05-02  09:37
 */
public class DBConnection {
    MiraiLogger logger;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    Boolean alive;//连接存活状态
    DBConnection(){
        logger = PluginMain.INSTANCE.getLogger();
        alive = tryConnect();
    }
    protected boolean tryConnect() {
        // 验证和连接数据库
        if (connection == null) {
            logger.info("正在尝试验证数据库账户和密码...");
            try {
                connection = JDBCUtils.INSTANCE.getConnection();
                logger.info("验证成功!");
            } catch (Exception e) {
                logger.info("验证失败");
                return false;
            }
        }
        if (statement == null) {
            logger.info("正在尝试建立与数据库的连接...\n");
            try {
                statement = connection.createStatement();
                logger.info("连接成功");
            } catch (Exception e) {
                logger.info("连接失败");
                return false;
            }
        }
        return true;
    }
    public Boolean close(){
        try {
            if(resultSet!=null)
                resultSet.close();
            statement.close();
            connection.close();
            alive = false;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean postContent(String title,String text){
        long time = new Date().getTime()/1000;
        try (PreparedStatement postStatement = connection.prepareStatement(
                "INSERT INTO `" + Config.INSTANCE.getTablePre() +
                        "contents` (`cid`, `title`, `slug`, `created`, `modified`, `text`, `order`, `authorId`, `template`, `type`, `status`, `password`, `commentsNum`, `allowComment`, `allowPing`, `allowFeed`, `parent`, `views`) " +
                        "VALUES (NULL,?,NULL,?,?,?,0,1,NULL,'post','publish',NULL,0,1,1,1,0,0)")) {
            postStatement.setObject(1, title);
            postStatement.setObject(2, time);
            postStatement.setObject(3, time);
            postStatement.setObject(4,"<!--markdown-->"+text);
            postStatement.executeUpdate();
            ResultSet set = statement.executeQuery("select @@identity FROM typecho_contents");
            set.next();Long cid=set.getLong(1);
            try(PreparedStatement relationshipStatement = connection.prepareStatement(
                    "INSERT INTO "+Config.INSTANCE.getTablePre()+"relationships(`cid`,`mid`) "+
                            "VALUES(?,?)")){
                relationshipStatement.setObject(1,cid);
                relationshipStatement.setObject(2,1);
                relationshipStatement.executeUpdate();
            }
        }catch (Exception e){
            logger.info(e.toString());
            return false;
        }
        return true;
    }
}
