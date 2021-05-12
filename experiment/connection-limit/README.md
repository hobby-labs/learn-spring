
curl -X POST http://localhost:8080/v1/setSomething

curl http://localhost:8080/v1/getSomething

for i in {1..100}; do echo "curl --silent --output /dev/null curl -X POST http://localhost:8080/v1/incrementThread"; done | xargs -I {} -P 256 bash -c {}

curl -X POST http://localhost:8080/v1/incrementThread

# Curl with timeout
This cause "connection reset by peer" when the 70 seconds elapsed after requested.

* application.yml
```
server:
  connection-timeout: 5s
  tomcat:
    max-threads: 1
    accept-count: 2
```

* command(3 times)
```
for i in {1..4}; do
  echo "curl --connect-timeout 70 http://localhost:8080/v1/getSomething"
done | xargs -I {} -P 4 bash -c {}
```

```
$ curl --max-time 70 http://localhost:8080/v1/getSomething
```




apt update
apt install iproute2

tc qdisc add dev eth0 root netem delay 60000ms
tc qdisc show dev eth0
tc qdisc del dev eth0 root



while true; do
  for i in {1..200}; do echo "curl --silent --output /dev/null http://localhost:8080/v1/getSomething"; done | xargs -I {} -P 256 bash -c {}
done
