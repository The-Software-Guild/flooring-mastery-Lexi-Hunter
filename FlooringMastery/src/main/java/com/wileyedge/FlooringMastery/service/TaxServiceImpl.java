package com.wileyedge.FlooringMastery.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wileyedge.FlooringMastery.dao.TaxDao;
import com.wileyedge.FlooringMastery.model.Tax;

@Component
public class TaxServiceImpl implements TaxService{

	TaxDao dao;
	
	public TaxServiceImpl(TaxDao dao) {
		this.dao = dao;
		readTaxFromFile();
	}
	
	public List<Tax> getTaxRates(){
		return dao.getTaxRates();
	}
	
	public BigDecimal getTaxRate(String state) {
		return dao.getTaxRate(state);
	}
	
	public void readTaxFromFile() {
		dao.readTaxFromFile();
	}
	
	public boolean isValidState(String state) {
		return dao.isValidState(state);
	}
}
