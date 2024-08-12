#read docker-compose, look for all services containing the build: statement and run a docker build for each one.
#Builds, (re)creates, starts, and attaches to containers for a service.
# flags -d - detached mode

docker-compose build
docker-compose --profile prod up -d