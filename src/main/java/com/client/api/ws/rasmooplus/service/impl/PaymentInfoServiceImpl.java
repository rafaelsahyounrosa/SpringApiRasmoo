package com.client.api.ws.rasmooplus.service.impl;

import com.client.api.ws.rasmooplus.dto.PaymentProccessDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.CustomerDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.OrderDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.PaymentDTO;
import com.client.api.ws.rasmooplus.exceptions.BusinessException;
import com.client.api.ws.rasmooplus.exceptions.NotFoundException;
import com.client.api.ws.rasmooplus.integration.WsRaspayIntegration;
import com.client.api.ws.rasmooplus.mapper.UserPaymentInfoMapper;
import com.client.api.ws.rasmooplus.mapper.wsraspay.CreditCardMapper;
import com.client.api.ws.rasmooplus.mapper.wsraspay.CustomerMapper;
import com.client.api.ws.rasmooplus.mapper.wsraspay.OrderMapper;
import com.client.api.ws.rasmooplus.mapper.wsraspay.PaymentMapper;
import com.client.api.ws.rasmooplus.model.User;
import com.client.api.ws.rasmooplus.model.UserPaymentInfo;
import com.client.api.ws.rasmooplus.repository.UserPaymentInfoRepository;
import com.client.api.ws.rasmooplus.repository.UserRepository;
import com.client.api.ws.rasmooplus.service.PaymentInfoService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;
    private final WsRaspayIntegration wsRaspayIntegration;


    PaymentInfoServiceImpl(UserRepository userRepository, UserPaymentInfoRepository userPaymentInfoRepository,
                           WsRaspayIntegration wsRaspayIntegration){
        this.userRepository = userRepository;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
        this.wsRaspayIntegration = wsRaspayIntegration;
    }

    @Override
    public Boolean process(PaymentProccessDTO paymentProccessDTO) {
        //verifica user por id e verifica se já existe assinatura
        var userOpt = userRepository.findById(paymentProccessDTO.getUserPaymentInfoDto().getUserId());
        if(userOpt.isEmpty()){
            throw new NotFoundException("user not found");
        }

        User user = userOpt.get();
        if(Objects.nonNull(user.getSubscriptionType())){
            throw new BusinessException("Payment cannot be processed because the user already has a subscription");
        }

        //criar ou atualiza o user raspay
        CustomerDTO customerDTO = wsRaspayIntegration.createCustomer(CustomerMapper.build(user));

        //cria o pedido de pagamento
        OrderDTO orderDTO = wsRaspayIntegration.createOrder(OrderMapper.build(customerDTO.getId(), paymentProccessDTO));

        //processa e retorna o pagamento
        PaymentDTO paymentDTO = PaymentMapper.build(customerDTO.getId(),
                                                    orderDTO.getId(),
                                                    CreditCardMapper.build(user.getCpf(),
                                                    paymentProccessDTO.getUserPaymentInfoDto()));

        Boolean successPayment = wsRaspayIntegration.processPayment(paymentDTO);

        if(successPayment){
            //salva as info de pagamento
            UserPaymentInfo userPaymentInfo = UserPaymentInfoMapper.fromDtoToEntity(paymentProccessDTO.getUserPaymentInfoDto(), user);
            userPaymentInfoRepository.save(userPaymentInfo);
        }

        //envia email de criação de conta
        //retorna o sucesso ou nao do pagamento

        return null;
    }
}
