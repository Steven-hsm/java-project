package com.hsm.entity;

import java.util.Date;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: UnixTime
 * @description: TODO
 * @date 2020/11/10 17:46
 */
public class UnixTime {
    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
