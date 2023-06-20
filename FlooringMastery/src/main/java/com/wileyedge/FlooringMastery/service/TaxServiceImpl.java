package com.wileyedge.FlooringMastery.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.wileyedge.FlooringMastery.dao.TaxDao;
import com.wileyedge.FlooringMastery.model.Tax;

@Service
public class TaxServiceImpl implements TaxService{

	TaxDao dao;
	
	@Autowired
	public TaxServiceImpl(TaxDao dao) {
		this.dao = dao;
		readTaxFromFile();
	}
	
	@Override
	public BigDecimal getTaxRate(String state) {
		return dao.getTaxRate(state);
	}
	
	@Override
	public void readTaxFromFile() {
		dao.readTaxFromFile();
	}
	
	@Override
	public boolean isValidState(String state) {
		return dao.isValidState(state);
	}
}
