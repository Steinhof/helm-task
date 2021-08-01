package com.otus.helm.domain;


import lombok.RequiredArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;


@Table(name = "user")
@RequiredArgsConstructor
public class User {

    @Id
    private Id id;

    private final String username;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String phone;
}
