# Taller Mecánico - Patrones de Diseño en Java

## ¿De qué trata el programa?

La idea es simular el sistema básico de un taller mecánico. El taller tiene un registro donde anota las reparaciones del día, atiende distintos tipos de trabajo (frenos, motor, etc.), usa un sistgema de facturación viejo que desarrolló alguien que ya no trabaja allí y tiene dos formas de cobrarle al cliente: por hora o precio fijo según el trabajo.

Se aplican cuatro patrones.


## Resultado de ejecutar Main

<img width="797" height="293" alt="WhatsApp Image 2026-05-09 at 8 24 30 PM" src="https://github.com/user-attachments/assets/4342f9fa-865b-4549-be2c-ea5bd248e19f" />

---

## Patrones usados

### 1. Singleton (Creacional) — `Registro.java`

El registro de reparaciones del día tiene que ser único. No tiene sentido que existan dos registros distintos al mismo tiempo porque las reparaciones se irían a lugares diferentes y se perdería información. Con el patrón Singleton se garantiza que sin importar cuántas veces se pida la instancia del registro, siempre es el mismo objeto.

El truco consiste en que el constructor es `private`, entonces nadie puede hacer `new Registro()` desde afuera. La única forma de obtenerlo es con `getInstancia()`, que la primera vez lo crea y las siguientes veces devuelve el mismo.

<img width="753" height="538" alt="image" src="https://github.com/user-attachments/assets/cdbbc9bd-1cb0-42cb-9b5f-cd7307d901b2" />


En el Main se puede ver que `r1` y `r2` son el mismo objeto, y que lo que se agrega desde uno se ve en el otro:

<img width="540" height="128" alt="image" src="https://github.com/user-attachments/assets/f4e27a6c-ff77-4644-b983-6fa38214b3f6" />


---

### 2. Factory Method (Creacional) — `FabricaReparacion.java`

El taller hace distintos tipos de reparación. En vez de crear cada una directamente con `new ReparacionFreno()` o `new ReparacionMotor()` desde el main, se usa una fábrica que se encarga de eso. La clase abstracta `FabricaReparacion` define el método `crear()`, y cada subclase concreta (`FabricaFreno`, `FabricaMotor`) lo implementa y devuelve el tipo de reparación que le corresponde.

Esto sirve porque si después el taller empieza a hacer también cambios de aceite, solo se crea `ReparacionAceite` y `FabricaAceite` sin tocar nada más del sistema.

<img width="540" height="144" alt="image" src="https://github.com/user-attachments/assets/865a36fe-9df5-43ab-bc24-77f4dda0cedd" />

<img width="523" height="184" alt="image" src="https://github.com/user-attachments/assets/b2574819-77db-481d-aab1-32881477f9dc" />

<img width="551" height="189" alt="image" src="https://github.com/user-attachments/assets/6edaa5fb-02b6-4f30-a866-c885702d314d" />


Uso en el Main:

<img width="492" height="169" alt="image" src="https://github.com/user-attachments/assets/8d041489-0961-4f40-b8ba-0b034942d1ca" />


---

### 3. Adapter (Estructural) — `AdaptadorFacturacion.java`

El taller tiene un sistema de facturación viejo que hizo alguien que ya no trabaja ahí. Ya funciona entonces no es buena idea tocarlo, pero el problema es que su método se llama `generarFactura()` mientras que el sistema nuevo espera `calcular()` de `EstrategiaCobro` entonces no son compatibles.

El Adapter soluciona esto sin tocar ninguno de los dos lados: se crea una clase que implementa `EstrategiaCobro` (lo que el sistema nuevo entiende), y por dentro lo que hace es llamar a `generarFactura()` del sistema viejo. 

<img width="742" height="132" alt="image" src="https://github.com/user-attachments/assets/070a15ad-6d39-4374-9f07-70363f47c01f" />

<img width="779" height="324" alt="image" src="https://github.com/user-attachments/assets/eebac92f-ca3c-4bbe-a7ed-1b8e5eda0121" />


Uso en el Main:

<img width="623" height="82" alt="image" src="https://github.com/user-attachments/assets/a759208f-4012-45d0-8ff5-ded503f92d50" />

---

### 4. Strategy (Comportamiento) — `EstrategiaCobro.java`

El taller no siempre cobra igual. A veces cobra por hora trabajada y a veces negocia un precio fijo por el trabajo completo. La forma de cobrar puede cambiar sin que alguna otra parte del sistema tenga que cambiar también.

Con Strategy se define una interfaz `EstrategiaCobro` con el método `calcular()`, y cada clase concreta implementa su propia lógica. Si en algún momento el taller quiere agregar descuento para clientes frecuentes, solo crea una nueva estrategia.

<img width="426" height="126" alt="image" src="https://github.com/user-attachments/assets/63924df7-574a-49cd-88dc-e9643fac26d4" />

<img width="567" height="184" alt="image" src="https://github.com/user-attachments/assets/eb41ac5d-c392-4d53-b682-f095783f1721" />

<img width="707" height="303" alt="image" src="https://github.com/user-attachments/assets/c291014b-bdfa-4664-847c-2f618f015168" />



Uso en el Main:

<img width="864" height="128" alt="image" src="https://github.com/user-attachments/assets/bdf1e7b0-e98d-4826-8414-b0c7ac4f7b67" />

