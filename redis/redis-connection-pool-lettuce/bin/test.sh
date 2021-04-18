#!/usr/bin/env bash

curl http://localhost:8080/api/v1/setProduct -H 'Content-Type: application/json' -d '{"name": "Apple", "price": 100}'

while true; do
    echo -n "$(date) "
    start="$(date +%s)"
    curl http://localhost:8080/api/v1/getProduct/Apple
    [[ $(( $(date +%s) - ${start} )) -gt 2 ]] && echo -n " (###Late response###)"
    echo
    sleep 0.5
done

