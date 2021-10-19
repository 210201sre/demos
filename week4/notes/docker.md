# Docker

Docker is a lightweight virtualization technology that is similar to Virtual Machines. It is referred to as "containerization" instead of "virtualization".

The reason Docker came about was due to the often very cumbersome virtual machine image sizes. It was not uncommon to see multiple GB file sizes. This made it much more difficult to distribute/share these inages.

To add onto this, Virtual Machines included entire Operating Systems, along with the large quantity of system libraries. Many of these weren't even needed to run most applications.

Containers solved this by effectively removing the unneeded Operating System. They only include the exact dependencies that are needed for an application. This allows our images to be much smaller. This smaller size makes them far easier to share and distribute these images.

In addition to the overall smaller size, docker images have a layered structure. Images can have some "base image", and therefore, certain layers of the image can be reused, saving overall storage space and network bandwidth.

When we install docker, we get 3 primary features: creating images, running containers from images, and sharing images. These 3 features are sometimes separated out into different software tools. For example, there could be a different software tool to create an image versus one that runs a container from it.

The similarities are that regardless of the specific software tool used, the image structure is the same. In a similar idea to how Java ByteCode operates with different JVM implementations.

We'll generally use docker for all 3 features though.

## Dockerfiles

In order to create Docker images, we outline a set of instructions in a "Dockerfile". Which is a flat-file that contains text.

There are many instructions we can use, which are outlined [here](https://docs.docker.com/engine/reference/builder/).

Some common Dockerfile instructions are:
- FROM
    - Define a base image
- RUN
    - Execute some command
- CMD
    - Similar to RUN, but acts as the command to begin a container
- ENTRYPOINT
    - Similar to CMD
    - The primary difference is if you want to associate the container with a specific executable, you should use ENTRYPOINT
- COPY
    - Copy files
        - From the host machine to the image
        - From one stage of the image to another
- ADD
    - Similar to copy, but can also be used to grab files from a URL
- EXPOSE
    - Expose certain ports to be available

## Docker workflow

Once we outline our Dockerfile, we can "build" an image from it.

```sh
docker build -t image-name /some/path
```

The `-t` command flag will "tag" the image with some label. This will be the image name.

Once we have the image created, we can run containers from them.

```sh
docker run -d --name container-name --rm -p host-port:container-port -e ENV_VAR image-name
```

There are many common command flags that we use for the `docker run`.

The `-d` command runs the container in `detached` mode. Which is similar to running a process in the background. However, it's a bit different in that for a normal background process, the standard output would still be printed to the shell. However, for a container, all standard output is stored as part of the container logs.

The `--name` command flag gives a specific name to a container. Note that there can only be 1 container with a given name at a time. And since containers can still exist in a "stopped" or "paused" state, this might require you to manually remove a stopped container before you can re-create it.

The `--rm` command flag avoids the above issue. It will completely remove the container after it exits. It will not persist in a "stopped" state.

The `-p` flag binds certain ports to the container. Containers with exposed ports are still in an isolated system. The only way they can be accessed, is if you bind a port on the host system to the container. This will forward network traffic from the host system to the container at the specified ports. Most commonly, the same port will be used on both systems, but not always.

The `-e` flag allows us to inject environment variables into the container.

The combination of flags, `-it` is both `-i` and `-t`.
`-i` will run a container in "interactive" mode.
`-t` will allocate a psuedo TTY, which is essentially a fake terminal.
The combination of these two are generally used when you want to get a shell inside a running container.

From this point on, the container is running.

There are many actions we can take.

We could stop the container with `docker stop container-name/container-id`
We could look at its logs with `docker logs container-name/container-id`
We could get a shell inside the container with `docker exec -it container-name/container-id /bin/sh`
Amongst others.

## Sharing images

An important aspect to working with Docker images and containers is how to share them with others.

The docker CLI includes some `docker push` and `docker pull` commands that allow you to download/publish images to image "registries".

The most common docker image registry is [DockerHub](https://hub.docker.com/). If you haven't already, you should create an account there. It's free.

Each DockerHub user gets their own image registry, identified by their username.
Images are associated with specific image repositories by renaming (or "tagging") an image with that registry name as a prefix.

```sh
docker tag image-name registry-name/image-name
```

From there, you can download or publish images as desired.

```sh
docker push registry-name/image-name

docker push ikenoxamos/jdbc:latest
```

## Image tags

In addition to being associated with a registry, docker images have a specific tag at the end (after a colon).

If not specified, it will have the `latest` tag.
All docker images will have 3 portions to an image name: `registry/image-name:tag-name`
