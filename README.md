====================================
## Build and deploy to docker
 
```
mvn package
docker-compose up
```

## Docker services to compose

### MySQL 5.7 

Image declared in [Dockerfile-mysql](Dockerfile-mysql)

[data/source/ny_cab_data_cab_trip_data_full.sql.zip](data/source/ny_cab_data_cab_trip_data_full.sql.zip)
unzipped into ```/tmp``` directory in mysql container

SQL dump is imported to mysql ```nyc``` database on first docker composition.

User ```user``` is granted access to ```nyc``` database.

User name and password for ```nyc``` database is specified in [docker-compose.yml](docker-compose.yml)

MySQL port 3306 is exposed as 3307 for external connections.

The mysql server can be accessed from outside of docker cluster: ```mysql://127.0.0.1:3307```

The mysql server can be accessed from inside of docker cluster: ```mysql://mysql_nyc:3306```

When docker cluster is started it is expected to have 73488 rows in ```nyc.cab_trip_data``` table.

### NYC Cab API web app

```mvn package``` packages NYC Cab API web app as WAR, using ```docker``` Spring boot profile.
 
NYC Cab API web app is deployed to tomcat docker container as specified in [docker-compose.yml](docker-compose.yml)

NYC Cab API web app is exposed as ```x4ax``` web app in tomcat web server.

Tomcat container exposes port 8091 for external connections.

### Invoking endpoints when API is deployed to docker

* To get count of trips made by cabs with medallions ```00184958F5D5FD0A9EC0B115C5B55796``` and ```000318C2E3E6381580E5C99910A60668``` 
given pickup date ```2013-12-30``` access URL in browser:

```
http://localhost:8091/x4ax/v1/report/trips/pickup-date/2013-12-30/medallions/00184958F5D5FD0A9EC0B115C5B55796,000318C2E3E6381580E5C99910A60668/count	 

```

* To get count of trips made by a cab with medallion ```00184958F5D5FD0A9EC0B115C5B55796``` ignoring cache 
given pickup date ```2013-12-30``` access URL in browser:

```
http://localhost:8091/x4ax/v1/report/trips/pickup-date/2013-12-30/medallions/00184958F5D5FD0A9EC0B115C5B55796/count?noCache=true	 

```

* to evict the data cache, run:

```
curl -X DELETE "http://localhost:8091/x4ax/v1/admin/cache"
```


## Local maven run

To run application locally use ```local```Spring profile.

```
mvn spring-boot:run -Dspring.profiles.active=local
```
