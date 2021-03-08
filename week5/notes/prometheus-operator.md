# Prometheus Operator

An extension to Kubernetes in order to ease the process of configuring Prometheus in a Kubernetes environemnt.

The reason this was originally difficult is that prometheus is configurated through 2 yaml files: `prometheus.yaml` and `rules.yaml`

If we wanted to change Prometheus' configuration in Kubernetes, we would have to change a configmap and then restart the Prometheus Pod to mount the new configuration.

This is especially difficult if you have a very large scale team with many many different applications that all want to expose metrics to Prometheus.

Prometheus Operator is an extension that was introduced that created new Kubernetes Objects (Custom Resource Definitions). This allows you to create Kubernetes manifests that govern Prometheus' Configuration.

Part of the `monitoring.coreos.com` api Group.

Some core Kubernetes Objects that are part of the Prometheus Operator API:
- Prometheus
    - The actual Prometheus Object
    - Has all of the configuration for Prometheus
- ServiceMonitor
    - A kind that defines how prometheus will perform scrapes against certain services
- PrometheusRule
    - A kind that defines recording and alerting rules
