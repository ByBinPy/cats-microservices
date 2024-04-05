package org.example.exceptions;

import org.apache.tomcat.util.net.jsse.JSSESupport;

public class UnknownCat extends Exception {
    public UnknownCat() {

    }

    public UnknownCat(String message) {
        super(message);
    }
}
