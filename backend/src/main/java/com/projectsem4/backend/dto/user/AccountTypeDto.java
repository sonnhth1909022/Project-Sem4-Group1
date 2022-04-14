package com.projectsem4.backend.dto.user;

import com.projectsem4.backend.entity.EAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountTypeDto {
    private EAccount accountType;
}
