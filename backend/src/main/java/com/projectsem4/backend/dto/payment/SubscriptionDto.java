package com.projectsem4.backend.dto.payment;

import com.projectsem4.backend.entity.EAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private EAccount accountType;
    private String purchaseDate;
    private String validUntil;
}
