SET autocommit=0;
SET unique_checks=0;
SET foreign_key_checks=0;

source /tmp/ny_cab_data_cab_trip_data_full.sql

COMMIT;
SET unique_checks=1;
SET foreign_key_checks=1;
