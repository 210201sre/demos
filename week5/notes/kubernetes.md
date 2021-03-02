# Overview

Kubernetes is a software solution for "Container Orchestration". We want to manage containers on a largew scale, such as representing groups of containers and as many instances of a single container as we can, as well as track them. If they fail, we will want to quickly replace it. As well as quickly scale up or down the number of instances. Among many other features.

## How it works

Clusters: A high-level organization unit for Kubernetes. Everything in Kubernetes is performed on a "cluster". A Cluster is a group of servers (each server would be referred to as a "node")
Generally, you might only have 1 cluster per global region.
Or perhaps, you might have a cluster per datacenter.

One of our nodes will run the Kubernetes Control Plane and act as the manager of all of the nodes. The rest will be worker nodes.

## Kubernetes Control Plane

The main processes and it will act with all other processes.
This is our main entrypoint to our cluster.

- Kube-Controller Manager
    - The piece thati s in charge of all controller "objects"
    - These controller objects are in charge of defining a piece of the "desired state"
- Kube-Scheduler
    - Watch all objects and schedules the objects to nodes for them to execute
- Etcd
    - A little database, a key-value store, to store everything that is needed for running the cluster
- Kube-Api-Server
    - A RESTful API Server
    - It has "resources" for all of the different Kubernetes objects that we can create and manipulate
- Kube-dns
    - DNS specifically for locating Kubernetes objects
    - Allows Kubernetes objects (services) to have separate IP Addresses from the node they operate on

### Desired State

Kubernetes represents the state of the cluster as the "current state" and compares it the configuration composed of all of the RESTful objects as the "Desired State".

At every moment, the Control plane (Kube-Controller-Manager and the Kube-Scheduler) will take actions to move the "Current State" into the "Desired State".

We use the RESTful API (or the kubectl commands) to change the "Desired State".

## Kubernetes Objects

- Pods
    - An organational unit that consists of 1 to infinity containers
    - Some amount of containers
    - These containers can talk to each other directly, but may not have access externally
    - Basic Pods will have just 1 container
    - Advanced configurations might have 2+ containers
        - 1 for web service
        - 1 for containerized DB
    - Are ephemeral, they will be created and destroyed frequently
        - Anything you put inside a Pod is not guaranteed to exist the next moment
    - Discrete piece of an application
        - What this means in practice depends on the specific application
- Service
    - A controller object
    - An object that wraps all copies of the same pod behind 1 IP address, generally to make them more accessible
    - Static IP Address
        - Not necessarily visible from outside the cluster
        - Kubernetes Clusters follow a similar principle to VPCs in AWS
    - Redirect traffic to an available Pod (load balancing)
        - A software load balancer
        - Client Side Load Balancer
        - Works well, but is not as efficient as a Hardware Load Balancer
            - Server Side Load Balancer
            - Primarily for small amounts of traffic
        - It is possible to configure a Service object with an external load balancer
- Volume
    - Permanent data storage that can attach to one or more pods
- Namespace
    - Kind of like DB Schema, but for Kubernetes
    - Different objects in different namespaces don't often interact (but they can)
    - Generally it is to achieve multi-tenancy
- Deployment
    - A configuration for the way an application should be deployed (made publically accessible)
    - A controller object
    - Describes how to create a set of pods
        - Rules for replicas
        - Rules to create each pod, and will create identical pods to reach # replicas
    - Will have a name
- Ingress
    - Another controller object
    - Will act as the entrypoint for all services in our cluster (or namespace)
        - Not load balancing
        - Routing requests to different targets
        - Example
            - Rules for `/my-app1` URIs get redirected to ServiceA
            - Rules for `/my-app2` URIs get redirected to ServiceB
    - API Gateway
    - We wouldn't necessarily have to expose our services to the outside internet, we would only expose an ingress object
        - Would redirect to further inside the cluster
    - Most commonly will use Ngnix as the implementation, which is popular for proxy servers
- There are MANY MANY more Kubernetes objects
    - There is a ton of depth

## How to Create Objects

We use kubectl, which is a CLI for interacting with a kubernetes cluster.
We already talked about using the `kubectl config` command to view and change your Kubernetes context, to switch between clusters.

Most of the time, we'll be using `kubectl apply -f some_config_file.yml`.
We create Kubernetes "Manifests", which are generally YAML files (sometimes JSON) that have all of the configuration for a Kubernetes Object.

General Structure of a manifest:
- apiVersion
- Kind
    - The specific Kubernetes Object
    - Deployment
    - Service
    - Pod
- Metadata
    - Information about the object itself
    - Namespace
    - Name
    - Labels
    - Annotations
- Spec
    - All of the details for the corresponding object to be configured
    - This structure changes depending on the Kind of object in the manifest
    - Think about how the JSON structure in a RESTful API would change depending on the resource

## Node Components
These are the core components that each node will need to operate Kubernetes (to work with the control plane).

- Kubelet
    - Process that makes sure that the pods are running, keeps track of the state, and reports back to the control plane
- Kube-proxy
    - Process that manages networking for the node as well as redirection of requests to the appropriate pod
- Container Runtime
    - Software dependency (library) in ordeer to create containers
    - We could use docker
        - Kubernetes used to use docker, but does not anymore
    - Instead, Kubernetes uses a different runtime that is based on their Container Runtime Interface (CRI)
        - The implementation is different from docker, but the result is the same

## Types of Services
- ClusterIP
    - Will make a service for a set of pods and give them one static ip address, but this address will only be accessible inside the cluster
    - This won't be a _real_ IP address, it is one that only exists through kube-dns
    - There is no external IP Address
    - Can think of it as a private service, so to speak, only visible inside the cluster
- NodePort
    - Will do the same above, but will redirect traffic from one port on the node itself to the clusterIP
    - Accessible from the outside world
    - URL: `node-ip:node-port`
        - If your nodes are EC2s, then this could something like:
        - `ec2-52-90-106-95.compute-1.amazonaws.com:31225`
- LoadBalancer
    - Generally uses cloud providers (AWS, GCP, Azure) technology to build a load balancer
    - A physical load balancer
    - Generally supported by Kubernetes Clusters created through these cloud providers
    - Creates a separate static IP Address for the load balancer, and that traffic will redirect to the clusterIP
    - Load Balancers are very expensive
        - $15 a month for a bare minimum load balancer
        - Even if you barely use it