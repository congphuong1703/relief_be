package com.relief.application.requests;

import com.relief.domain.enums.YesNo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeRequest {

    private String postId;
    private String commentId;
    private YesNo isLiked;
}
