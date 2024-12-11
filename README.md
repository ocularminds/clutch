# **Clutch: Multi-Identity Service for Enterprise Security and Observability**

**Clutch** is a Spring Boot-based enterprise-grade application that integrates multiple identity providers (e.g., SAML, Keycloak, Azure IAM, AWS IAM, Okta) to provide robust authentication, role-based access control (RBAC), and observability. It is designed for scalability, performance, and security.

---

## **Features**
- **Multi-Identity Provider Support**:
    - Seamlessly switch between SAML, Keycloak, Azure IAM, AWS IAM, and Okta.
    - Configurable via `application.yml` or environment variables.

- **Role-Based Access Control (RBAC)**:
    - Enforce security with roles like `ADMIN`, `DEV`, `AUDIT`, and `OPERATIONS`.

- **JWT-Based API Security**:
    - Secure APIs with issued JWT tokens containing user roles and claims.

- **Observability**:
    - Integrated with **Micrometer** and **Prometheus** for metrics collection.
    - Dashboards with **Grafana** for monitoring authentication and API performance.

---

## **Quick Start**

### **1. Prerequisites**
- **Java 17** or higher
- **Gradle** 7.x
- **Docker** (for testing identity providers like Keycloak)
- **PostgreSQL** (for database integration)

### **2. Clone the Repository**
```bash
git clone https://github.com/your-repo/clutch.git
cd clutch
```

### **3. Configure Identity Providers**
   Modify the application.yml file to set up identity provider details:
```bash
identity:
provider: keycloak
saml:
metadata-url: "http://saml-idp.com/metadata"
keycloak:
issuer-uri: "http://localhost:8080/realms/example-realm"
client-id: "clutch-client"
client-secret: "keycloak-secret"
azure:
issuer-uri: "https://login.microsoftonline.com/tenant-id/v2.0"
client-id: "azure-client-id"
client-secret: "azure-secret"
aws:
issuer-uri: "https://cognito-idp.aws-region.amazonaws.com/aws-pool-id"
client-id: "aws-client-id"
client-secret: "aws-secret"
okta:
issuer-uri: "https://dev-xxxxxx.okta.com/oauth2/default"
client-id: "okta-client-id"
client-secret: "okta-secret"
```
### **4. Build and Run the Application**
   - **Run the application using Gradle:**

```bash
./gradlew clean build bootRun
```
Alternatively, package and run the application as a JAR:

```bash
./gradlew clean build
java -jar build/libs/clutch-0.0.1-SNAPSHOT.jar
```
## **Endpoints**
- **Authentication**
Login: Redirects to the configured identity provider for authentication.
Token Validation: Validates issued JWT tokens for secured API access.
- **APIs**
Public Endpoints:
- `/api/public` → Accessible by everyone.
- Role-Based Endpoints:
`/api/admin` → Requires ADMIN role.
`/api/dev` → Requires DEV role.
`/api/audit` → Requires AUDIT role.
`/api/operations` → Requires OPERATIONS role.

## **Observability**
Metrics exposed at `/actuator/prometheus`.
- **Monitor** application performance and authentication flows using Prometheus and Grafana.
- **Metrics Collected**:
- **Login Events**: Success and failure rates by identity provider.
- **API Hits**: Requests per endpoint, categorized by roles.
- **Latency**: Average response time for secured endpoints.

## **Testing**
### **1. Running Keycloak for Testing**
   Start a Keycloak container for local testing:

```bash
docker run -d \
-p 8080:8080 \
-e KEYCLOAK_ADMIN=admin \
-e KEYCLOAK_ADMIN_PASSWORD=admin \
quay.io/keycloak/keycloak:latest \
start-dev
```

### **2. Testing APIs**
   Use curl or a tool like Postman:

Fetch a token from your identity provider (e.g., Keycloak, Azure).
Use the token in the Authorization header to test secured endpoints:
```bash
curl -H "Authorization: Bearer <your-token>" http://localhost:8080/api/admin
```
## **Future Roadmap**
- **Add support for LDAP and custom identity providers.**
Extend observability to include distributed tracing with Jaeger.
Implement database-level security using PostgreSQL RLS.

- **License**
This project is licensed under the MIT License. See the LICENSE file for details.

yaml
---

### **Spring Boot Initialization Command**

For a **Gradle Build** with the necessary dependencies for Clutch, use the following command to initialize the project:

```bash
spring init \
  --name=clutch \
  --dependencies=web,security,oauth2-resource-server,oauth2-client,spring-data-jpa,postgresql,actuator \
  --build=gradle \
  --package-name=com.binaries.clutch \
  clutch
```
Additional Dependencies to Add
Edit the build.gradle file to include:

gradle
```bash
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
    implementation 'org.springframework.boot:spring-boot-starter-oauth2-resource-server'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
    implementation 'io.micrometer:micrometer-registry-prometheus'
    runtimeOnly 'org.postgresql:postgresql'
}
```