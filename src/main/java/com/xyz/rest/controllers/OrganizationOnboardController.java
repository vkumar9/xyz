package com.xyz.rest.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.common.constant.ApplicationConstant;
import com.xyz.common.constant.MessageConstants;
import com.xyz.common.dtos.RegisterOrgDTO;
import com.xyz.common.dtos.RegisterTheatreDTO;
import com.xyz.common.dtos.ResponseDTO;
import com.xyz.services.orgonboard.IOrgOnboardService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping(path="/orgonb", produces = {MediaType.APPLICATION_JSON_VALUE})
public class OrganizationOnboardController {
	
	@Autowired
	private IOrgOnboardService iorgOnBoardSevice;
	@PostMapping("/create")
    public ResponseEntity<ResponseDTO> registerPartner(@Valid @RequestBody RegisterOrgDTO registerOrgDTO)throws Exception {
	   
       try{
    	   registerOrgDTO=	iorgOnBoardSevice.registerOrganization(registerOrgDTO);
	   return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(ApplicationConstant.STATUS_201, MessageConstants.ORG_REG_SUCCESS,Boolean.TRUE,registerOrgDTO));
       }catch(Exception e) {
    	   throw e;
       }
    }
	@PostMapping("/regtheater/{org_code}/")
    public ResponseEntity<ResponseDTO> registerTheater(@Valid @RequestBody RegisterTheatreDTO registerTheatreDTO,@PathVariable("org_code") String orgCode)throws Exception {
	   
       try{
    	   registerTheatreDTO.setOrgCode(orgCode);
    	   registerTheatreDTO=	iorgOnBoardSevice.registerTheater(registerTheatreDTO);
	   return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(ApplicationConstant.STATUS_201, MessageConstants.THEATER_SUCCESSFULLY_REGISTER,Boolean.TRUE,registerTheatreDTO));
       }catch(Exception e) {
    	   throw e;
       }
    }

}
