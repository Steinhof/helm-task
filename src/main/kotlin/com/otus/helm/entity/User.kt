package com.otus.helm.entity

import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var username: String? = null,

    var firstname: String? = null,

    var lastname: String? = null,

    var email: String? = null,

    var phone: String? = null
)
