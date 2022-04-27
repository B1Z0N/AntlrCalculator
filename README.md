# AntlrCalculator

Simple CLI calculator app for me to get used to [ANTLR parser generator](https://www.antlr.org/).

# Up and running

To build you need `maven`, to run you need `java`. I use `openjdk18`.

### Locally

1. To build:

```
./scripts/build.sh
```

1. To run as CLI:

```
./scripts/run.sh (1.0 + -2)*-2.5e+1
```

2. To run as interactive REPL:

```
./scripts/run.sh
```

### Docker

1. To build:

```
./scripts/docker/build.sh
```

1. To run as CLI:

```
./scripts/docker/run.sh (1.0 + -2)*-2.5e+1
```

2. To run as interactive REPL:

```
./scripts/run.sh
```

## TODO

1. Cache some `maven` dependencies during `docker` build so that it doesn't take lifetime to run.
