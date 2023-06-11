#!/bin/bash
set -Eexuo pipefail

cd "$(dirname "$0")"/.. || exit

declare module_name="$1"

# copy files into tmp dir
mkdir -p "./tmp/$module_name"
cp -a "./src/main/java/$module_name/" "./tmp/"

# remove the first line to remove the pacakge statements
find ./tmp -type f -exec sed -i '1d' "{}" \;

# zip
rm "./tmp/$module_name.zip"
zip -j -r "./tmp/$module_name.zip" "./tmp/$module_name/"