# System Overview

```mermaid
flowchart LR

Client["Web / Mobile Client"]

subgraph Backend["Service Marketplace API"]

Controller["Controllers"]

Facade["Facades"]

Service["Services"]

Repository["Repositories"]

Security["Spring Security + JWT"]

end

Database[(MySQL)]

Client --> Controller

Controller --> Facade

Facade --> Service

Service --> Repository

Repository --> Database

Controller --> Security

Security --> Controller
```

## Description

The application follows a layered architecture.

* Controllers expose REST endpoints.
* Facades orchestrate business workflows.
* Services contain business logic.
* Repositories handle persistence.
* Spring Security validates authentication and authorization.
* MySQL stores application data.
