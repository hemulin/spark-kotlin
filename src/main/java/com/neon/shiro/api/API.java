package com.neon.shiro.api;

import com.neon.shiro.user.LoginRequest;
import com.neon.shiro.user.UserManager;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.threadPool;

class API {

    public void start() {
        init(1234);

        // get("/", (req, res) -> {
        //     return "Spark server with Apache Shiro authentication is running!";
        // });

        //----------------------APIs---------------------------------
        UserAPI();
    }

    private void UserAPI() {
        //configure user management
        UserManager um = new UserManager();

        //Login
        // post("/login", (req, res) -> {
        //     LoginRequest fromJson = gson.fromJson(req.body(), LoginRequest.class);

        //     String token = um.login(fromJson.getUser(), fromJson.getPassword());
        //     if (token.length() > 0) {
        //         return token;
        //     }
        //     res.status(401);
        //     return "Not able to login!";
        // });
    }

    static void init(int port) {
        port(port);

        int maxThreads = 4;
        int minThreads = 2;
        int timeOutMillis = 30000;
        threadPool(maxThreads, minThreads, timeOutMillis);
    }
}