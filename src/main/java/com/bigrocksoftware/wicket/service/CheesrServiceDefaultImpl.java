package com.bigrocksoftware.wicket.service;

import java.io.Serializable;
import java.util.List;

import com.bigrocksoftware.wicket.domain.Cheese;

public class CheesrServiceDefaultImpl implements CheesrService, Serializable {

	private static final long serialVersionUID = 1L;

	private List<Cheese> cheeses;
	
	public CheesrServiceDefaultImpl(List<Cheese> cheeses){
		this.cheeses = cheeses;
	}
	
	public Cheese findByName(String name) {

		for(Cheese cheese : cheeses){
			if(cheese.getName().equalsIgnoreCase(name)){
				return cheese;
			}
		}
		return null;
	}

}
