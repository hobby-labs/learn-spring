#!/bin/bash
set -m

NUM_OF_REDIS_SERVERS=3
CONTAINER_IP="172.31.111.2"

main() {
    local i
    local port
    local server_count=0
    local try_count=100
    local ret
    local cluster_option=""

    for i in $(seq 1 ${NUM_OF_REDIS_SERVERS}); do
        port=$((10000 + $i))

        if [[ $server_count == "0" ]]; then
            # Run without daemonize to watch the process at the after instruction
            exec redis-server /data/conf/redis.conf --port $port \
                --cluster-config-file /data/conf/redis_${i}.conf --daemonize no &
        else
            redis-server /data/conf/redis.conf --port $port \
                --cluster-config-file /data/conf/redis_${i}.conf --daemonize yes
        fi

        # Check whether the process succeeded in launching
        for i in $(seq 1 ${try_count}); do
            sleep 0.5
            redis-cli -p $port info > /dev/null 2>&1
            ret=$?

            [[ $ret -eq 0 ]] && break
        done

        if [[ $i -ge ${try_count} ]]; then
            echo "Failed to launch redis server" >&2
            return 1
        fi

        cluster_option+="${CONTAINER_IP}:${port} "
        ((server_count++))
    done

    redis-cli --cluster create $cluster_option <<< "yes"
    ret=$?
    if [[ $ret -ne 0 ]]; then
        echo "ERROR: Failed to create cluster" >&2
        return 1
    fi

    fg %1
}

main "$@"

