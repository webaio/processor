create table session (
  id VARCHAR(40) NOT NULL,
  visitorId VARCHAR(40) NOT NULL,
  startAt TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  endAt TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  primary key (id)
);

create table event_session (
  id VARCHAR(40) NOT NULL,
  eventId VARCHAR(40) NOT NULL,
  sessionId VARCHAR(40) NOT NULL,
  visitorId VARCHAR(40) NOT NULL,
  visitAt TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  eventType VARCHAR(20) NOT NULL,
  payload JSON NOT NULL,
  device JSON NOT NULL,
  localization JSON NOT NULL,
  dates JSON NOT NULL,
  primary key (id)
);

create table event_aggregator (
  id VARCHAR(40) NOT NULL,
  sessionId VARCHAR(40) NOT NULL,
  visitorId VARCHAR(40) NOT NULL,
  numberOfActions INTEGER NOT NULL,
  primary key (id)
);
