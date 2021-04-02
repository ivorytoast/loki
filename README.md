## FIX Protocol
* 8=FIX
* 1=USER_ID
* 2=SYMBOL
* 3=QUANTITY
* 4=PRICE
* 5=SIDE

docker run -it --network todo-app nicolaka/netshoot

## Docker
* mvn clean install -DskipTests
* docker build -t ivorytoast3853/loki .
* docker push ivorytoast3853/loki
* docker run ivorytoast3853/loki