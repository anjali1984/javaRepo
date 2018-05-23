package com.optum.tops.J5427HC1.controllers;

import org.springframework.http.ResponseEntity;


import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;

public interface IHC1Controller {

	ResponseEntity<?> getCOBDetails(Hc1Request request);

}