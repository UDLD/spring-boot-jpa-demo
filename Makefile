.DEFAULT_GOAL:=help
SHELL = /bin/sh
UNAME := $(shell uname -s)
DATABASE_NAME=eth_chain_data

build-image: ## Run build docker image.
	@echo "[pre-build] build docker image"
	docker  build -t nick/maven-jdk:8386 .

build: ## Run build target jar.
	@echo "[build] build target jar"
	docker run -i -v $(shell pwd):/opt/demo  nick/maven-jdk:8386  /bin/bash -c 'cd /opt/demo && mvn clean package'

run-mysql: ## Run mysql service.
	@echo "[run] build target jar"
	docker run -it --name mysql-node -p 3306:3306 -e MYSQL_DATABASE=eth_chain_data -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7.37

run-nacos: ## Run nacos service.
	@echo "[run] run nacos service"
	docker run -it --name nacos-node -p 8848:8848 -p 9848:9848 -v $(shell pwd)/standalone-logs/:/home/nacos/logs -e PREFER_HOST_MODE=hostname -e MODE=standalone nacos/nacos-server:v2.1.1

run-elk: ## Run elk service.
	@echo "[run] run elk service"
	 docker-compose -f docker-compose-elk.yaml up

run-local: ## Run backend service.
	@echo "[run] run backend service"
	java -jar -Djasypt.encryptor.password=1234 target/demo-*-SNAPSHOT.jar

run: ## Run service.
	@echo "[run] run service"
	docker-compose up

test: ## Run all test.
	@echo "[test] run all tests"
	docker run -i -v $(shell pwd):/opt/demo  nick/maven-jdk:8386  /bin/bash -c 'cd /opt/demo && mvn clean test'

