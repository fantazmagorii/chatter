create table messages (
  id bigserial not null,
  message_type varchar(15) not null,
  payload varchar(160) not null,
  created_at timestamp without time zone not null,
  primary key (id)
);