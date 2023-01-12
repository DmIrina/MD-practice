# Розробка мікросервісів
#### лабораторна робота №4

---


## Images
#### MovieService - Spring Boot App (backend)

```
docker build --no-cache -t dmirina/cinemaservice:4.1 CinemaService
```

```
docker push dmirina/cinemaservice:4.1
```

#### TicketService - Spring Boot App (backend)

```
docker build --no-cache -t dmirina/ticketservice:4.1 TicketService
```

```
docker push dmirina/ticketservice:4.1
```

#### Migrations

```
docker build --no-cache -t dmirina/migrations:2.0 migrations
```

```
docker push dmirina/migrations:2.0
```

---

```
choco install kubernetes-helm
```

```
minikube start
```

```
kubectl create namespace istio-system
```

```
helm repo add istio https://istio-release.storage.googleapis.com/charts
```

```
helm repo update
```

```
helm install istio-base istio/base -n istio-system
```

```
helm install istio-base istio/base -n istio-system
```

```
helm install istiod istio/istiod -n istio-system --wait
```

```
kubectl get all
```

```
kubectl apply -f k8s_v1/db
```

```
kubectl apply -f k8s_v1/client
```

```
kubectl apply -f k8s_v1/cinemaservice
```

```
kubectl apply -f k8s_v1/ticketservice
```

```
kubectl apply -f k8s_v2_retry/cinema-virtual-service.yaml
```

```
minikube tunnel
```


Postman:
POST:localhost:80/api/tickets/create

run test

localhost/api/session/session/bad

run test

```
kubectl apply -f k8s_v2_retry
```

```
minikube tunnel
```

run test

```
kubectl apply -f k8s_v3_circuit
```

```
minikube tunnel
```

run test

---

### Minikube useful commands

```
minikube addons enable ingress
```

```
minikube addons list
```

```
minikube dashboard --url
```
