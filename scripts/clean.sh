#!/usr/bin/env bash

script_path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )
cd "$script_path"
eval $(cat imports.txt)
cd ../src
 
rm *.tokens *.interp *.class Calculator*.java 
