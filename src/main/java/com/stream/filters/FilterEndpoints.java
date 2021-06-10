package com.stream.filters;

import com.stream.entity.LandingJobsJob;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
class FilterEndpoints {

    // https://medium.com/bakdata/queryable-kafka-topics-with-kafka-streams-8d2cca9de33f

    @GetMapping("")
    public ResponseEntity<List<LandingJobsJob>> getFilteredData() {
        LandingJobsJob job = new LandingJobsJob();
        List<LandingJobsJob> result = new ArrayList();
        result.add(job);



        /*
  KTable<String, Integer> table = kafkaStream
                .mapValues((value) -> value.getId())
                .toTable();


        KafkaStreams ks = GlobalAppState.getInstance().getKafkaStreams();
        HostInfo thisInstance = GlobalAppState.getInstance().getHostPortInfo();

        Metrics metrics = null;

        StreamsMetadata metadataForMachine = ks.metadataForKey(storeName, machine, new StringSerializer());

        if (metadataForMachine.host().equals(thisInstance.host()) && metadataForMachine.port() == thisInstance.port()) {
            LOGGER.log(Level.INFO, "Querying local store for machine {0}", machine);
            metrics = getLocalMetrics(machine);
        } else {
            //LOGGER.log(Level.INFO, "Querying remote store for machine {0}", machine);
            String url = "http://" + metadataForMachine.host() + ":" + metadataForMachine.port() + "/metrics/remote/" + machine;
            metrics = Utils.getRemoteStoreState(url, 2, TimeUnit.SECONDS);
            LOGGER.log(Level.INFO, "Metric from remote store at {0} == {1}", new Object[]{url, metrics});
        }


         */
        return ResponseEntity.ok(result);
    }


}