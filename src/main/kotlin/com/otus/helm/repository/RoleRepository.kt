package com.otus.helm.repository

import com.otus.helm.entity.Role
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository


@Repository
interface RoleRepository : ReactiveCrudRepository<Role, String>
