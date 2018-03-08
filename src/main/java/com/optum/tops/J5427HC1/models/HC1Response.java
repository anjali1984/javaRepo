package com.optum.tops.J5427HC1.models;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class HC1Response {

	
	//private List<V5427HC1> response_list_all_claims = new ArrayList<V5427HC1>() ;
	private ConcurrentHashMap<Integer, V5427HC1> response_map_all_claims = new ConcurrentHashMap<Integer, V5427HC1>();

	/*
	public List<V5427HC1> getResponse_list_all_claims() {
		return response_list_all_claims;
	}

	public void setResponse_list_all_claims(List<V5427HC1> response_list_all_claims) {
		this.response_list_all_claims = response_list_all_claims;
	}
	
	*/

	public ConcurrentHashMap<Integer, V5427HC1> getResponse_map_all_claims() {
		return response_map_all_claims;
	}

	public void setResponse_map_all_claims(ConcurrentHashMap<Integer, V5427HC1> response_map_all_claims) {
		this.response_map_all_claims = response_map_all_claims;
	} 
	
	@Override
	public String toString() {
		return "HC1Response [response_map_all_claims=" + response_map_all_claims + "]";
	}

}
