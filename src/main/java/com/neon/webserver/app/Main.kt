package com.neon.webserver.app;

import com.neon.shiro.user.UserManager
import app.user.UserDao
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import spark.Request
import spark.Spark.*

fun main(args: Array<String>) {

    exception(Exception::class.java) { e, req, res -> e.printStackTrace() }

    val userManager = UserManager()

    val userDao = UserDao()

    path("/login") {
        post("") { req, res ->
            userManager.login(req.qp("email"), req.qp("password"))
            "ok"
            // userDao.save(name = req.qp("name"), email = req.qp("email"))
            // res.status(201)
            // "ok"
        }
    }

    path("/users") {

        get("") { req, res ->
            jacksonObjectMapper().writeValueAsString(userDao.users)
        }

        get("/:id") { req, res ->
            userDao.findById(req.params("id").toInt())
        }

        get("/email/:email") { req, res ->
            userDao.findByEmail(req.params("email"))
        }

        post("/create") { req, res ->
            println(req)
            userDao.save(name = req.qp("name"), email = req.qp("email"))
            res.status(201)
            "ok"
        }

        patch("/update/:id") { req, res ->
            userDao.update(
                    id = req.params("id").toInt(),
                    name = req.qp("name"),
                    email = req.qp("email")
            )
            "ok"
        }

        delete("/delete/:id") { req, res ->
            userDao.delete(req.params("id").toInt())
            "ok"
        }

    }

    userDao.users.forEach(::println)

}

fun Request.qp(key: String): String = this.queryParams(key) //adds .qp alias for .queryParams on Request object
