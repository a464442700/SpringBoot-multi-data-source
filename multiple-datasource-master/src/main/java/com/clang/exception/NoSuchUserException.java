package com.clang.exception;

/**
 * @Author  Clang
 * @Date 2022-03-17
 */
public class NoSuchUserException extends Exception {

    public NoSuchUserException() {
        super();
    }

    public NoSuchUserException(String msg) {
        super(msg);
    }
}
