package org.youngmonkeys.example.app.controller;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.core.annotation.EzyEventHandler;
import com.tvd12.ezyfoxserver.context.EzyAppContext;
import com.tvd12.ezyfoxserver.controller.EzyAbstractAppEventController;
import com.tvd12.ezyfoxserver.event.EzyServerReadyEvent;
import com.tvd12.ezyfoxserver.support.factory.EzyResponseFactory;
import lombok.AllArgsConstructor;
import org.youngmonkeys.example.app.constant.Commands;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.tvd12.ezyfoxserver.constant.EzyEventNames.SERVER_READY;

@EzySingleton
@EzyEventHandler(SERVER_READY) // refer EzyEventType
@AllArgsConstructor
public class ServerReadyController
    extends EzyAbstractAppEventController<EzyServerReadyEvent> {

    private final EzyResponseFactory responseFactory;

    @Override
    public void handle(EzyAppContext ctx, EzyServerReadyEvent event) {
        logger.info("ezyfox-server-unity-example app: fire custom app ready");

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(
            () -> {
                logger.info("move transform by {0.1, 0.2, 0.3}");
                responseFactory.newObjectResponse()
                    .command(Commands.MOVE_TRANSFORM)
                    .param("offsetX", 0.1)
                    .param("offsetY", 0.2)
                    .param("offsetZ", 0.3)
                    .username("unityserver")
                    .execute();
            },
            0,
            5,
            TimeUnit.SECONDS
        );

    }
}
