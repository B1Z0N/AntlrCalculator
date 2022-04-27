# AntlrCalculator

Simple CLI calculator app for me to get used to [ANTLR parser generator](https://www.antlr.org/).

# Up and running

To build you need `maven`, to run you need `java`. I use `openjdk18`.

### Locally

1. To build:

```
./scripts/build.sh
```

2. To run as CLI:

```
./scripts/run.sh (1.0 + -2)*-2.5e+1  # wait for some time
```

3. To run as interactive REPL:

```
./scripts/run.sh  # wait for some time
```

4. To run tests:

```
./scripts/test.sh
```


### Docker

1. To build:

```
./scripts/docker/build.sh
```

2. To run as CLI:

```
./scripts/docker/run.sh (1.0 + -2)*-2.5e+1  # wait for some time
```

3. To run as interactive REPL:

```
./scripts/docker/run.sh  # wait for some time
```

4. To run tests:

```
./scripts/docker/test.sh
```

## TODO

1. Cache some `maven` dependencies during `docker` build so that it doesn't take lifetime to run.
2. Mount src to docker so that you could edit it on the fly.
