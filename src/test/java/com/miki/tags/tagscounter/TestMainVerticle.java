package com.miki.tags.tagscounter;

import com.miki.tags.tagscounter.model.MsgReq;
import com.miki.tags.tagscounter.model.MsgRes;
import com.miki.tags.tagscounter.service.Service;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.rxjava.ext.unit.Async;
import io.vertx.rxjava.ext.unit.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(VertxExtension.class)
public class TestMainVerticle {

  @BeforeEach
  void deploy_verticle(Vertx vertx, VertxTestContext testContext) {
    vertx.deployVerticle(new MainVerticle(), testContext.succeeding(id -> testContext.completeNow()));
  }

  @Test
  void verticle_deployed(Vertx vertx, VertxTestContext testContext) throws Throwable {
    testContext.completeNow();
  }


  @Test
  void cal_tester() {
    Service service = new Service();

    String input = "<html><head></head><body><div><div></div></div></p></i>";
    assertEquals(3, service.calNumberOfValidTags(input));

    String input2 = "<html><head></head><body><div><div></div></body></html>";
    assertEquals(4, service.calNumberOfValidTags(input2));

  }

}
