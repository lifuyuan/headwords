package com.niuti.fuyuan.headwords.widget;

/**
 * Created by fuyuan on 2015/7/14.
 */
public enum ScrollState {

    /**
     * Widget is stopped.
     * This state does not always mean that this widget have never been scrolled.
     */
    STOP,

    /**
     * Widget is scrolled up by swiping it down.
     */
    UP,

    /**
     * Widget is scrolled down by swiping it up.
     */
    DOWN,

}
