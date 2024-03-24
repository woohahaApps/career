package com.woohahaapps.career.exception;

import org.apache.ibatis.javassist.NotFoundException;

public class ItemNotFoundException extends NotFoundException {

    public ItemNotFoundException(String msg) {
        super(msg);
    }
}
