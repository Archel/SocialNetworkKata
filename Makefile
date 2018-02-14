.PHONY: build
build:
	./gradlew assemble

.PHONY: test-unit
test-unit:
	./gradlew test --tests \*Should

.PHONY: test-acceptance
test-acceptance:
	./gradlew test --tests \*Feature

all: build test-unit test-acceptance

test: test-unit test-acceptance
