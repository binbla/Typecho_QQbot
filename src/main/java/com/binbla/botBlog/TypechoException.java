package com.binbla.botBlog;

/**
 * >ClassName TypechoException.java
 * >Description TODO
 * >Author binbla
 * >Version 1.0.0
 * >CreateTime 2021-05-02  14:53
 */
public class TypechoException extends Exception{
    int code;
    TypechoException(int code){
        this.code = code;
    }
}
