az group create --name broccoli --location WestEurope
az aks create --resource-group broccoli --name broccoli-cluster --node-count 2 --generate-ssh-keys
az acr create --resource-group broccoli --name broccoliregistry --sku Basic --admin-enabled true
az aks get-credentials --resource-group broccoli --name broccoli-cluster
