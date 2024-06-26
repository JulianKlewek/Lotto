#clean and package all services.
# flags: -pl select a specified project, -am If project list is specified, also build projects required by the list

sh mvnw clean
sh mvnw package -pl discovery-server-service -am
sh mvnw package -pl lotto-engine -am
sh mvnw package -pl numbers-generator -am