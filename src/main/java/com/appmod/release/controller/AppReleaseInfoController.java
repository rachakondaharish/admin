package com.appmod.release.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appmod.release.domain.User;
import com.appmod.release.dto.AppReleaseInfoDto;
import com.appmod.release.dto.AppReleaseInfoDtos;
import com.appmod.release.dto.SearchDto;
import com.appmod.release.service.CustomUserDetailsService;
import com.appmod.release.service.IAppReleaseService;

@RestController
public class AppReleaseInfoController {
	
private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IAppReleaseService releaseService;
	
	@Autowired
    private CustomUserDetailsService userService;
	
	@RequestMapping(value = "/create/application/details", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public ResponseEntity<AppReleaseInfoDtos> createAppReleaseDetails(@RequestBody final AppReleaseInfoDto appReleaseInfoDto) {
		LOG.info("Creating Application Release Details"); //To be moved into an aspect
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    final User user = userService.findUserByEmail(auth.getName());
	    List<AppReleaseInfoDto> details = null;
	    if(user!=null) {
	    	details = releaseService.createAppReleaseDetails(appReleaseInfoDto);
	    }
	    final AppReleaseInfoDtos appDetails = new AppReleaseInfoDtos();
	    appDetails.setAppDetails(details);
		return new ResponseEntity<>(appDetails,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/view/application/details", method = RequestMethod.POST)
	public ResponseEntity<AppReleaseInfoDtos> viewAppReleaseDtls(final SearchDto searchInfo) {
		LOG.info("Retrieving details");
		final List<AppReleaseInfoDto> retrieveAppReleaseDetails = releaseService.retrieveAppReleaseDetails(searchInfo);
		final AppReleaseInfoDtos appDetails = new AppReleaseInfoDtos();
	    appDetails.setAppDetails(retrieveAppReleaseDetails);
		return new ResponseEntity<>(appDetails,HttpStatus.OK);
	}

}
