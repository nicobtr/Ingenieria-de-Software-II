# Laboratorio JWT — API REST Node.js

## Parte 1 — Validación local (Windows)

El servidor corre en Windows con `node server.js` en una terminal PowerShell. Las pruebas se hacen desde otra terminal PowerShell en el mismo equipo usando `localhost`.

### Arrancar el servidor
```powershell
node server.js
```
![servidor corriendo](<img width="659" height="245" alt="image" src="https://github.com/user-attachments/assets/6e4a9a6c-106f-4ace-8626-fb2a6f541975" />
)

### Register
```powershell
Invoke-RestMethod -Method POST -Uri http://localhost:3000/auth/register -ContentType "application/json" -Body '{"username":"ana","email":"ana@test.com","password":"1234"}'
```
![register](<img width="1132" height="131" alt="image" src="https://github.com/user-attachments/assets/febe320c-11d8-4f69-9104-6496c982c763" />
)

### Login y token
```powershell
$token = (Invoke-RestMethod -Method POST -Uri http://localhost:3000/auth/login -ContentType "application/json" -Body '{"email":"ana@test.com","password":"1234"}').token
$token
```
![login](<img width="1144" height="93" alt="image" src="https://github.com/user-attachments/assets/92496537-c4aa-4353-b9b1-d5f031571e80" />
)

### GET /tasks sin token (401)
```powershell
Invoke-RestMethod -Method GET -Uri http://localhost:3000/tasks
```
![get sin token](<img width="1129" height="165" alt="image" src="https://github.com/user-attachments/assets/39a7f364-7dc1-4f30-8b63-0cad60594140" />
)

### GET /tasks con token
```powershell
Invoke-RestMethod -Method GET -Uri http://localhost:3000/tasks -Headers @{Authorization="Bearer $token"}
```
![get con token](<img width="1141" height="106" alt="image" src="https://github.com/user-attachments/assets/27b39948-fe21-40f4-a307-fc406701a237" />
)

### POST /tasks
```powershell
$tarea = Invoke-RestMethod -Method POST -Uri http://localhost:3000/tasks -ContentType "application/json" -Headers @{Authorization="Bearer $token"} -Body '{"title":"Tarea de prueba","description":"Para probar PUT y DELETE"}'
$tarea
```
![crear tarea](<img width="1144" height="209" alt="image" src="https://github.com/user-attachments/assets/ca12d2f1-44b2-4dff-b1b2-8eae62b74122" />
)

### PUT /tasks/:id
```powershell
Invoke-RestMethod -Method PUT -Uri http://localhost:3000/tasks/$($tarea.id) -ContentType "application/json" -Headers @{Authorization="Bearer $token"} -Body '{"status":"completed"}'
```
![put tarea](<img width="1150" height="470" alt="image" src="https://github.com/user-attachments/assets/341759d6-849a-4033-818c-d68d57ff35f7" />
)

### DELETE /tasks/:id
```powershell
Invoke-RestMethod -Method DELETE -Uri http://localhost:3000/tasks/$($tarea.id) -Headers @{Authorization="Bearer $token"}
```
![delete tarea](<img width="1146" height="162" alt="image" src="https://github.com/user-attachments/assets/81688d09-ab66-4333-95ae-94aeb1d50aa6" />
)

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
