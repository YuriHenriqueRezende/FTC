# 🤖 Limelight 3A no FIRST Tech Challenge (FTC)

Este repositório documenta o uso da **Limelight 3A** como sistema de visão computacional para robôs do **FIRST Tech Challenge (FTC)**, incluindo aplicações em Autônomo, TeleOp e estratégias avançadas.

---

## 📷 O que é a Limelight 3A?

A **Limelight 3A** é uma câmera inteligente com coprocessador integrado, capaz de realizar processamento de visão computacional em tempo real.  
Ela se conecta ao **Control Hub** via USB/Ethernet e fornece dados prontos para o código em Java.

---

## 🎯 Principais Funcionalidades

### ✅ 1. Detecção de AprilTags
- Identificação de tags do campo (IDs)
- Cálculo de deslocamento (`tx`, `ty`)
- Estimativa de distância e orientação

### 🧭 2. Localização do Robô (BotPose)
- Estimativa da posição global do robô no campo (x, y, heading)
- Suporte a **MegaTag1** (visão) e **MegaTag2** (visão + IMU)

### 🎯 3. Aim Assist
- Alinhamento automático ao alvo
- Controle de giro baseado em erro angular

### 🧠 4. Inteligência Artificial
- Detecção de objetos (Detector)
- Classificação de objetos (Classifier)
- Detecção por cor (Color Pipeline)

### 🐍 5. Python (SnapScript)
- Execução de scripts Python dentro da Limelight
- Filtros de visão, cálculo de distância, IA e lógica customizada
- Envio de dados personalizados para o Java (`pythonOutput`)

---

## ⚙️ Integração com FTC SDK (Java)

Exemplo básico de leitura da Limelight:

```java
Limelight3A limelight = hardwareMap.get(Limelight3A.class, "limelight");
limelight.pipelineSwitch(0);
limelight.start();

LLResult result = limelight.getLatestResult();
if (result != null && result.isValid()) {
    double tx = result.getTx();
    double ty = result.getTy();
}
