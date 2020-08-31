package com.miki.tags.tagscounter;

import com.miki.tags.tagscounter.model.MsgReq;
import com.miki.tags.tagscounter.model.MsgRes;
import com.miki.tags.tagscounter.service.Service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainVerticle extends AbstractVerticle {
/*
In order to create a real microservice we need to register service in a service discovery storage like zookeeper
,etcd,cunsol ...
Also we need to implement circuit breaker.
 */

  private final static Logger LOGGER = Logger.getLogger("VertxHttpServer");

  @Override
  public void start(Promise<Void> startPromise) throws Exception {


    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());
    router.post("/api/tags")
      .handler(this::getNumberOfTags);

        vertx.createHttpServer()
          .requestHandler(router)
          .listen(config().getInteger("http.port", 8085),
            result -> {
              if (result.succeeded()) {

                startPromise.complete();
              } else {
                LOGGER.log(Level.SEVERE,result.cause().getMessage());
                startPromise.fail(result.cause());
              }
            });

  }

  private void getNumberOfTags(RoutingContext routingContext) {
    JsonObject body = routingContext.getBodyAsJson();
    MsgReq  req = body.mapTo(MsgReq.class);
    Service service = new Service();
    int i = service.calNumberOfValidTags(req.getHtml());
    MsgRes msgRes = new MsgRes(i);
    routingContext.response()
      .putHeader("Content-Type", "application/json; charset=UTF8")
      .setStatusCode(200).end( JsonObject.mapFrom(msgRes).encodePrettily());
  }


}
