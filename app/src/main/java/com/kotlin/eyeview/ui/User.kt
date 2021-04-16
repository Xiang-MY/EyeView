package com.kotlin.eyeview.ui

import cn.bmob.v3.BmobUser
import cn.bmob.v3.datatype.BmobFile

import cn.bmob.v3.datatype.BmobGeoPoint


class User : BmobUser() {
    /**
     * 昵称
     */
    var nickname: String? = null
        private set

    /**
     * 国家
     */
    var country: String? = null
        private set

    /**
     * 得分数
     */
    var score: Int? = null
        private set

    /**
     * 抢断次数
     */
    var steal: Int? = null
        private set

    /**
     * 犯规次数
     */
    var foul: Int? = null
        private set

    /**
     * 失误个数
     */
    var fault: Int? = null
        private set

    /**
     * 年龄
     */
    var age: Int? = null
        private set

    /**
     * 性别
     */
    var gender: Int? = null
        private set

    /**
     * 用户当前位置
     */
    var address: BmobGeoPoint? = null
        private set

    /**
     * 头像
     */
    var avatar: BmobFile? = null
        private set

    /**
     * 别名
     */
    var alias: List<String>? = null
        private set

    fun setNickname(nickname: String?): User {
        this.nickname = nickname
        return this
    }

    fun setCountry(country: String?): User {
        this.country = country
        return this
    }

    fun setScore(score: Int?): User {
        this.score = score
        return this
    }

    fun setSteal(steal: Int?): User {
        this.steal = steal
        return this
    }

    fun setFoul(foul: Int?): User {
        this.foul = foul
        return this
    }

    fun setFault(fault: Int?): User {
        this.fault = fault
        return this
    }

    fun setAge(age: Int?): User {
        this.age = age
        return this
    }

    fun setGender(gender: Int?): User {
        this.gender = gender
        return this
    }

    fun setAddress(address: BmobGeoPoint?): User {
        this.address = address
        return this
    }

    fun setAvatar(avatar: BmobFile?): User {
        this.avatar = avatar
        return this
    }

    fun setAlias(alias: List<String>?): User {
        this.alias = alias
        return this
    }
}
