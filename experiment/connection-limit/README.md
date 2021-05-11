
curl -X POST http://localhost:8080/v1/setSomething

curl http://localhost:8080/v1/getSomething

for i in {1..100}; do echo "curl --silent --output /dev/null curl -X POST http://localhost:8080/v1/incrementThread"; done | xargs -I {} -P 256 bash -c {}

curl -X POST http://localhost:8080/v1/incrementThread

apt update
apt install iproute2

tc qdisc add dev eth0 root netem delay 3000ms

while true; do
  for i in {1..200}; do echo "curl --silent --output /dev/null http://localhost:8080/v1/getSomething"; done | xargs -I {} -P 256 bash -c {}
done
