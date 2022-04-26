#!/usr/bin/env bash

script_path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )

cd /usr/local/lib
sudo curl "https://www.antlr.org/download/antlr-4.10.1-complete.jar" -O

cd "$script_path"
eval $(cat imports.txt)
