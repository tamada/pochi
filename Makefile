GO := go
NAME := pochi
POCHIC := $(NAME)c
VERSION := 2.0.0
DIST := $(NAME)-$(VERSION)
DESTINATION := target

# test and build
all: build

build-all: $(DIST)

setup:
	git submodule update --init

update_version:
	@for i in README.md site/content/_index.md; do\
		sed -e 's!Version-[0-9.]*-yellowgreen!Version-${VERSION}-yellowgreen!g' -e 's!tag/v[0-9.]*!tag/v${VERSION}!g' $$i > a ; mv a $$i; \
	done
	@sed 's/const VERSION = .*/const VERSION = "${VERSION}"/g' cmd/pochi/main.go > a; mv a cmd/pochi/main.go
	@echo "Replace version to \"${VERSION}\""

	mvn versions:set -DnewVersion=$(VERSION)
	mvn versions:commit

# test pochi cli command.
test: setup
	$(GO) test -covermode=count -coverprofile=coverage.out ./cmd/pochi

# refer from https://pod.hatenablog.com/entry/2017/06/13/150342
define _createDist
	mkdir $(DESTINATION)/dist_$(1)_$(2)
	cp -r $(DIST) $(DESTINATION)/dist_$(1)_$(2)
	GOOS=$1 GOARCH=$2 go build -o $(DESTINATION)/dist_$(1)_$(2)/$(DIST)/bin/$(NAME)$(3) cmd/$(NAME)/*.go
	tar cfz $(DESTINATION)/$(DIST)_$(1)_$(2).tar.gz -C $(DESTINATION)/dist_$(1)_$(2) $(DIST)
endef

package:
	mvn verify

dist: $(DIST) package
	@$(call _createDist,darwin,amd64,)
	@$(call _createDist,windows,amd64,.exe)
	@$(call _createDist,windows,386,.exe)
	@$(call _createDist,linux,amd64,)
	@$(call _createDist,linux,386,)

build-pochi: setup test
	$(GO) build -o $(NAME) -v cmd/$(NAME)/*.go

# creating CLI interface, pochi modules, documents, and distribution package for this platform.
build: build-pochi package site $(DIST)

$(DIST): site
	@echo "creating distribution package at $(DIST)"
	@mkdir -p $(DIST)/bin $(DIST)/lib
	@cp       $(NAME) $(DIST)/bin
	@cp -r    completions examples README.md Dockerfile LICENSE $(DIST)
	@cp       {pochi-core,pochi-api,kunai2}/target/*-$(VERSION).jar pochi-core/target/lib/{asm-8.0.1,jackson-annotations-2.11.0,jackson-core-2.11.0,jackson-databind-2.11.0,vavr-0.10.3,vavr-match-0.10.3}.jar $(DIST)/lib
	@cp -r    site/public $(DIST)/docs
	@rm -rf   $(DIST)/docs/{.git,public}

site: site/public

site/public:
	make --directory site

distclean:
	@rm -rf $(DIST) target/$(DESTINATION)/dist_*
	@mvn clean

clean:
	@rm -rf $(NAME) $(DIST)
	@make --directory site clean
