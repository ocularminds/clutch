az aks create --resource-group your-resource-group --name clutch-aks --node-count 3 --generate-ssh-keys
az aks get-credentials --resource-group your-resource-group --name clutch-aks
kubectl apply -f clutch-deployment.yml
kubectl get pods
kubectl get services
