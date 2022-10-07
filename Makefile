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

run-mysql: ## Run build target jar.
	@echo "[build] build target jar"
	docker run -it --name mysql-node -p 3306:3306 -e MYSQL_DATABASE=eth_chain_data -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7.37

run: ## Run backend service.
	@echo "[run] run backend service"
	docker-compose up

test: ## Run all test.
	@echo "[test] run all tests"
	docker run -i -v $(shell pwd):/opt/demo  nick/maven-jdk:8386  /bin/bash -c 'cd /opt/demo && mvn clean test'

