package com.client.api.ws.rasmooplus.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String cpf;

    @Column(name = "dt_subscription")
    private LocalDate dtSubscription;

    @Column(name = "dt_expiration")
    private LocalDate dtExpiration;

    @Column(name = "photo_name")
    private String photoName;

    private byte[] photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriptions_type_id")
    private SubscriptionType subscriptionType;
}
