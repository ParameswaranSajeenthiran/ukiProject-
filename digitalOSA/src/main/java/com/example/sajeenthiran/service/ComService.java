package com.example.sajeenthiran.service;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.sajeenthiran.model.Activities;
import com.example.sajeenthiran.model.Docs;
import com.example.sajeenthiran.model.MainCommunity;
import com.example.sajeenthiran.model.Reports;
import com.example.sajeenthiran.model.SubCom;
import com.example.sajeenthiran.model.User;
import com.example.sajeenthiran.model.response.MessageResponse;

import com.example.sajeenthiran.repository.ActivitiesRepositories;
import com.example.sajeenthiran.repository.CustomRepository;
import com.example.sajeenthiran.repository.MainComRepository;
import com.example.sajeenthiran.repository.ReportRepository;
import com.example.sajeenthiran.repository.SubComRepository;
import com.example.sajeenthiran.repository.UserRepository;

@Service
public class ComService {
	
	
	@Autowired
	UserRepository userRepository;
@Autowired
SubComRepository subComRepostiory;

@Autowired
MainComRepository mainComRepository;

@Autowired
ActivitiesRepositories activitiesRepository;

@Autowired
ReportRepository reportsRepository;

@Autowired
CustomRepository customRepository;
	public ResponseEntity<?>createMainCommunity(MainCommunity mainCom){
		try {
//			System.out.println(mainCom);
			if (mainComRepository.existsByName(mainCom.getName())) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error: Username is already taken!"));
			}
			else{	
		
			
//		List<SubCom> subCom=new ArrayList<>();
//		subCom.addAll(mainCom.getSubCom());
//		List<SubCom> newSubCom= subComRepostiory.saveAll(subCom);
//		mainCom.setSubCom(newSubCom);
		mainComRepository.save(mainCom);
				
			return  new ResponseEntity<>(mainCom, HttpStatus.CREATED);
					}
			
		}catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	public ResponseEntity<List<MainCommunity>>getMainCom(){
		try {
		    List<MainCommunity> mainCommunities = new ArrayList<MainCommunity>();
		    mainComRepository.findAll().forEach(mainCommunities::add);
		
		    if (mainCommunities.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(mainCommunities, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public ResponseEntity<?>createSubCom(SubCom subCom,String id ){
		try {
			
			if (subComRepostiory.existsByName(subCom.getName())) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error: community already!"));
			}
			else{		
			
					
						Optional<MainCommunity>mainCom=mainComRepository.findById(id);
						
							if(mainCom.isPresent()){
								
							
						SubCom newSubCom=subComRepostiory.save(subCom);
						
						List<SubCom> newSubCom1=new ArrayList<>();
						newSubCom1.add(subCom);
						
						MainCommunity main=mainCom.get();
						newSubCom1.addAll(main.getSubCom());
						main.setSubCom(newSubCom1);
						String subId=mainComRepository.save(main).getId();
						
								
									return  new ResponseEntity<>(subId, HttpStatus.CREATED);
							}else {
									return ResponseEntity.badRequest()
											.body(new MessageResponse("Error: Main Community does not exist!"));
							}
			}
			
		}catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	public ResponseEntity<?>getAllSubCom(String id ){
		try {Optional<MainCommunity>mainCom=mainComRepository.findById(id);
						
							if(mainCom.isPresent()){
						List<SubCom> newSubCom=new ArrayList<>();
						MainCommunity main=mainCom.get();
						newSubCom.addAll(main.getSubCom());
					
				
								
									return  new ResponseEntity<>(newSubCom, HttpStatus.CREATED);
							}else {
									return ResponseEntity.badRequest()
											.body(new MessageResponse("Error: Main Community does not exist!"));
							}
			
			
		}catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	
	public ResponseEntity<?>joinSubCom(Activities activity){
		try {
			Date d=new Date();
			activity.setDate(d);
		activitiesRepository.save(activity);
				
			return  new ResponseEntity<>(activity, HttpStatus.CREATED);
					
			
		}catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	

	public ResponseEntity<?>joinMainCom(Activities activity){
		try {
			Date d=new Date();
			activity.setDate(d);
		activitiesRepository.save(activity);
				
			return  new ResponseEntity<>(activity, HttpStatus.CREATED);
					
			
		}catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	public ResponseEntity<?>leaveSubCom(Activities activity){
		try {
			Date d=new Date();
			activity.setDate(d);
		activitiesRepository.save(activity);
				
			return  new ResponseEntity<>(activity, HttpStatus.CREATED);
					
			
		}catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	public ResponseEntity<?>findNumMembers(String subComId){
		try {
			List<Activities> joined=activitiesRepository.findAllBySubComAndIsMember(subComId ,true);
//			 List<Activities>left=activitiesRepository.findAllBySubComAndIsMember(subComId ,false);
//			int numMem=joined.size()-left.size();
			
			return  new ResponseEntity<>(joined, HttpStatus.OK);
					
			
		}catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	public ResponseEntity<List<Activities>>getActivities(){
		try {
		    List<Activities> activities = new ArrayList<Activities>();
		    activitiesRepository.findAll().forEach(activities::add);
		
		    if (activities.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(activities, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public ResponseEntity<?>addReport(Reports report,String id){
		try {

			if (reportsRepository.existsByTitleAndType(report.getTitle(),report.getType())) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error: Username is already taken!"));
			}
			else{	
				
				Date d=new Date();
				report.setDate(d);
			report.setSubCom(id);
				
			return  new ResponseEntity<>(reportsRepository.save(report), HttpStatus.CREATED);
					}
			
		}catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	public ResponseEntity<List<Reports>>getAllReportsbyType(String type){
		try {
		    List<Reports> reports=reportsRepository.findByType(type);
		
		    if (reports.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(reports, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
