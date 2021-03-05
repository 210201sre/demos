# Prometheus

## What is it?

Prometheus is a Systems monitoring and alerting toolkit. It supports a highly dimensional data model, and stores as time series. With this in mind, Prometheus also acts as a Time Series Database.

## What are its features?
- Supports PromQL (Prometheus Query Language) that allows highly customized queries to select metrics to visualize.
    - Has 4 primary types:
        - Instant Vectors
        - Range Vectors
        - Scalars
        - Strings
    - Has 4 metric types:
        - Gauge
        - Summary
        - Counter
        - Histogram
- Has a pull model over HTTP
    - Prometheus will "scrape" metrics from other sources at configured intervals
    - The default path that prometheus looks for is `/metrics` unless configured otherwise
- Supports a push model if needed for certain use-cases
    - Examples would be batch jobs that would not exist long enough to be scraped
    - In these scenarios, we can export the data directly to Prometheus through its `PushGateway`

## Pull vs Push Model

Each has their own pros/cons. Neither is necessarily better than the other.

Some pros of pull model:
- Services do not have to worry about packet loss'
- Don't have to worry about the location of prometheus

Some downsides of pull model:
- Services must exist long enough to be pulled from

### Understanding Prometheus Data Model

Prometheus' Highly dimensional data model can be quite difficult to wrap your mind around.

The perspective that I personally find most helpful is to think of metrics as 2-dimensional arrays
- For each metric, we have an array of labels/sources/tags
- Each of these arrays is itself an array, which consists of the metric's values over time

When we perform operations/functions, such as `sum` or `rate`, we must keep this dimensionality in mind.
`sum` expects an `Instant Vector`. This means, it wants only a single value across all of the different labels. `sum` will then add up all of those values, with a result of type `Counter` across all labels.

`rate` expects a Range Vector. For each label, it wants to compute a Counter that represents the "rate" of the counter over some time range. For example, the rate of some metric over the last 5m. This will output an Instant Vector.

#### Examples

```
sum(rate(container_cpu_usage_seconds_total{container="fluentd"}[5m]))
```

In the above case, we start from the `container_cpu_usage_seconds_total` metric. By using the `[5m]` timesacele, we obtain a Range Vector.

We further filter the `container_cpu_usage_seconds_total` metric to only include time-series that had the label of `container="fluentd"`. The result still has multiple labels. But for each metric, the value for the `container` label will definitely be `fluentd`. We are not removing labels, just filtering based on labels.

We then use the `rate` function on the provided Range Vector. This produces an Instant Vector in response. We finally use the `sum` function on the Instant Vector, getting a `Counter`.

Grafana will provide a Graph as well that shows what the result of this calculation would have been at different evaluation times.

```
sum(container_cpu_usage_seconds_total) by (container)
```

In the above case, we used the `sum` function on the `container_cpu_usage_seconds_total` metric to obtain a `Counter` that is the sum of all of the labels.

We then group the sum based on the value of the `container` label. This results in a Instant Vector, for the different values of the `container` label. Each time-series will have the sum across all of the other labels.

Once again, we get a graph that shows what the values would have been at different evaluation times.

In this particular example, one of the labels for `container`, the empty string (which represents the lack of a value for that label) had a far higher sum than all of the labeled time-series. With this in mind, we might want to choose to filter out that one time-series.

We can do so, by adding a selector against the `container` label, to not include the empty string.

```
sum(container_cpu_usage_seconds_total{container!=""}) by (container)
```