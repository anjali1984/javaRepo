package com.optum.tops.J5427HC1.services;

import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;

public interface IRequestProcessor {

	HC1Response process(Hc1Request request);

}