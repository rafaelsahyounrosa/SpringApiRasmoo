package com.client.api.ws.rasmooplus.service;

import com.client.api.ws.rasmooplus.dto.PaymentProccessDTO;

public interface PaymentInfoService {

    Boolean process(PaymentProccessDTO paymentProccessDTO);
}
