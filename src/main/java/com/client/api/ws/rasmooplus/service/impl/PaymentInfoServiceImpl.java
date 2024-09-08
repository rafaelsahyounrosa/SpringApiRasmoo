package com.client.api.ws.rasmooplus.service.impl;

import com.client.api.ws.rasmooplus.dto.PaymentProccessDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.CustomerDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.OrderDTO;
import com.client.api.ws.rasmooplus.dto.wsraspay.PaymentDTO;
import com.client.api.ws.rasmooplus.enums.UserTypeEnum;
import com.client.api.ws.rasmooplus.exceptions.BusinessException;
import com.client.api.ws.rasmooplus.exceptions.NotFoundException;
import com.client.api.ws.rasmooplus.integration.MailIntegration;
import com.client.api.ws.rasmooplus.integration.WsRaspayIntegration;
import com.client.api.ws.rasmooplus.mapper.UserPaymentInfoMapper;
import com.client.api.ws.rasmooplus.mapper.wsraspay.CreditCardMapper;
import com.client.api.ws.rasmooplus.mapper.wsraspay.CustomerMapper;
import com.client.api.ws.rasmooplus.mapper.wsraspay.OrderMapper;
import com.client.api.ws.rasmooplus.mapper.wsraspay.PaymentMapper;
import com.client.api.ws.rasmooplus.model.jpa.User;
import com.client.api.ws.rasmooplus.model.jpa.UserCredentials;
import com.client.api.ws.rasmooplus.model.jpa.UserPaymentInfo;
import com.client.api.ws.rasmooplus.repository.jpa.*;
import com.client.api.ws.rasmooplus.service.PaymentInfoService;
import com.client.api.ws.rasmooplus.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

    @Value("${webservices.rasplus.default.password}")
    private String defaultPass;


    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;
    private final WsRaspayIntegration wsRaspayIntegration;
    private final MailIntegration mailIntegration;
    private final UserDetailsRepository userDetailsRepository;
    private final UserTypeRepository userTypeRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;


    PaymentInfoServiceImpl(UserRepository userRepository, UserPaymentInfoRepository userPaymentInfoRepository,
                           WsRaspayIntegration wsRaspayIntegration, MailIntegration mailIntegration,
                           UserDetailsRepository userDetailsRepository, UserTypeRepository userTypeRepository,
                           SubscriptionTypeRepository subscriptionTypeRepository) {
        this.userRepository = userRepository;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
        this.wsRaspayIntegration = wsRaspayIntegration;
        this.mailIntegration = mailIntegration;
        this.userDetailsRepository = userDetailsRepository;
        this.userTypeRepository = userTypeRepository;
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    public Boolean process(PaymentProccessDTO dto) {
        var userOpt = userRepository.findById(dto.getUserPaymentInfoDto().getUserId());
        if (userOpt.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User user = userOpt.get();
        if (Objects.nonNull(user.getSubscriptionType())) {
            throw new BusinessException("Pagamento não pode ser processado pois usuário já possui assinatura");
        }

        Boolean successPayment = getSucessPayment(dto, user);

        return createUserCredentials(dto, user, successPayment);
    }

    private boolean createUserCredentials(PaymentProccessDTO dto, User user, Boolean successPayment) {
        if (Boolean.TRUE.equals(successPayment)) {
            UserPaymentInfo userPaymentInfo = UserPaymentInfoMapper.fromDtoToEntity(dto.getUserPaymentInfoDto(), user);
            userPaymentInfoRepository.save(userPaymentInfo);

            var userTypeOpt = userTypeRepository.findById(UserTypeEnum.ALUNO.getId());

            if (userTypeOpt.isEmpty()) {
                throw new NotFoundException("UserType not found");
            }
            UserCredentials userCredentials = new UserCredentials(null, user.getEmail(), PasswordUtils.encode(defaultPass), userTypeOpt.get());
            userDetailsRepository.save(userCredentials);

            var subscriptionTypeOpt = subscriptionTypeRepository.findByProductKey(dto.getProductKey());

            if (subscriptionTypeOpt.isEmpty()) {
                throw new NotFoundException("SubscriptionType not found");
            }
            user.setSubscriptionType(subscriptionTypeOpt.get());
            userRepository.save(user);

            mailIntegration.send(user.getEmail(), "Usuario: " + user.getEmail() + " - Senha: " + defaultPass, "Acesso liberado");
            return true;
        }
        return false;
    }

    private Boolean getSucessPayment(PaymentProccessDTO dto, User user) {
        CustomerDTO customerDto = wsRaspayIntegration.createCustomer(CustomerMapper.build(user));
        OrderDTO orderDto = wsRaspayIntegration.createOrder(OrderMapper.build(customerDto.getId(), dto));
        PaymentDTO paymentDto = PaymentMapper.build(customerDto.getId(), orderDto.getId(), CreditCardMapper.build(user.getCpf(), dto.getUserPaymentInfoDto()));
        return wsRaspayIntegration.processPayment(paymentDto);
    }
}
