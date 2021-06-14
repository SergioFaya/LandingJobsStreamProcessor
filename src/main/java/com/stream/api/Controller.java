package com.stream.api;


import com.stream.entity.JobOffer;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private InteractiveQueryService interactiveQueryService;

    @GetMapping("")
    public ResponseEntity<JobOffer> findAll() {
        final ReadOnlyKeyValueStore<String, JobOffer> songStore =
                interactiveQueryService.getQueryableStore("JOBS_STORAGE2", QueryableStoreTypes.<String, JobOffer>keyValueStore());
        KeyValueIterator<String, JobOffer> offers = songStore.all();
        List<JobOffer> result = new ArrayList<>();
        while (offers.hasNext() && result.size() < 10) {
            result.add(offers.next().value);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }
}