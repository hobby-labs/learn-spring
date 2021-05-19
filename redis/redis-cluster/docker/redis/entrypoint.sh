#!/usr/bin/env bash

for i in {7000..7006}; do
    redis-server /redis.conf --port ${i} \
        --cluster-config-file /nodes.${i}.conf --daemonize yes

    COMMAND_STRING+=" 172.31.112.2:${i}"
done

while true; do
    for i in {7000..7006}; do
        echo "Trying to launch redis port ${i}"
        while [[ "$(redis-cli -p ${i} <<< "PING")" != "PONG" ]]; do
            sleep 0.5
        done
        break 2
    done
done

yes "yes" | redis-cli --cluster create ${COMMAND_STRING} --cluster-replicas 1

tail -f /dev/null

