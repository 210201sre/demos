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
