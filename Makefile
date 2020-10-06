GO := go
NAME := pochi
POCHIC := $(NAME)c
VERSION := 2.0.0
DIST := $(NAME)-$(VERSION)
DESTINATION := target

all: test build

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

test: setup
	$(GO) test -covermode=count -coverprofile=coverage.out ./cmd/pochi

# refer from https://pod.hatenablog.com/entry/2017/06/13/150342
define _createDist
	mkdir $(DESTINATION)/dist_$(1)_$(2)
	cp -r distribution/target/$(DIST)-dist/$(DIST) $(DESTINATION)/dist_$(1)_$(2)
	cp -r examples completions Dockerfile README.md LICENSE $(DESTINATION)/dist_$(1)_$(2)/$(DIST)
	rm $(DESTINATION)/dist_$(1)_$(2)/$(DIST)/lib/distribution-$(VERSION).jar
	GOOS=$1 GOARCH=$2 go build -o $(DESTINATION)/dist_$(1)_$(2)/$(DIST)/bin/$(NAME)$(3) cmd/$(NAME)/*.go
	tar cfz $(DESTINATION)/$(DIST)_$(1)_$(2).tar.gz -C $(DESTINATION)/dist_$(1)_$(2) $(DIST)
endef

package: distribution/target/pochi-${VERSION}-dist

distribution/target/pochi-${VERSION}-dist: build
	mvn package

dist: build package
	@$(call _createDist,darwin,amd64,)
	@$(call _createDist,windows,amd64,.exe)
	@$(call _createDist,windows,386,.exe)
	@$(call _createDist,linux,amd64,)
	@$(call _createDist,linux,386,)

site:
	cd site && make

build: setup
	$(GO) build -o $(NAME) -v cmd/$(NAME)/*.go

build-all: build package
	@echo "creating distribution package at $(DIST)"
	@mkdir -p $(DIST)/bin
	@cp $(NAME) $(DIST)/bin
	@cp -r completions examples distribution/target/lib README.md Dockerfile LICENSE $(DIST)

distclean:
	mvn clean

clean:
	@rm -rf $(NAME)
