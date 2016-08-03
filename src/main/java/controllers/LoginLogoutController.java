/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import com.sun.org.apache.xerces.internal.impl.dv.xs.BooleanDV;
import com.sun.org.apache.xpath.internal.operations.Bool;
import models.User;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.session.FlashScope;
import ninja.session.Session;
import services.UserService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoginLogoutController {
    
    @Inject
    UserService userService;
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Login
    ///////////////////////////////////////////////////////////////////////////
    public Result login(Context context) {

        return Results.html();

    }

    public Result loginPost(@Param("username") String username,
                            @Param("password") String password,
                            @Param("rememberMe") Boolean rememberMe,
                            Context context) {

        boolean isUserNameAndPasswordValid = userService.isUserAndPasswordValid(username, password);
        
        Session session = context.getSession();
        FlashScope flashScope = context.getFlashScope();
        
        if (isUserNameAndPasswordValid) {
        	
            session.put("username", username);
            flashScope.success("login.loginSuccessful");

            User user = userService.getUserById(username);

            session.put("role", user.getRoles().get(0).getId().toString());
            session.put("userId", user.getId().toString());

            if(rememberMe != null && rememberMe)
                session.setExpiryTime(30 * 24 * 60 * 60 * 1000L);
            else
                session.setExpiryTime(60 * 60 * 1000L);

            if(username.equalsIgnoreCase("admin"))
                return Results.redirect("/admin");
            else
                return Results.redirect("/profile");
            
        }
        else {
            
            // something is wrong with the input or password not found.
        	flashScope.error("login.errorLogin");

            return Results.redirect("/login");
            
        }
        
    }

    ///////////////////////////////////////////////////////////////////////////
    // Logout
    ///////////////////////////////////////////////////////////////////////////
    public Result logout(Context context) {

    	Session session = context.getSession();
        FlashScope flashScope = context.getFlashScope();
        
        // remove any user dependent information    	
        session.clear();

        return Results.redirect("/");

    }

}
