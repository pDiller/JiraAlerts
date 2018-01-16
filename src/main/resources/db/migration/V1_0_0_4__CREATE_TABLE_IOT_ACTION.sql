CREATE TABLE IOT_ACTION (
ROUTINE_QUERY_ID BIGINT NOT NULL,
DEVICE_ID BIGINT NOT NULL,
ACTION LONGBLOB NOT NULL,
PRIORITY INT NOT NULL,
PRIMARY KEY(ROUTINE_QUERY_ID, DEVICE_ID),
CONSTRAINT IOT_ACTION_ROUTINE_QUERY_FK FOREIGN KEY (ROUTINE_QUERY_ID) REFERENCES ROUTINE_QUERY (ID),
CONSTRAINT IOT_ACTION_DEVICE_FK	FOREIGN KEY (DEVICE_ID) REFERENCES DEVICE (ID)
);