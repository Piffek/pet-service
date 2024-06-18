docker build -t pet-service:$1 .
docker tag pet-service:$1 piffek1/pet-service:$1
docker push piffek1/pet-service:$1
