package com.relief.application.requests;

import com.relief.domain.enums.ActionType;
import com.relief.domain.models.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest implements BaseRequest<Notification> {

    private String body;
    private ActionType type;

    private String userId;
}
