package com.relief.infrastructure.mappers;

import com.relief.application.dtos.NotificationDTO;
import com.relief.application.requests.NotificationRequest;
import com.relief.domain.models.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper {

    Notification toDomain(NotificationRequest source);

    void updateDomain(NotificationRequest source, @MappingTarget Notification notification);

    NotificationDTO toDTO(Notification source);

    List<NotificationDTO> toListDTO(List<Notification> source);
}
