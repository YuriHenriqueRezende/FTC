# 🤖 Modo Autônomo – FIRST Tech Challenge (FTC)

Este documento descreve o funcionamento, a arquitetura e as estratégias do **modo Autônomo (Autonomous Period)** do robô da equipe.

O Autônomo corresponde aos **30 segundos iniciais da partida**, onde o robô opera **sem controle humano**.

---

## 🎯 Objetivos do Autônomo

Durante o período autônomo, o robô deve:

- Navegar pelo campo de forma independente  
- Alinhar-se automaticamente aos alvos  
- Pontuar artefatos no GOAL  
- Executar tarefas estratégicas (coleta, posicionamento, estacionamento)  
- Preparar o robô para o TeleOp  

---

## 🧠 Arquitetura do Autônomo

### 🔹 Sensores Utilizados
- **Encoders** – controle de distância e deslocamento  
- **IMU** – controle de orientação (heading)  
- **Limelight 3A** – visão computacional (AprilTags, BotPose)  

### 🔹 Sistemas do Robô
- Sistema de Drive (mecanum/tank)  
- Shooter / Flywheel  
- Intake / Feeder  
- Controlador de visão  

---

## 🔁 Fluxo Geral do Autônomo

1. Inicialização dos sensores e hardware  
2. Leitura de AprilTags para decisão de rota  
3. Navegação até a zona de tiro  
4. Alinhamento automático com visão (Aim Assist)  
5. Sequência de disparos  
6. Ações adicionais (coleta, reposicionamento)  
7. Estacionamento final  

---

## 🧩 Estrutura de Software

O autônomo é implementado como uma **máquina de estados**, evitando o uso excessivo de `sleep()`.

### Exemplo de Estados:
```text
INIT → MOVE → ALIGN → SHOOT → PARK → END
