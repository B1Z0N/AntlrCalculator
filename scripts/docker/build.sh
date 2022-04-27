#!/usr/bin/env bash

# go to the project root directory
script_path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )
cd "$script_path"
cd ../..

docker build --tag antlr_calculator .   
