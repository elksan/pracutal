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

package conf;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import dao.AdminDao;
import dao.OfferDao;
import dao.UserDao;
import dao.impl.AdminDaoImpl;
import dao.impl.InternshipDaoImpl;
import dao.impl.OfferDaoImpl;
import dao.impl.UserDaoImpl;
import services.*;
import dao.InternshipDao;
import services.impl.*;

@Singleton
public class Module extends AbstractModule {
    

    protected void configure() {
        
        bind(StartupActions.class);    
        
        bind(UserService.class).to(UserServiceImpl.class);
        bind(UserDao.class).to(UserDaoImpl.class);
        
        bind(OfferService.class).to(OfferServiceImpl.class);
        bind(OfferDao.class).to(OfferDaoImpl.class);

        bind(InternshipService.class).to(InternshipServiceImpl.class);
        bind(InternshipDao.class).to(InternshipDaoImpl.class);

        bind(AdminService.class).to(AdminServiceImpl.class);
        bind(AdminDao.class).to(AdminDaoImpl.class);

        bind(MailService.class).to(MailServiceImpl.class);
    }

}
