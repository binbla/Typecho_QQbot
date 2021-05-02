package com.binbla.botBlog

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

/**
 * >ClassName TypechoBotConfig.java
 * >Description TODO
 * >Author binbla
 * >Version 1.0.0
 * >CreateTime 2021-05-02  08:25
 */
object Config : AutoSavePluginConfig("TypechoConfig") {
    @ValueDescription("机器人所有者")
    val owner: Long by value(905908099L)

    @ValueDescription("机器人ID")
    val botID: MutableList<Long> by value(mutableListOf(1449427875L, 725541084L))

    @ValueDescription("数据库地址")
    val mysqlAddress:String by value("enddate.cn")

    @ValueDescription("数据库端口")
    val mysqlPort:Int by value(3306)

    @ValueDescription("数据库名")
    val databaseName:String by value("blogbinblacom")

    @ValueDescription("表单前缀")
    val tablePre:String by value("typecho_")

    @ValueDescription("数据库用户")
    val userName:String by value("typecho")

    @ValueDescription("数据库用户密码")
    val userPasswd:String by value("typecho")

    @ValueDescription("Command")
    val command_postContent:MutableList<String> by value(mutableListOf("post","发表"))
    val command_draftContent:MutableList<String> by value(mutableListOf("draft","草稿"))
}
