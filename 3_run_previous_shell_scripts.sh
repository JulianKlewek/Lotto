#Clean, package, build images if not present, starts dockerized app.

sh 1_clean_and_package-all.sh
sh 2_run_docker-compose.sh