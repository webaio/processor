Weba.IO Processor
==============

[![CircleCI](https://circleci.com/gh/webaio/processor.svg?style=svg)](https://circleci.com/gh/webaio/processor)

Processor is a part of Weba.IO application responsible for normalizing incoming events from raw http requests and storing in elasticsearch
as visit.

Available options for IngestEventsElasticsearchJob job (example):

--kafka-bootstrap-servers 100.0.0.205:9092 
--kafka-group-id IngestEventsElasticsearchJob
--kafka-topic eventor_webserver 
--es-index webaio_events
--es-type webaio_events 
--es-servers elasticsearch:9300
--es-cluster-name webaio
--run-locally (optional)