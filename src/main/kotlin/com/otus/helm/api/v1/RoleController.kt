package com.otus.helm.api.v1

import com.otus.helm.security.RoleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/role")
class RoleController(
    private val roleService: RoleService
) {
    @GetMapping
    fun findAll() =
        roleService.findAll()

    @PostMapping
    fun createRole(@RequestParam name: String) =
        roleService.create(name)
}