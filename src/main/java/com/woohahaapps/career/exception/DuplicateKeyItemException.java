package com.woohahaapps.career.exception;

import org.springframework.dao.DuplicateKeyException;

public class DuplicateKeyItemException extends DuplicateKeyException {
    public DuplicateKeyItemException(String msg) {
        super(msg);
    }
}
