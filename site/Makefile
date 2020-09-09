PWD := ${CURDIR}
DOCKER_IMAGE_NAME := www9pochi
CONTAINER_REMOVE_FLAG := "--rm"
BASE_URL := "https://tamada.github.io/pochi"
HUGO_THEME := "cayman-hugo-theme"

build: public/v1.0.0/apidocs
	docker run -it ${CONTAINER_REMOVE_FLAG} -d --name ${DOCKER_IMAGE_NAME}_build -v "${PWD}":/src -v ${PWD}/public:/output -e HUGO_THEME=${HUGO_THEME} -e HUGO_BASEURL=${BASE_URL} jojomi/hugo:latest

start:
	docker run ${CONTAINER_REMOVE_FLAG} -d --name ${DOCKER_IMAGE_NAME} -p 1313:1313 -v "${PWD}":/src -v "$(PWD)"/public:/output -e HUGO_THEME=${HUGO_THEME} -e HUGO_WATCH="true" -e HUGO_BASEURL=${BASE_URL} jojomi/hugo:latest

stop:
	docker stop ${DOCKER_IMAGE_NAME}

public/v1.0.0/apidocs: resources/apidocs_v1.0.0.zip
	(cd public/v1.0.0; jar xvf ../../resources/apidocs_v1.0.0.zip)

clean:
	@rm -rf static/apidocs
