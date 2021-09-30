package com.otus.helm.entity

import javax.persistence.*

class User(

    var username: String,

    var password: String,

    var name: String,

    var role: String,
)
