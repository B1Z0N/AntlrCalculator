#!/usr/bin/env bash

if [ -z "$*" ]; then
  mode="-it"
else
  mode="-i -a stdin -a stdout -a stderr"
fi

docker run $mode antlr_calculator exec:java -Dexec.mainClass=org.antlrcalculator.app.MainVisitor -Dexec.args="$*" -q
