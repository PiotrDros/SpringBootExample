package gad.example;

import com.example.DemoApplication;
import com.example.controller.DataController.ThreadInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ThreadTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test() {

        ExecutorService poll = Executors.newFixedThreadPool(4);

        List<Future<Long>> list = new ArrayList();

        for (int i = 0; i < 1000; i++) {

            list.add(poll.submit(() -> restTemplate.getForEntity("http://localhost:" + this.port + "/thread", ThreadInfo.class).getBody().id));
        }

        poll.shutdown();

        list.forEach((l) -> {
            try {
                System.out.println(">>>" + l.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

    }

}
