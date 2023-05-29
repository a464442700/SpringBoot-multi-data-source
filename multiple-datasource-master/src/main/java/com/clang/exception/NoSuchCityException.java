package com.clang.exception;

/**
 * @Author  Clang
 * @Date 2022-03-17
 */
public class NoSuchCityException extends Exception {

    public NoSuchCityException() {
        super();
    }

    public NoSuchCityException(String msg) {
        super(msg);
    }
}
