#!/usr/bin/env bash

script_path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )
cd "$script_path"
cd ../..

docker build --tag antlr_calculator .   
