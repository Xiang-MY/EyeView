package com.kotlin.eyeview.ui

import java.util.regex.Matcher
import java.util.regex.Pattern

class Phone {
    //这个方法是判断是否是电话号的
    fun isMobile(str: String?): Boolean {
        var p: Pattern? = null
        var m: Matcher? = null
        var b = false
        p = Pattern.compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");// 验证手机号
        m = p.matcher(str)
        b = m.matches()
        return b
    }
}