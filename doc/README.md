# Artifacts and Notes
This directory contains some artifacts from running different
maven commands during the development of this application and
trying to get the maven shade plugin working

# Articles
- [Why fat jars](https://product.hubspot.com/blog/the-fault-in-our-jars-why-we-stopped-building-fat-jars)
- 
# Background
- By default the shade plugin includes the first file in the fat JAR and discards the rest. This caused some really frustrating bugs until we figured out what was going on

- the shade plugin supports resource transformers that allow you to define a merge strategy when it encounters duplicate files

- alternative is to use the maven-dependency-plugin to copy all of the application’s dependencies into the build directory
