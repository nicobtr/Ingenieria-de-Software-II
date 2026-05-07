# Laboratorio JWT — API REST Node.js

## Parte 1 — Validación local (Windows)

El servidor corre en Windows con `node server.js` en una terminal PowerShell. Las pruebas se hacen desde otra terminal PowerShell en el mismo equipo usando `localhost`.

### Arrancar el servidor
```powershell
node server.js
```
<img width="659" height="245" alt="image" src="https://github.com/user-attachments/assets/6e4a9a6c-106f-4ace-8626-fb2a6f541975" />

### Register
```powershell
Invoke-RestMethod -Method POST -Uri http://localhost:3000/auth/register -ContentType "application/json" -Body '{"username":"ana","email":"ana@test.com","password":"1234"}'
```
<img width="1132" height="131" alt="image" src="https://github.com/user-attachments/assets/febe320c-11d8-4f69-9104-6496c982c763" />

### Login y token
```powershell
$token = (Invoke-RestMethod -Method POST -Uri http://localhost:3000/auth/login -ContentType "application/json" -Body '{"email":"ana@test.com","password":"1234"}').token
$token
```
<img width="1144" height="93" alt="image" src="https://github.com/user-attachments/assets/92496537-c4aa-4353-b9b1-d5f031571e80" />


### GET /tasks sin token (401)
```powershell
Invoke-RestMethod -Method GET -Uri http://localhost:3000/tasks
```
<img width="1129" height="165" alt="image" src="https://github.com/user-attachments/assets/39a7f364-7dc1-4f30-8b63-0cad60594140" />

### GET /tasks con token
```powershell
Invoke-RestMethod -Method GET -Uri http://localhost:3000/tasks -Headers @{Authorization="Bearer $token"}
```
<img width="1141" height="106" alt="image" src="https://github.com/user-attachments/assets/27b39948-fe21-40f4-a307-fc406701a237" />


### POST /tasks
```powershell
$tarea = Invoke-RestMethod -Method POST -Uri http://localhost:3000/tasks -ContentType "application/json" -Headers @{Authorization="Bearer $token"} -Body '{"title":"Tarea de prueba","description":"Para probar PUT y DELETE"}'
$tarea
```
<img width="1144" height="209" alt="image" src="https://github.com/user-attachments/assets/ca12d2f1-44b2-4dff-b1b2-8eae62b74122" />


### PUT /tasks/:id
```powershell
Invoke-RestMethod -Method PUT -Uri http://localhost:3000/tasks/$($tarea.id) -ContentType "application/json" -Headers @{Authorization="Bearer $token"} -Body '{"status":"completed"}'
```
<img width="1150" height="470" alt="image" src="https://github.com/user-attachments/assets/341759d6-849a-4033-818c-d68d57ff35f7" />


### DELETE /tasks/:id
```powershell
Invoke-RestMethod -Method DELETE -Uri http://localhost:3000/tasks/$($tarea.id) -Headers @{Authorization="Bearer $token"}
```
<img width="1146" height="162" alt="image" src="https://github.com/user-attachments/assets/81688d09-ab66-4333-95ae-94aeb1d50aa6" />


---

## Parte 2 — Conexión SSH

SSH es un protocolo que permite controlar un equipo remotamente desde otro. Al conectarse, se obtiene una terminal del equipo remoto y los comandos se ejecutan allá, no en el equipo local.

En este caso tenemos dos dispositivos en una red local. El servidor corre en un PC con Ubuntu. Desde un PC con Windows se establece una conexión SSH hacia Ubuntu, y desde esa sesión remota en el PC con Windows se consumen los endpoints utilizando una terminal PowerShell que se instala en el PC con Ubuntu.

**Instalación del servidor SSH en Ubuntu:**
```bash
sudo apt install openssh-server
sudo systemctl enable ssh
sudo systemctl start ssh
sudo systemctl status ssh
```

<img width="1048" height="382" alt="WhatsApp Image 2026-05-06 at 9 29 35 PM" src="https://github.com/user-attachments/assets/f1173176-f729-4eaf-8eaa-6a2e3da264ce" />
<img width="914" height="458" alt="WhatsApp Image 2026-05-06 at 8 12 02 PM" src="https://github.com/user-attachments/assets/3f8223b4-3f86-45f1-815e-d198b2992286" />


**Instalación de PowerShell en Ubuntu:**
```bash
sudo snap install powershell --classic
```
<img width="1047" height="164" alt="WhatsApp Image 2026-05-06 at 9 35 14 PM" src="https://github.com/user-attachments/assets/ced5fc7e-e4fe-4c7c-aca0-4451a43be4ae" />


**Conexión desde Windows:**
```powershell
ssh nico@192.168.1.5
```
<img width="1025" height="383" alt="image" src="https://github.com/user-attachments/assets/96595c86-fe1b-4f71-ac6f-9a2eb8ac9d20" />


**Entrar a PowerShell dentro de Ubuntu:**
```bash
pwsh
```
<img width="1019" height="82" alt="image" src="https://github.com/user-attachments/assets/39126e3b-6d72-4bf2-8771-13e962ff645a" />

**Arrancar el servidor en la PC con Ubuntu**
```bash
node server.js
```
<img width="809" height="321" alt="WhatsApp Image 2026-05-06 at 9 47 07 PM" src="https://github.com/user-attachments/assets/0edb1747-5419-41dd-912f-fdd13deb2b0f" />


---

## Parte 3 — Validación remota de endpoints (Ubuntu vía SSH desde Windows)

El servidor sigue corriendo en Ubuntu. Los comandos se escriben en Windows pero se ejecutan en Ubuntu a través de SSH.

### Register
```powershell
Invoke-RestMethod -Method POST -Uri http://localhost:3000/auth/register -ContentType "application/json" -Body '{"username":"ana","email":"ana@test.com","password":"1234"}'
```
<img width="966" height="158" alt="image" src="https://github.com/user-attachments/assets/23499941-0478-4c4a-91c5-1a5be7d6c9c1" />


### Login y token
```powershell
$token = (Invoke-RestMethod -Method POST -Uri http://localhost:3000/auth/login -ContentType "application/json" -Body '{"email":"ana@test.com","password":"1234"}').token
$token
```
<img width="968" height="103" alt="image" src="https://github.com/user-attachments/assets/089258fb-acf5-43a2-9aa1-a980679601fe" />


### POST /tasks
```powershell
$tarea = Invoke-RestMethod -Method POST -Uri http://localhost:3000/tasks -ContentType "application/json" -Headers @{Authorization="Bearer $token"} -Body '{"title":"Tarea de prueba","description":"Para probar PUT y DELETE"}'
$tarea
```
<img width="956" height="196" alt="image" src="https://github.com/user-attachments/assets/15b82b7f-c73f-4c5d-bde8-480260eb1641" />


### PUT /tasks/:id
```powershell
Invoke-RestMethod -Method PUT -Uri http://localhost:3000/tasks/$($tarea.id) -ContentType "application/json" -Headers @{Authorization="Bearer $token"} -Body '{"status":"completed"}'
```
<img width="962" height="363" alt="image" src="https://github.com/user-attachments/assets/42d4c8a6-7b71-4644-817e-b6195364c8df" />


### DELETE /tasks/:id
```powershell
Invoke-RestMethod -Method DELETE -Uri http://localhost:3000/tasks/$($tarea.id) -Headers @{Authorization="Bearer $token"}
```
<img width="1123" height="130" alt="image" src="https://github.com/user-attachments/assets/c19ff0e2-8436-48e7-90c1-7e1bef1e3de4" />

