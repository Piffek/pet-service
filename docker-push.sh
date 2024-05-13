docker build -t pet-service:$1 .
docker tag pet-service:latest piffek1/pet-service:$1
docker push piffek1/pet-service:$1
