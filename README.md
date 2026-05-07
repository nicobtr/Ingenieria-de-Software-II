# Laboratorio JWT — API REST Node.js

## Parte 1 — Validación local (Windows)

El servidor corre en Windows con `node server.js`. Las pruebas se hacen desde PowerShell en el mismo equipo usando `localhost`.

### Arrancar el servidor
```powershell
node server.js
```
![servidor corriendo](imagenes/01-servidor.png)

### Register
```powershell
Invoke-RestMethod -Method POST -Uri http://localhost:3000/auth/register -ContentType "application/json" -Body '{"username":"ana","email":"ana@test.com","password":"1234"}'
```
![register](imagenes/02-register.png)

### Login y token
```powershell
$token = (Invoke-RestMethod -Method POST -Uri http://localhost:3000/auth/login -ContentType "application/json" -Body '{"email":"ana@test.com","password":"1234"}').token
$token
```
![login](imagenes/03-login.png)

### GET /tasks sin token (401)
```powershell
Invoke-RestMethod -Method GET -Uri http://localhost:3000/tasks
```
![get sin token](imagenes/04-get-sin-token.png)

### GET /tasks con token
```powershell
Invoke-RestMethod -Method GET -Uri http://localhost:3000/tasks -Headers @{Authorization="Bearer $token"}
```
![get con token](imagenes/05-get-token.png)

### POST /tasks
```powershell
$tarea = Invoke-RestMethod -Method POST -Uri http://localhost:3000/tasks -ContentType "application/json" -Headers @{Authorization="Bearer $token"} -Body '{"title":"Tarea de prueba","description":"Para probar PUT y DELETE"}'
$tarea
```
![crear tarea](imagenes/06-post-tarea.png)

### PUT /tasks/:id
```powershell
Invoke-RestMethod -Method PUT -Uri http://localhost:3000/tasks/$($tarea.id) -ContentType "application/json" -Headers @{Authorization="Bearer $token"} -Body '{"status":"completed"}'
```
![put tarea](imagenes/07-put-tarea.png)

### DELETE /tasks/:id
```powershell
Invoke-RestMethod -Method DELETE -Uri http://localhost:3000/tasks/$($tarea.id) -Headers @{Authorization="Bearer $token"}
```
![delete tarea](imagenes/08-delete-tarea.png)

---

## Parte 2 — Conexión SSH

SSH es un protocolo que permite controlar un equipo remotamente desde otro. Al conectarse, se obtiene una terminal del equipo remoto y los comandos se ejecutan allá, no en el equipo local.

En este caso el servidor corre en el PC con Ubuntu. Desde el PC con Windows se establece una conexión SSH hacia Ubuntu, y desde esa sesión remota se consumen los endpoints.

**Instalación del servidor SSH en Ubuntu:**
```bash
sudo apt install openssh-server
sudo systemctl enable ssh
sudo systemctl start ssh
```
![ssh status](imagenes/09-ssh-status.png)

**Instalación de PowerShell en Ubuntu:**
```bash
sudo snap install powershell --classic
```
![powershell ubuntu](imagenes/10-pwsh-install.png)

**Conexión desde Windows:**
```powershell
ssh nico@192.168.1.5
```
![conexion ssh](imagenes/11-ssh-conexion.png)

**Entrar a PowerShell dentro de Ubuntu:**
```bash
pwsh
```
![pwsh](imagenes/12-pwsh.png)

---

## Parte 3 — Validación remota de endpoints (Ubuntu vía SSH desde Windows)

El servidor sigue corriendo en Ubuntu. Los comandos se escriben en Windows pero se ejecutan en Ubuntu a través de SSH.

### Register
```powershell
Invoke-RestMethod -Method POST -Uri http://localhost:3000/auth/register -ContentType "application/json" -Body '{"username":"ana","email":"ana@test.com","password":"1234"}'
```
![register remoto](imagenes/13-register-remoto.png)

### Login y token
```powershell
$token = (Invoke-RestMethod -Method POST -Uri http://localhost:3000/auth/login -ContentType "application/json" -Body '{"email":"ana@test.com","password":"1234"}').token
$token
```
![token remoto](imagenes/14-token-remoto.png)

### POST /tasks
```powershell
$tarea = Invoke-RestMethod -Method POST -Uri http://localhost:3000/tasks -ContentType "application/json" -Headers @{Authorization="Bearer $token"} -Body '{"title":"Tarea de prueba","description":"Para probar PUT y DELETE"}'
$tarea
```
![tarea remota](imagenes/15-post-remoto.png)

### PUT /tasks/:id
```powershell
Invoke-RestMethod -Method PUT -Uri http://localhost:3000/tasks/$($tarea.id) -ContentType "application/json" -Headers @{Authorization="Bearer $token"} -Body '{"status":"completed"}'
```
![put remoto](imagenes/16-put-remoto.png)

### DELETE /tasks/:id
```powershell
Invoke-RestMethod -Method DELETE -Uri http://localhost:3000/tasks/$($tarea.id) -Headers @{Authorization="Bearer $token"}
```
![delete remoto](imagenes/17-delete-remoto.png)
