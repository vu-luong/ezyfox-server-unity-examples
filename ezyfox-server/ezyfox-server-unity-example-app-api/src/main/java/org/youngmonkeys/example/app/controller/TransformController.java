package org.youngmonkeys.example.app.controller;

import com.tvd12.ezyfox.core.annotation.EzyDoHandle;
import com.tvd12.ezyfox.core.annotation.EzyRequestController;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyfoxserver.entity.EzyUser;
import org.youngmonkeys.example.app.constant.Commands;
import org.youngmonkeys.example.app.request.MoveTransformResultRequest;

@EzyRequestController
public class TransformController extends EzyLoggable {

    @EzyDoHandle(Commands.MOVE_TRANSFORM_RESULT)
    public void moveTransform(EzyUser user, MoveTransformResultRequest request) {
        logger.info("Move transform result: " + request.toString());
    } 
}
