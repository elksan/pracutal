/**
 * Copyright (C) 2012-2015 the original author or authors.
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

import com.google.inject.internal.InternalFlags;
import controllers.*;
import models.Internship;
import models.Offer;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;

import com.google.inject.Inject;
import services.AdminService;

public class Routes implements ApplicationRoutes {
    
    @Inject
    NinjaProperties ninjaProperties;

    /**
     * Using a (almost) nice DSL we can configure the router.
     * 
     * The second argument NinjaModuleDemoRouter contains all routes of a
     * submodule. By simply injecting it we activate the routes.
     * 
     * @param router
     *            The default router of this application
     */
    @Override
    public void init(Router router) {  
        
        // puts test data into db:
        if (!ninjaProperties.isProd()) {
            router.GET().route("/setup").with(ApplicationController.class, "setup");
        }
        
        ///////////////////////////////////////////////////////////////////////
        // Login / Logout
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/login").with(LoginLogoutController.class, "login");
        router.POST().route("/login").with(LoginLogoutController.class, "loginPost");
        router.GET().route("/logout").with(LoginLogoutController.class, "logout");

        ///////////////////////////////////////////////////////////////////////
        //PRACUTAL
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/offers").with(OfferController.class, "offers");
        router.GET().route("/offerDetails/{offerId}").with(OfferController.class, "offerDetails");
        router.GET().route("/newOffer").with(OfferController.class, "newOffer");
        router.POST().route("/saveOffer").with(OfferController.class, "saveOffer");
        router.GET().route("/deleteOffer/{offerId}").with(OfferController.class, "deleteOffer");
        router.GET().route("/editOffer/{offerId}").with(OfferController.class, "editOffer");
        router.POST().route("/updateOffer").with(OfferController.class, "updateOffer");
        router.GET().route("/approveOffer").with(OfferController.class, "approveOffer");
        router.GET().route("/publishOffer").with(OfferController.class, "publishOffer");
        router.GET().route("/applyForOffer").with(OfferController.class, "applyForOffer");
        router.GET().route("/viewApplicants").with(OfferController.class, "viewApplicants");
        router.POST().route("/selectCandidate").with(OfferController.class, "selectCandidate");
        router.GET().route("/saveCandidates").with(OfferController.class, "saveCandidates");
        router.GET().route("/viewCandidates").with(OfferController.class, "candidates");
        router.GET().route("/chooseFinalCandidate/{applicationId}").with(OfferController.class, "chooseFinalCandidate");
        router.GET().route("/endProcess/{applicationId}").with(InternshipController.class, "assignInternshipp");
        router.GET().route("/getOrganizationsJSON").with(OfferController.class, "getOrganizationsJSON");
        router.GET().route("/directApproveOffer").with(OfferController.class, "directApproveOffer");

        ///////////////////////////////////////////////////////////////////////
        //PROFILE
        //////////////////////////////////////////////////////////////////////
        router.GET().route("/profile").with(ProfileController.class, "profile");
        router.GET().route("/myApplications").with(ProfileController.class, "myApplications");
        router.GET().route("/editOrganizationProfile").with(ProfileController.class, "editOrganizationProfile");
        router.POST().route("/updateOrganizationProfile").with(ProfileController.class, "updateOrganizationProfile");
        router.POST().route("/uploadProfilePictureFinish").with(ProfileController.class, "uploadProfilePictureFinish");
        router.GET().route("/editStudentProfile").with(ProfileController.class, "editStudentProfile");
        router.POST().route("/updateStudentProfile").with(ProfileController.class, "updateStudentProfile");
        ///////////////////////////////////////////////////////////////////////
        // INTERNSHIP
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/assignInternship/{studentId}/{offerId}").with(InternshipController.class, "assignInternship");
        router.GET().route("/internships").with(InternshipController.class, "internships");
        router.GET().route("/logbook/{internshipId}").with(InternshipController.class, "logbook");
        router.GET().route("/newLogbookEntry").with(InternshipController.class, "logbookNewEntry");
        router.POST().route("/addLogbookEntry").with(InternshipController.class, "logbookSaveEntry");
        router.POST().route("/evaluationUploadFinish").with(InternshipController.class, "evaluationUploadFinish");
        router.GET().route("/evaluate").with(InternshipController.class, "evaluate");
        ///////////////////////////////////////////////////////////////////////
        // ADMINISTRATION
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/admin").with(AdminController.class, "menu");
        router.GET().route("/newStudent").with(AdminController.class, "newStudent");
        router.POST().route("/addStudent").with(AdminController.class, "addStudent");
        router.GET().route("/newOrganization").with(AdminController.class, "newOrganization");
        router.POST().route("/addOrganization").with(AdminController.class, "addOrganization");
		router.GET().route("/editOrganization/{organizationId}").with(AdminController.class, "editOrganization");
		router.POST().route("/updateOrganization").with(AdminController.class, "updateOrganization");
        router.GET().route("/registerUser/{token}").with(AdminController.class, "accountRegistration");
        router.POST().route("/activateUser").with(AdminController.class, "activateUser");
        router.GET().route("/students").with(AdminController.class, "students");
        router.GET().route("/organizations").with(AdminController.class, "organizations");
        router.POST().route("/importStudents").with(AdminController.class, "importStudents");
        router.GET().route("/addStudents").with(AdminController.class, "addStudents");
        router.GET().route("/react").with(AdminController.class, "react");
        router.POST().route("/setAdmin").with(AdminController.class, "generateAdminAccount");
        router.GET().route("/reports").with(AdminController.class, "reports");
        router.GET().route("/getStudentsWithInternshipAssigned").with(AdminController.class, "getStudentsWithInternshipAssigned");
		router.GET().route("/viewOrganizationProfile/{organizationId}").with(ProfileController.class, "viewOrganizationProfile");
        router.GET().route("/editStudent/{studentId}").with(AdminController.class, "editStudent");
        router.POST().route("/updateStudent").with(AdminController.class, "updateStudent");
        ///////////////////////////////////////////////////////////////////////
        // Validations
        ///////////////////////////////////////////////////////////////////////
        router.POST().route("/validateOffer").with(ValidationController.class, "validateOffer");
 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController.class, "serveWebJars");
        router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/").with(ApplicationController.class, "index");
    }

}
