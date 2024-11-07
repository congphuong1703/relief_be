package com.relief.application.dtos;

import com.relief.domain.enums.ActionType;
import com.relief.domain.enums.NotificationStatus;
import com.relief.domain.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private Long id;

    private String body;

    private String subject;

    private String brief;

    private Users sender;
    private Users recipient;

    private ActionType type;

    private NotificationStatus status;
}
