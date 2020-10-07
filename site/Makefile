PWD := ${CURDIR}
DOCKER_IMAGE_NAME := www9pochi
CONTAINER_REMOVE_FLAG := "--rm"
BASE_URL := "https://tamada.github.io/pochi"
HUGO_THEME := "cayman-hugo-theme"

build: build-impl public/v1.0.0/apidocs public/apidocs

build-impl:
	docker run -it ${CONTAINER_REMOVE_FLAG} -d --name ${DOCKER_IMAGE_NAME}_build -v "${PWD}":/src -v ${PWD}/public:/output -e HUGO_THEME=${HUGO_THEME} -e HUGO_BASEURL=${BASE_URL} jojomi/hugo:latest

start:
	docker run ${CONTAINER_REMOVE_FLAG} -d --name ${DOCKER_IMAGE_NAME} -p 1313:1313 -v "${PWD}":/src -v "${PWD}"/public:/output -e HUGO_THEME=${HUGO_THEME} -e HUGO_WATCH="true" -e HUGO_BASEURL=${BASE_URL} jojomi/hugo:latest

stop:
	docker stop ${DOCKER_IMAGE_NAME}

public/apidocs: resources/javadoc/targets
	sed "s!#{PROJECT_DIR}!$(PWD)/..!g" resources/javadoc/options > resources/javadoc/options_abs
	javadoc -d public/apidocs @resources/javadoc/options_abs @resources/javadoc/targets
	@rm -rf resources/javadoc/options_abs

resources/javadoc/targets: msp
	find -L msp/jp.cafebabe.{kunai,birthmarks,pochi} -name '*.java' | grep -v src/test | grep -v target > resources/javadoc/targets

msp:
	mkdir -p msp    # abbrev. of module-source-path
	ln -s ${PWD}/../kunai2/src/main/java msp/jp.cafebabe.kunai
	ln -s ${PWD}/../pochi-api/src/main/java msp/jp.cafebabe.birthmarks
	ln -s ${PWD}/../pochi-core/src/main/java msp/jp.cafebabe.pochi

public/v1.0.0/apidocs: resources/apidocs_v1.0.0.zip
	unzip -d public/v1.0.0 resources/apidocs_v1.0.0.zip

clean:
	@rm -rf static/apidocs
	@rm -rf msp resources/javadoc/targets
